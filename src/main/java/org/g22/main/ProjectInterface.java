package org.g22.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import org.apache.commons.io.FilenameUtils;
import org.g22.calc.StayPointCalc;
import org.g22.clustering.ClusterPoint;
import org.g22.clustering.StayPointCluster;
import org.g22.clustering.StayPointClustering;
import org.g22.entities.json.out.StayPoint;
import org.g22.file.ClusterPointsJSON;
import org.g22.file.FileSelector;
import org.g22.file.StayPointJSON;
import org.g22.file.StayPointKML;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ProjectInterface
{
	public void runProject() throws JsonParseException, JsonMappingException, IOException
	{
		StayPointCalc stayPointDetection=new StayPointCalc();
		StayPointClustering stayPointClustering=new StayPointClustering();
		ClusterPoint clusterPoint=new ClusterPoint();
		
		FileSelector fileSelector=new FileSelector();
		
		ArrayList<File> files = fileSelector.getFiles();
		
		String currDir=fileSelector.getCurrentDirectory();
		
		String jsonDestPath=currDir+File.separator+".."+File.separator+"Output"+File.separator+"JSON"+File.separator;
		String clJsonDestPath=currDir+File.separator+".."+File.separator+"Output"+File.separator+"CLJSON"+File.separator;
		String spKmlDestPath=currDir+File.separator+".."+File.separator+"Output"+File.separator+"SPKML"+File.separator;
		String clKmlDestPath=currDir+File.separator+".."+File.separator+"Output"+File.separator+"CLKML"+File.separator;
		
		File jsonFolder=new File(jsonDestPath);
		File spKmlFolder=new File(spKmlDestPath);
		File clKmlFolder=new File(clKmlDestPath);
		File clJsonFolder=new File(clJsonDestPath);
		
		if(!jsonFolder.exists())
		{
			jsonFolder.mkdirs();
		}
		if(!spKmlFolder.exists())
		{
			spKmlFolder.mkdirs();
		}
		if(!clKmlFolder.exists())
		{
			clKmlFolder.mkdirs();
		}
		if(!clJsonFolder.exists())
		{
			clJsonFolder.mkdirs();
		}
		
		for (File file : files)
		{
			String sorceJsonPath=file.getAbsolutePath();
			TreeSet<StayPoint> stayPoints = stayPointDetection.detectStayPoint(sorceJsonPath);
			ArrayList<StayPoint> apparentStayPoint=stayPointClustering.clustering(stayPoints);
			ArrayList<StayPointCluster> clusters = clusterPoint.addToClusters(apparentStayPoint,stayPoints);
			
			
			StayPointJSON jsonGenerator=new StayPointJSON();
			StayPointKML kmlGenerator = new StayPointKML();
			ClusterPointsJSON clJsonGenerator = new ClusterPointsJSON();
			
			String jsonDestFile=jsonDestPath+FilenameUtils.getBaseName(file.toString())+"-sp.json";
			String spKmlDestFile=spKmlDestPath+FilenameUtils.getBaseName(file.toString())+"-sp.kml";
			String clKmlDestFile=clKmlDestPath+FilenameUtils.getBaseName(file.toString())+"-cl.kml";
			String clJsonFile=clJsonDestPath+FilenameUtils.getBaseName(file.toString())+"-cl.json";
			
			jsonGenerator.generateJsonFile(stayPoints,jsonDestFile);
			kmlGenerator.generateKmlFile(stayPoints,spKmlDestFile);
			kmlGenerator.generateKmlFile(apparentStayPoint, clKmlDestFile);
			clJsonGenerator.generateJsonFile(clusters, clJsonFile);
		}
		
		System.out.println("JSON Files at : "+jsonFolder.getAbsolutePath());
		System.out.println("StayPoint-KML Files at : "+spKmlFolder.getAbsolutePath());
		System.out.println("Clustered StayPoint-KML Files at : "+clKmlFolder.getAbsolutePath());
		System.out.println("Clustered StayPoint-JSON Files at : "+clJsonFolder.getAbsolutePath());
	}
}
