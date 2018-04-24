package org.g22.calc;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.TreeSet;

import org.g22.entities.json.in.LocationData;
import org.g22.entities.json.out.StayPoint;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StayPointCalc
{
	private static final double distThresh = 0.1; /* In Kilometers */
	private static final long timeThresh = 15; /* In Minutes */
	
	private LocationData data;
	
	public TreeSet<StayPoint> detectStayPoint(String sourceJsonPath) throws JsonParseException, JsonMappingException, IOException
	{
		File file=new File(sourceJsonPath);
		ObjectMapper mapper=new ObjectMapper();
		data=mapper.readValue(file, LocationData.class);
		CalculateMean computeMean=new CalculateMean();
		
		int i=0;
		int pointNum=data.getLocations().size();
		
		CalculateDistance gpsDistance=new CalculateDistance();
		CalculateTimeDifference timeDiff=new CalculateTimeDifference();
		
		//HashSet<StayPoint> stayPoints=new HashSet<StayPoint>();
		
		TreeSet<StayPoint> stayPoints=new TreeSet<StayPoint>();
		
		while(i < pointNum)
		{
			int j = i + 1 ;
			int Token = 0 ;
			while(j < pointNum)
			{
				int lat1 = data.getLocations().get(i).getLatitudeE7();
				int long1 = data.getLocations().get(i).getLongitudeE7();
				int lat2 = data.getLocations().get(j).getLatitudeE7();
				int long2 = data.getLocations().get(j).getLatitudeE7();
				
				double dist=gpsDistance.calculateDistance(long1, lat1, long2, lat2, "K");
				
				if(dist>distThresh)
				{
					Timestamp timestamp1=data.getLocations().get(i).getTimestampMs();
					Timestamp timestamp2=data.getLocations().get(j).getTimestampMs();
					long timeSpan=timeDiff.compareTwoTimeStamps(timestamp1, timestamp2);
					
					if(timeSpan>timeThresh)
					{
						stayPoints.add(computeMean.computeMeanPoint(i, j,data));
						i=j;
						Token=1;
					}
					break;
				}
				j++;
			}
			if(Token != 1)
			{
				i=i+1;
			}
		}
		return stayPoints;
	}
}
