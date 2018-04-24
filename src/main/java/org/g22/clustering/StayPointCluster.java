package org.g22.clustering;

import java.util.ArrayList;

import org.g22.entities.json.out.StayPoint;

public class StayPointCluster
{
	private int id;
	private StayPoint clusterCentre;
	private ArrayList<StayPoint> clusteredStayPoints;
	private ArrayList<StayDuration> durations;
	
	public StayPointCluster()
	{
		clusteredStayPoints=new ArrayList<StayPoint>();
		durations=new ArrayList<StayDuration>();
	}
	
	public StayPointCluster(int id, StayPoint clusterCentre, ArrayList<StayPoint> clusteredStayPoint)
	{
		super();
		this.id = id;
		this.clusterCentre = clusterCentre;
		this.clusteredStayPoints = clusteredStayPoint;
		clusteredStayPoints=new ArrayList<StayPoint>();
		durations=new ArrayList<StayDuration>();
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public StayPoint getClusterCentre()
	{
		return clusterCentre;
	}
	public void setClusterCentre(StayPoint clusterCentre)
	{
		this.clusterCentre = clusterCentre;
	}
	public ArrayList<StayPoint> getClusteredStayPoints()
	{
		return clusteredStayPoints;
	}
	public void setClusteredStayPoints(ArrayList<StayPoint> clusteredStayPoint)
	{
		this.clusteredStayPoints = clusteredStayPoint;
	}
	public ArrayList<StayDuration> getDurations() {
		return durations;
	}
	public void setDurations(ArrayList<StayDuration> durations) {
		this.durations = durations;
	}
}
