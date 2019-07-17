package HandlingBugfix;

import java.util.ArrayList;
import Entities.S_selectiveVectors;
import Entities.U_vector;
import WorkSpace.FuzzyCMeansVar;

public class SelectiveChoice {
	public static void printIt(String s) {
		System.out.println(s);
	}
	/**
	 * this function set a matrix 3 by cluster numbers where row 0 is the lowest runtime value in the cluster, row 1 is the highest and row
	 * 2 is the average.
	 * @param system
	 * @return int[][]
	 */
	public static int[][] SetRangeValuesOfTime(FuzzyCMeansVar system) {
		int[][] ClusterLow_High=new int[3][system.getNumberOfClusters()];//0:low,1:high,2:AVG_Cluster
		int i=0,sum;
		for(ArrayList<Integer> cls:system.getClusters()) {
			sum=0;
			for(Integer vec:cls) {
				if(ClusterLow_High[0][i]>system.get_vectors_data_point().get(vec).getFactor())//check minimum time
					ClusterLow_High[0][i]=system.get_vectors_data_point().get(vec).getFactor();
				if(ClusterLow_High[1][i]<system.get_vectors_data_point().get(vec).getFactor())//check maximum time
					ClusterLow_High[1][i]=system.get_vectors_data_point().get(vec).getFactor();
				sum+=system.get_vectors_data_point().get(vec).getFactor();
			}
			ClusterLow_High[2][i]=sum/cls.size();
			i++;
		}
		return ClusterLow_High;
	}
	
	/**
	 * This function choice the from our clustered set a vector group according to some limits which are given as parameters
	 * @param system
	 * @param wantedTime
	 * @param percentOfVect
	 * @param delta
	 * @param low_highTime
	 * @return S_selectiveVectors
	 */
	public static S_selectiveVectors static_Choice(FuzzyCMeansVar system,int wantedTime,double percentOfVect,double delta,int[][] low_highTime) {
		//Get automatically according to Delta & Percent of vectors
		//Delta: [0.001,1]
		
			//Variables================================================
		    int avg=wantedTime/system.getNumberOfClusters();//make sure in seconds
		    int UDelta=(int) (wantedTime*delta);
			int i=0,left,right,j;//clusters.length-1;
			//Random rand = new Random();
			int numberOfVect;
			S_selectiveVectors selected=new S_selectiveVectors();
			ArrayList<ArrayList <U_vector>> selectList = new ArrayList<ArrayList <U_vector>>();
			//=========================================================
			//Error in choice
			for(ArrayList<Integer> c:system.getClusters()) {
				numberOfVect=(int)(percentOfVect*c.size());
				ArrayList<U_vector> temp=new ArrayList<U_vector>();
				//Make sure in range
				if(avg>low_highTime[1][i]) {//AVG out of range 
					left=low_highTime[2][i];
					right=low_highTime[1][i];
					
				}else if(avg < low_highTime[0][i]) {
					left=low_highTime[0][i];
					right=low_highTime[2][i];
					
				}else if(avg-UDelta>low_highTime[2][i] || avg+UDelta<low_highTime[2][i]) {
					left=low_highTime[2][i]-UDelta;
					right=low_highTime[2][i]+UDelta;
				}else {
					left=avg-UDelta;
					right=avg+UDelta;
				}
				//Vector Choice
				j=0;
				while(temp.size()!=numberOfVect && j<c.size()) {
					if((left< system.get_vectors_data_point().get(c.get(j)).getFactor()) && (system.get_vectors_data_point().get(c.get(j)).getFactor()<right)) {
						temp.add(new U_vector(system.get_vectors_data_point().get(c.get(j))));
					}
					j++;
				}
				selectList.add(new ArrayList<U_vector>(temp));//Make sure of the Allocation*/
				i++;
			}
			 selected.setSelectList(selectList);
			 selected.setAvgTime(avg);
			 return selected;
			
		}
/**
 * This function convert the normal runtime of each vector into |runtime-AverageTime|
 * @param s
 * @return S_selectiveVectors
 */
	public static S_selectiveVectors convertingTime(S_selectiveVectors s) {
		for(ArrayList<U_vector> c:s.getSelectList()) {
			for(U_vector v:c) {
				v.setFactorPareto(Math.abs(v.getFactor()-s.getAvgTime()));/*Check Factor & Factor Pareto*/
			}
		}
		return s;
	}
}
