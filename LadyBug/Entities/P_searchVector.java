package Entities;

public class P_searchVector {
	/**
	 *This class is used to save the search party for Pareto algorithm 
	 *Variables:
	 *index_x - for cluster number
	 *index_y - for vectors number
	 *closeness - for the vector to the bugfix
	 *time_abs - for the vector runtime to the avreage
	 ***/
	private int index_x,index_y;
	private double closseness,time_abs;
	public P_searchVector(int index_x,int index_y,double e,double d) {
		setIndex_x(index_x);
		setIndex_y(index_y);
		setTime_abs(e);
		setClosseness(d);
	}
	public int getIndex_y() {
		return index_y;
	}

	public void setIndex_y(int index_y) {
		this.index_y = index_y;
	}

	public int getIndex_x() {
		return index_x;
	}

	public void setIndex_x(int index_x) {
		this.index_x = index_x;
	}

	public double getTime_abs() {
		return time_abs;
	}

	public void setTime_abs(double time_abs) {
		this.time_abs = time_abs;
	}

	public double getClosseness() {
		return closseness;
	}

	public void setClosseness(double d) {
		this.closseness = d;
	}
}
