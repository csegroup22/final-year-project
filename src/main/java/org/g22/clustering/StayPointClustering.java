package org.g22.clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.g22.entities.json.out.StayPoint;

public class StayPointClustering
{
	private final double range=0.03;
	private final int minPoints=5;
	private final static int convFactor=-7;
	private final static int reConvFactor=7;
	
	public ArrayList<StayPoint> clustering(TreeSet<StayPoint> stayPoints)
	{
		DBSCANClusterer dbscan=new DBSCANClusterer(range, minPoints,new GeoDistanceMeasure());
		
		List<Cluster<DoublePoint>> cluster=dbscan.cluster(getGPS(stayPoints));
		
		ArrayList<StayPoint> apparentStayPoints=new ArrayList<StayPoint>();
		
		for (Cluster<DoublePoint> clstr : cluster)
		{
			ArrayList<DoublePoint> doublePoints = (ArrayList<DoublePoint>) clstr.getPoints();
			double [] centre=getClusterCentre(doublePoints);
			int lati=(int) (centre[0]*Math.pow(10, reConvFactor));
			int longi=(int) (centre[1]*Math.pow(10, reConvFactor));
			StayPoint stayPoint=new StayPoint(null, lati, longi, 0, null, null, null);
			apparentStayPoints.add(stayPoint);
		}
		return apparentStayPoints;
	}
	
	private double[] getClusterCentre(ArrayList<DoublePoint> doublePoints)
	{
		int n=doublePoints.size();
		
		double lati=0;
		double longi=0;

		for(int i=0;i<n;i++)
		{
			lati+=(double)doublePoints.get(i).getPoint()[0]/(double)n;
			longi+=(double)doublePoints.get(i).getPoint()[1]/(double)n;
		}
		double centre[]=new double[2];
		centre[0]=lati;
		centre[1]=longi;
		return centre;
		
	}

	private static ArrayList<DoublePoint> getGPS(TreeSet<StayPoint> stayPoints)
	{
	    ArrayList<DoublePoint> points = new ArrayList<DoublePoint>();
	    
	    for (StayPoint stayPoint : stayPoints)
	    {
	    	double d[]=new double[2];
	    	
	    	d[0]=stayPoint.getLatitudeE7()*Math.pow(10, convFactor);
	    	d[1]=stayPoint.getLongitudeE7()*Math.pow(10, convFactor);
	    	points.add(new DoublePoint(d));
		}
	    return points;
	}
}
