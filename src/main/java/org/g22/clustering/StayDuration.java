package org.g22.clustering;

import java.sql.Timestamp;

public class StayDuration
{
	private Timestamp arrivalTime;
	private Timestamp departureTime;
	
	public Timestamp getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Timestamp getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(Timestamp departureTime) {
		this.departureTime = departureTime;
	}
}
