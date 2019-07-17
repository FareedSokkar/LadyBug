package Preprocessing;

import java.util.ArrayList;

import WorkSpace.FuzzyCMeansVar;

public class Sillhouette {
	/**
	 * this function calculate the Sillhouette plot for our workspace
	 * @param system
	 * @return double
	 */
	public static double SillhouetteCompute(FuzzyCMeansVar system) {
		double sumSillhouetteSingle=0.0;
		int sumDivider=0,i=0;
		for(ArrayList<Integer> cl:system.getClusters()) {
			for(int vc:cl) {
				sumSillhouetteSingle+=SillhouetteComputeForPoint(system,i,vc);	
			}
			sumDivider+=cl.size();
			i++;
		}
		return sumSillhouetteSingle/sumDivider;
	}
	/**
	 * this function calculate the Sillhouette plot for one point
	 * @param system
	 * @param clusterNum
	 * @param vectorNum
	 * @return
	 */
	private static double SillhouetteComputeForPoint(FuzzyCMeansVar system,int clusterNum,int vectorNum) {
		double MinNeighbourAVGDistance=1,tmp,LocalAVGDistance = -1;
		for(int i=0;i<system.getNumberOfClusters();i++) {
			if(i!=clusterNum) {
				tmp=NeighbouringAverageDistance(system,vectorNum,i);
				if(MinNeighbourAVGDistance>tmp)
					MinNeighbourAVGDistance=tmp;
			}else {
				LocalAVGDistance=LocalAverageDistance(system,i,vectorNum);
			}
		}
		return ((MinNeighbourAVGDistance-LocalAVGDistance)/Math.max(LocalAVGDistance, MinNeighbourAVGDistance));
	}
	/**
	 * this function calculate the local average distance for one vector in certain centroid which contain the vector
	 * @param system
	 * @param clusterNum
	 * @param vectorNum
	 * @return double
	 */
	private static double LocalAverageDistance(FuzzyCMeansVar system,int clusterNum,int vectorNum) {
		int[] vec=system.getData_vectors()[vectorNum];
		return (sumClusterDistance(system,vec,clusterNum)/(system.getClusters().get(clusterNum).size()-1));
	}
	/**
	 * this function calculate the Neighbour average distance for one vector to certain Neighbour centroid
	 * @param system
	 * @param vectorNum
	 * @param neighberCluster
	 * @return double
	 */
	private static double NeighbouringAverageDistance(FuzzyCMeansVar system,int vectorNum,int neighberCluster) {
		int[] vec=system.getData_vectors()[vectorNum];
		return (sumClusterDistance(system,vec,neighberCluster)/(system.getClusters().get(neighberCluster).size()));
	}
	/**
	 * the sume of all the vectors distance to a certain vector in the same cluster
	 * @param system
	 * @param vec
	 * @param clusterNum
	 * @return
	 */
	private static double sumClusterDistance(FuzzyCMeansVar system,int[] vec,int clusterNum) {
		double sum=0.0;
		for(int cl:system.getClusters().get(clusterNum)) {
			sum+=NCD.normalizedCompressionDistance(vec,system.getData_vectors()[cl]);
		}
		return sum;
	}
}
