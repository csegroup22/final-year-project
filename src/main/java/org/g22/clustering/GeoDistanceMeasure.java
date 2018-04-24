package org.g22.clustering;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.ml.distance.DistanceMeasure;
import org.g22.calc.GPSCoordinateDistance;

public class GeoDistanceMeasure implements DistanceMeasure
{
	public double compute(double[] a, double[] b) throws DimensionMismatchException
	{
		GPSCoordinateDistance gpsCoordinateDistance=new GPSCoordinateDistance();
		
		return gpsCoordinateDistance.gpsDistance(a[1], a[0], b[1], b[0], "K");
	}
}
