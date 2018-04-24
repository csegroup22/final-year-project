package org.g22.clustering;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.TreeSet;

import org.g22.calc.CalculateDistance;
import org.g22.entities.json.out.StayPoint;

public class ClusterPoint
{
	private static double range=0.065;
	
	public void addToClusters(ArrayList<StayPoint> apparentStayPoint,TreeSet<StayPoint> stayPointsSet)
	{
		ArrayList<StayPoint> stayPoints=new ArrayList<StayPoint>(stayPointsSet);
		ArrayList<StayPointCluster> stayPointsClusters=new ArrayList<StayPointCluster>();
		
		for(int i=0;i<apparentStayPoint.size();i++)
		{
			StayPointCluster stayPointCluster=new StayPointCluster();
			stayPointCluster.setId(i);
			stayPointCluster.setClusterCentre(apparentStayPoint.get(i));
			stayPointsClusters.add(stayPointCluster);
		}
		
		int lastVisitedID=0;
		
		Timestamp lastArrivalTime=stayPoints.get(0).getArrivalTime();
		Timestamp lastDepartureTime=stayPoints.get(0).getDepartureTime();
		
		for(int i=0;i<stayPoints.size();i++)
		{
			for(int j=0;j<stayPointsClusters.size();j++)
			{
				if(isStayPointLiesInsideCluster(stayPoints.get(i),stayPointsClusters.get(j).getClusterCentre()))
				{
					StayDuration stayDuration=new StayDuration();
					stayDuration.setArrivalTime(lastArrivalTime);
					stayDuration.setDepartureTime(lastDepartureTime);
					
					if(lastVisitedID!=stayPointsClusters.get(j).getId())
					{
						stayPointsClusters.get(j).getDurations().add(stayDuration);
						lastArrivalTime=stayPoints.get(i).getArrivalTime();
						lastVisitedID=j;
					}
					
					if(lastVisitedID==stayPointsClusters.get(j).getId())
					{
						lastDepartureTime=stayPoints.get(i).getDepartureTime();
						stayDuration.setDepartureTime(lastDepartureTime);
					}
					
					break;
				}
			}
		}
	}

	private boolean isStayPointLiesInsideCluster(StayPoint stayPoint, StayPoint clusterCentre)
	{
		CalculateDistance calculateDist=new CalculateDistance();
		
		int latSP=stayPoint.getLatitudeE7();
		int longSP=stayPoint.getLongitudeE7();
		
		int latCC=clusterCentre.getLatitudeE7();
		int longCC=clusterCentre.getLongitudeE7();
		
		if(calculateDist.calculateDistance(longSP, latSP, longCC, latCC, "K")<=range)
			return true;
		
		return false;
	}
}
