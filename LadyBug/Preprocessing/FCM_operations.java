package Preprocessing;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import Entities.U_vector;
import WorkSpace.FuzzyCMeansVar;
import WorkSpace.MainSetUp;

public class FCM_operations {
	/**
	 * This function is the core of the Fuzzy Clustering algorithm 
	 * while the update in degree change is greater than epsilone then keep 
	 * calculating the new centroids values and update membership until converged.
	 * @param system
	 * @return
	 */
	public static boolean FCM(FuzzyCMeansVar system) {
		if(system.isReady()) {
			 double max_diff;
			 do {
			    calculate_centre_vectors(system);
			    max_diff = update_degree_of_membership(system);
			 } while (max_diff > system.getEpsilon());
			 system.RearrangeClusters();
			 return true;
		}else {
			return false;
		}
	}
	/**
	 * This function adds new vectors to our clustered set in case the amount of 
	 * vectors we are adding is lesser than a certain amount and the set already
	 * been clusters so we add the vectors and update our system.
	 * @param system
	 * @param vectorAdds
	 * @return boolean
	 */
	public static boolean PatchingFCM(FuzzyCMeansVar system,ArrayList<U_vector> vectorAdds) {
		system.AddNewVectors(vectorAdds);
		system.RearrangeClusters();
		return system.isReady();
	}
	/**
	 * This function is the function where it starts to look for the most suitable cluster numbers and set up the workspace accordingly
	 * @param fuzzy
	 * @param epsilone
	 */
	public static void StartLookingForClustersNumber(double fuzzy,double epsilone) {
		double max=-1,silhouetteValue;
		int clusterNm = 2;
		//Clusters Equals 2============================================================
		MainSetUp.setWorkSystem(new FuzzyCMeansVar(MainSetUp.getLoadedVectors().get_vectors_data_point(),2,fuzzy,epsilone));
		MainSetUp.display("Setting up Workspace for Fuzzy C-Means with 2 clusters...");
		FCM_operations.FCM(MainSetUp.getWorkSystem());
		MainSetUp.display("Running the Fuzzy C-Means clustering with 2 clusters...");
		silhouetteValue=Sillhouette.SillhouetteCompute(MainSetUp.getWorkSystem());
		MainSetUp.display("Obtaining Sillhouette plot value...");
		if(max<silhouetteValue) {
			clusterNm=2;
			max=silhouetteValue;
		}
		//=============================================================================
		for(int i=3;i<6;i++) {//Change if have time
			MainSetUp.getWorkSystem().SetTheClusterAndUpdate(i);
			MainSetUp.display("Setting up Workspace for Fuzzy C-Means with "+i+" clusters...");
			FCM_operations.FCM(MainSetUp.getWorkSystem());
			MainSetUp.display("Running the Fuzzy C-Means clustering with "+i+" clusters...");
			silhouetteValue=Sillhouette.SillhouetteCompute(MainSetUp.getWorkSystem());
			MainSetUp.display("Obtaining Sillhouette plot value...");
			if(max<silhouetteValue) {
				clusterNm=i;
				max=silhouetteValue;
			}
		}
		MainSetUp.display("Found the best clustering values...");
		MainSetUp.getWorkSystem().SetTheClusterAndUpdate(clusterNm);
		//MainSetUp.setWorkSystem(new FuzzyCMeansVar(MainSetUp.getLoadedVectors().get_vectors_data_point(),clusterNm,slider.getValue()/100.0,0.4));
		FCM_operations.FCM(MainSetUp.getWorkSystem());
		MainSetUp.display("Making sure the clustering set to the best values...");
		writeClusters(MainSetUp.getWorkSystem());
		MainSetUp.display("Done Proccessing, saving values...");
		MainSetUp.setDoneCluster(true);

	}
	/**
	 * This function write into a file our clustered datafile
	 * @param system
	 */
	private static void writeClusters(FuzzyCMeansVar system) {
		try (PrintWriter out = new PrintWriter("Clusters/Clustered.txt")) {
			int i=1;
			for(ArrayList<Integer> st:system.getClusters()) {
				out.println("Cluster Number "+i+" Centroid="+CentroidToString(system.getCentroid()[i-1])+" Vectors: {");
				for(Integer vec:st) {
					out.println(system.get_vectors_data_point().get(vec).toString());
				}
				out.println("}");
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * This function convert our centroid vector into String
	 * @param cen
	 * @return String
	 */
	private static String CentroidToString(double[] cen) {
		StringBuilder str=new StringBuilder();
		str.append("[");
		for(double s:cen) {
			str.append(s+",");
		}
		str.replace(str.length()-1, str.length()-1, "]");
		return str.toString();
	}
	/**
	 * This function calculate the centroids of each clusters   
	 * @param system
	 * @return boolean
	 */
	private static boolean calculate_centre_vectors(FuzzyCMeansVar system) {
		
		    int i, j, k;
		    double numerator, denominator;
		    int cols=system.getNumberOfVectors();
		    int rows=system.getNumberOfClusters();
		    double[][] t=new double[rows][cols];
		  
 		    for (i = 0; i < rows; i++)
		    {
		        for (j = 0; j < cols; j++)
		        {
		            t[i][j] = Math.pow(system.getMatrixOfCofficents()[i][j], system.getFuzziness());
		        }
		    }
		    for (j = 0; j <rows; j++)//Clusters
		    {
		        for (k = 0; k < system.getNumberOfDimention(); k++)//Dimensions
		        {
		            numerator = 0.0;
		            denominator = 0.0;
		            for (i = 0; i <cols ; i++) //Vectors
		            {
		                numerator += t[j][i] * system.getData_vectors()[i][k];
		                denominator += t[j][i];
		            }
		        system.getCentroid()[j][k]=numerator/denominator;
		        }
		    }
			return true;

		
	}
	/**
	 * This function update the degree of membership of each vector in each cluster
	 * @param system
	 * @return double
	 */
	private static double update_degree_of_membership(FuzzyCMeansVar system)
	{
	    int i, j;
	    double new_uij;
	    double max_diff = 0.0, diff;
	    for (j = 0; j < system.getNumberOfClusters(); j++) 
	    {
	        for (i = 0; i < system.getNumberOfVectors(); i++) 
	        {
	        	new_uij=NCD.normalizedCompressionDistance(system.getCentroid()[j],system.getData_vectors()[i]);
	            diff = new_uij - system.getMatrixOfCofficents()[j][i];
	            if (diff > max_diff)
	                max_diff = diff;
	            system.getMatrixOfCofficents()[j][i] = new_uij;
	            system.updatelow_high(new_uij, j);
	        }
	    }
	    return max_diff;

	}

}
