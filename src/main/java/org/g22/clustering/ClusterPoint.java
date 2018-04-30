package org.g22.clustering;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.TreeSet;

import org.g22.calc.CalculateDistance;
import org.g22.entities.json.out.StayPoint;

public class ClusterPoint
{
	private static double range=0.065;
	
	public ArrayList<StayPointCluster> addToClusters(ArrayList<StayPoint> apparentStayPoint,TreeSet<StayPoint> stayPointsSet)
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
		int startingPoint=0;
		
		for(int i=0;i<stayPoints.size();i++)
		{
			for(int j=0;j<stayPointsClusters.size();j++)
			{
				if(isStayPointLiesInsideCluster(stayPoints.get(i),stayPointsClusters.get(j).getClusterCentre()))
				{
					lastVisitedID=j;
					startingPoint=i;
					break;
				}
			}
		}
		
		Timestamp lastArrivalTime=stayPoints.get(startingPoint).getArrivalTime();
		Timestamp lastDepartureTime=stayPoints.get(startingPoint).getDepartureTime();
		
		for(int i=0;i<stayPoints.size();i++)
		{
			for(int j=0;j<stayPointsClusters.size();j++)
			{
				if(isStayPointLiesInsideCluster(stayPoints.get(i),stayPointsClusters.get(j).getClusterCentre()))
				{
					stayPointsClusters.get(j).getClusteredStayPoints().add(stayPoints.get(i));
					
					if(lastVisitedID==stayPointsClusters.get(j).getId())
					{
						lastDepartureTime=stayPoints.get(i).getDepartureTime();
					}
					
					if(lastVisitedID!=stayPointsClusters.get(j).getId())
					{
						StayDuration stayDuration=new StayDuration();
						stayDuration.setArrivalTime(lastArrivalTime);
						stayDuration.setDepartureTime(lastDepartureTime);
						stayPointsClusters.get(lastVisitedID).getDurations().add(stayDuration);
						lastArrivalTime=stayPoints.get(i).getArrivalTime();
						lastDepartureTime=stayPoints.get(i).getDepartureTime();
						lastVisitedID=j;
					}
					
					if(i==stayPoints.size()-1)
					{
						StayDuration stayDuration=new StayDuration();
						stayDuration.setArrivalTime(lastArrivalTime);
						stayDuration.setDepartureTime(lastDepartureTime);
						stayPointsClusters.get(lastVisitedID).getDurations().add(stayDuration);
						lastVisitedID=j;
					}
					break;
				}
			}
		}
		return stayPointsClusters;
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
