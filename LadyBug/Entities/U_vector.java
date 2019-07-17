package Entities;

import java.util.Vector;
/**
 * 
 * @author fareed & ruba
 * U_vector is the vector class in our system for easier use.
 */
public class U_vector {

	private int factor;
	private int factorPareto;
	private String ID;
	private Vector<Object> vec;//=new Vector<Object>(0,1);
	public U_vector(int factor,String id) {
		setFactor(factor);
		setID(id);
		setFactorPareto(0);
		setVec(new Vector<Object>(0,1));
	}
	public U_vector(int factor,int factorPareto,String id,Vector<Object> vec) {
		setFactor(factor);
		setFactorPareto(factorPareto);
		setID(id);
		setVec(vec);
	}
	public U_vector(U_vector vec) {
		setFactor(vec.getFactor());
		setFactorPareto(vec.getFactorPareto());
		setID(vec.getID());
		setVec(vec.getVec());
	}
	public void addVectorElement(Object s) {
		getVec().addElement(s);
	}
	public int getFactorPareto() {
		return factorPareto;
	}
	public void setFactorPareto(int factorPareto) {
		this.factorPareto = factorPareto;
	}
	public Vector<Object> getVec() {
		return vec;
	}
	public void setVec(Vector<Object> vec) {
		this.vec = vec;
	}
	public int getFactor() {
		return factor;
	}
	public void setFactor(int factor) {
		this.factor = factor;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String toString() {
		StringBuilder str=new StringBuilder();
		str.append("["+getID()+","+getFactor()+","+getFactorPareto()+"]=[");
		for(Object s:getVec()) {
			str.append(s.toString()+",");
		}
		str.replace(str.length()-1, str.length()-1, "]");
		return str.toString();
		
	}
}
