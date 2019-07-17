package Entities;

import java.util.ArrayList;
/**
 * 
 * @author fareed & ruba
 * This Entity class is used after determine which vectors we want to use in the search process.
 *
 */
public class S_selectiveVectors {
	private ArrayList<ArrayList <U_vector>> selectList;
	private int avgTime;
	public U_vector getU_vector(int clusternum,int vectornum) {
		return this.getSelectList().get(clusternum).get(vectornum);
	}
	public void addToClusterSelective(int index,U_vector e) {
		this.getSelectList().get(index).add(e);
	}
	public void setClusterSelectives(ArrayList <U_vector> als,int index) {
		this.getSelectList().set(index, als);
	}
	public ArrayList <U_vector> getClusterSelectives(int index){
		return this.getSelectList().get(index);
	}
	
	public ArrayList<ArrayList <U_vector>> getSelectList() {
		return selectList;
	}

	public void setSelectList(ArrayList<ArrayList <U_vector>> selectList) {
		this.selectList = selectList;
	}
	public int getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(int avgTime) {
		this.avgTime = avgTime;
	}
}
