package org.g23.main;

import java.io.IOException;
import java.util.HashSet;

import org.g23.entities.json.out.StayPoint;
import org.g23.filegenerator.StayPointJSON;
import org.g23.filegenerator.StayPointKML;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ProjectInterface
{
	public void runProject() throws JsonParseException, JsonMappingException, IOException
	{
		StayPointDetection stayPointDetection=new StayPointDetection();
		HashSet<StayPoint> stayPoints = stayPointDetection.detectStayPoint();
		StayPointJSON jsonGenerator=new StayPointJSON();
		StayPointKML kmlGenerator = new StayPointKML();
		jsonGenerator.generateJsonFile(stayPoints);
		kmlGenerator.generateKmlFile(stayPoints);
	}
}
