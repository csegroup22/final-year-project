package org.g23.filegenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;

import org.g23.entities.json.out.StayPoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class StayPointJSON
{
	public void generateJsonFile(HashSet<StayPoint> stayPoints) throws JsonProcessingException, FileNotFoundException
	{
		ObjectMapper mapper=new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String stayPointFile = ow.writeValueAsString(stayPoints);
		PrintWriter out = new PrintWriter("/home/nirmal/Documents/Final-Year-Project/Output/Stay-Points.json");
		out.print(stayPointFile);
		out.close();
		System.out.println("Stay Points(JSON) at : /home/nirmal/Documents/Final-Year-Project/Output/Stay-Points.json");
	}
}
