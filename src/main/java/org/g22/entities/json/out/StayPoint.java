package org.g22.entities.json.out;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.math3.ml.clustering.Clusterable;
import org.g22.entities.json.in.Activity;

public class StayPoint implements Comparable<StayPoint>,Clusterable
{
	private Timestamp meanTime;
    private int latitudeE7;
    private int longitudeE7;
    private int accuracy;
    private Timestamp arrivalTime;
    private Timestamp departureTime;
    private List<Activity> activity;
	public StayPoint(Timestamp meanTime, int latitudeE7, int longitudeE7, int accuracy, Timestamp arrivalTime, Timestamp departureTime, List<Activity> activity)
	{
		super();
		this.meanTime = meanTime;
		this.latitudeE7 = latitudeE7;
		this.longitudeE7 = longitudeE7;
		this.accuracy = accuracy;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.activity = activity;
	}
	public Timestamp getMeanTime()
	{
		return meanTime;
	}
	public void setMeanTime(Timestamp meanTime)
	{
		this.meanTime = meanTime;
	}
	public int getLatitudeE7()
	{
		return latitudeE7;
	}
	public void setLatitudeE7(int latitudeE7)
	{
		this.latitudeE7 = latitudeE7;
	}
	public int getLongitudeE7()
	{
		return longitudeE7;
	}
	public void setLongitudeE7(int longitudeE7)
	{
		this.longitudeE7 = longitudeE7;
	}
	public int getAccuracy()
	{
		return accuracy;
	}
	public void setAccuracy(int accuracy)
	{
		this.accuracy = accuracy;
	}
	public Timestamp getArrivalTime()
	{
		return arrivalTime;
	}
	public void setArrivalTime(Timestamp arrivalTime)
	{
		this.arrivalTime = arrivalTime;
	}
	public Timestamp getDepartureTime()
	{
		return departureTime;
	}
	public void setDepartureTime(Timestamp departureTime)
	{
		this.departureTime = departureTime;
	}
	public List<Activity> getActivity()
	{
		return activity;
	}
	public void setActivity(List<Activity> activity)
	{
		this.activity = activity;
	}
	
	public int compareTo(StayPoint stayPoint)
	{
		if(this.arrivalTime.after(stayPoint.getArrivalTime()))
		{
			return 1;
		}
		if(this.arrivalTime.before(stayPoint.getArrivalTime()))
		{
			return -1;
		}
		return 0;
	}
	public double[] getPoint()
	{
		return null;
	}
	
}