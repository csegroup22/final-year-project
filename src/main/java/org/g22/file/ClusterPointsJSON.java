package org.g22.file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.g22.clustering.StayPointCluster;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class ClusterPointsJSON
{
	public void generateJsonFile(ArrayList<StayPointCluster> clStayPoints, String jsonDestFile) throws JsonProcessingException, FileNotFoundException
	{
		ObjectMapper mapper=new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String stayPointFile = ow.writeValueAsString(clStayPoints);
		PrintWriter out = new PrintWriter(jsonDestFile);
		out.print(stayPointFile);
		out.close();
	}
}
