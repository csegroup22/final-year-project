package org.g23.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.commons.io.FilenameUtils;
import org.g23.calc.StayPointCalc;
import org.g23.entities.json.out.StayPoint;
import org.g23.file.FileSelector;
import org.g23.file.StayPointJSON;
import org.g23.file.StayPointKML;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ProjectInterface
{
	public void runProject() throws JsonParseException, JsonMappingException, IOException
	{
		StayPointCalc stayPointDetection=new StayPointCalc();
		FileSelector fileSelector=new FileSelector();
		ArrayList<File> files = fileSelector.getFiles();
		
		String currDir=fileSelector.getCurrentDirectory();
		String jsonDestPath=currDir+File.separator+".."+File.separator+"Output"+File.separator+"JSON"+File.separator;
		String kmlDestPath=currDir+File.separator+".."+File.separator+"Output"+File.separator+"KML"+File.separator;
		
		File jsonFolder=new File(jsonDestPath);
		File kmlFolder=new File(kmlDestPath);
		
		if(!jsonFolder.exists())
		{
			jsonFolder.mkdirs();
		}
		if(!kmlFolder.exists())
		{
			kmlFolder.mkdirs();
		}
		
		for (File file : files)
		{
			String sorceJsonPath=file.getAbsolutePath();
			HashSet<StayPoint> stayPoints = stayPointDetection.detectStayPoint(sorceJsonPath);
			
			StayPointJSON jsonGenerator=new StayPointJSON();
			StayPointKML kmlGenerator = new StayPointKML();
			
			String jsonDestFile=jsonDestPath+FilenameUtils.getBaseName(file.toString())+".json";
			String kmlDestFile=kmlDestPath+FilenameUtils.getBaseName(file.toString())+".kml";
			
			jsonGenerator.generateJsonFile(stayPoints,jsonDestFile);
			kmlGenerator.generateKmlFile(stayPoints,kmlDestFile);
		}
		System.out.println("JSON Files at : "+jsonFolder.getAbsolutePath());
		System.out.println("KML Files at : "+kmlFolder.getAbsolutePath());
	}
}
