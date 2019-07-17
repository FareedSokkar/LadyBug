package WorkSpace;

import java.util.ArrayList;
import java.util.Random;

import Entities.U_vector;
import Preprocessing.LZW;
import Preprocessing.NCD;
/**
 * 
 * @author fareed & ruba
 *This is our workspace for easier user in our system
 *which contain all of the information we need for an easier clustering and searching process
 */
public class FuzzyCMeansVar {
	private ArrayList<U_vector> _vectors_data_point;
	private int[][] data_vectors;
	private double[][] centroid;//centroid//cluster_centre
	private double [][] MatrixOfCofficents;//Mat of cof=//degree_of_memb
	private int NumberOfClusters;
	private int NumberOfVectors;
	private int NumberOfDimention;
	private double fuzziness;
	private double epsilon;
	private volatile boolean ready;//Check for vertivication
	private double[][] low_high;//0:Low,1:High
	private ArrayList<ArrayList<Integer>> clusters;
	private LZW Directory;
	private double AVGTime;
	//Make sure of right size of low high and set low to 1
	//Default value of double is 0.0 and our range is [0,1]
	/**
	 * Constructor
	 * @param _vectors_data_point
	 * @param NumberOfClusters
	 * @param fuzziness
	 * @param epsilon
	 */
	public FuzzyCMeansVar(ArrayList<U_vector> _vectors_data_point, int NumberOfClusters,double fuzziness
			,double epsilon) {
		setReady(false);
		MainSetUp.display("Disabling The Ready Flag!");
		set_vectors_data_point(_vectors_data_point);
		MainSetUp.display("Setting our tests vectors!");
		setEpsilon(epsilon);
		MainSetUp.display("Setting Epsilone!");
		setFuzziness(fuzziness);
		MainSetUp.display("Setting fuzziness level!");
		setNumberOfClusters(NumberOfClusters);
		MainSetUp.display("Setting Clusters number!");
		setNumberOfVectors(get_vectors_data_point().size());
		setDirectory(new LZW());
		MainSetUp.display("Creating new dictionary!");
		setData_vectors(new int[getNumberOfVectors()][]);
		//setAVGTime(time);
		MainSetUp.display("Converting our vectors using Lempel Ziv Welnch converter!");
		convertToIntVector();
		MainSetUp.display("Initilizing Low High Matrix!");
		lowHighIniti();
		MainSetUp.display("Initilizing Coeffictioncey Matrix!");
		intitRandomizeCoeffictioncey();
		setCentroid(new double[getNumberOfClusters()][getNumberOfDimention()]);
		MainSetUp.display("Enabling The Ready Flag!");
		setReady(true);
	}
	/**
	 * this function update the number of clusters and prepare our workspace for clustering
	 * @param numberOfClusters
	 */
	public void SetTheClusterAndUpdate(int numberOfClusters) {
		setReady(false);
		setNumberOfClusters(numberOfClusters);
		lowHighIniti();
		intitRandomizeCoeffictioncey();
		setCentroid(new double[getNumberOfClusters()][getNumberOfDimention()]);
		setReady(true);
	}
	/**
	 * this function is called after we found our clusters number fitted for our system and set our Clusters arraylist to the best fitted result.
	 */
	public void RearrangeClusters() {
		ArrayList<ArrayList<Integer>> clustersTmp=new ArrayList<ArrayList<Integer>>(getNumberOfClusters());
		for(int j=0;j<getNumberOfClusters();j++) {
			clustersTmp.add(new ArrayList<Integer>());
			for(int i=0;i<getNumberOfVectors();i++) {
				//if add
				if(Math.abs(getLow_high()[1][i]-getMatrixOfCofficents()[j][i])<=getFuzziness()) {
					clustersTmp.get(j).add(i);
				}
			}
		}
		setClusters(clustersTmp);
	}
	/**
	 * Addes new vectors to our workspace
	 * @param newV
	 */
	public void AddNewVectors(ArrayList<U_vector> newV) {
		setReady(false);
		for(U_vector uv:newV) {
			get_vectors_data_point().add(uv);
		}
		setNumberOfVectors(getNumberOfVectors()+newV.size());
		convertToIntVector();
		CoffictionceyMatrixNewVectors();
		setReady(true);
	}
	/**
	 * Initialize the low high matrix where 0 is low and 1 is high
	 */
	private void lowHighIniti() {
		setLow_high(new double[2][getNumberOfVectors()]);
		for(int i=0;i<getNumberOfVectors();i++) {
			getLow_high()[0][i]=1.0;
		}
	}
	/**
	 * calculate the CoffictionceyMatrix for the new vectors we added
	 */
	private void CoffictionceyMatrixNewVectors() {
		double[][] matTmp=new double[getNumberOfClusters()][getNumberOfVectors()];
		double[][] coMat=getMatrixOfCofficents();
		int cols=coMat[0].length;
		for(int i=0;i<cols;i++) {
			for(int j=0;j<getNumberOfClusters();j++) {
				matTmp[j][i]=coMat[j][i];
			}
		}
		for(int j=0;j<getNumberOfClusters();j++) {
			for(int i=cols;i<getNumberOfVectors();i++){
				getMatrixOfCofficents()[i][j] =NCD.normalizedCompressionDistance(getCentroid()[j],getData_vectors()[i]);
				updatelow_high(getMatrixOfCofficents()[i][j],j);
			}
		}
	}
	/**
	 * Initialize the Coeffictioncey matrix to random numbers between [0,1]
	 */
	private void intitRandomizeCoeffictioncey() {
		setMatrixOfCofficents(new double[getNumberOfClusters()][getNumberOfVectors()]);
		Random r = new Random();
		double value;
		for(int i=0;i<getNumberOfClusters();i++) {
			for(int j=0;j<getNumberOfVectors();j++) {
				value=r.nextDouble();
				setValueOfMatixIndex(i, j, value);
				updatelow_high(value,j);
			}
		}
	}
	/**
	 * update the low high matrix
	 * @param value
	 * @param j
	 */
	public void updatelow_high(double value,int j) {
		if(getLow_high()[0][j]>value) {
			getLow_high()[0][j]=value;
		}else if(getLow_high()[1][j]<value) {
			getLow_high()[1][j]=value;
		}
	}
	/**
	 * set value to MatrixOfCofficents()[i][j]
	 * @param i
	 * @param j
	 * @param value
	 */
	private void setValueOfMatixIndex(int i,int j, double value) {
	     getMatrixOfCofficents()[i][j]=value;
	}
	/**
	 * convert our U_vectors to int[] using the LZW compressor
	 */
	private void convertToIntVector() {
		ArrayList<ArrayList<Integer>> matrix=new ArrayList<ArrayList<Integer>>();
		int seconds=0;
		//maxDimention(get_vectors_data_point());
		getDirectory().EncoderList(get_vectors_data_point().get(0).getVec().toString());
		for(U_vector u:get_vectors_data_point()) {
			//int[] intVec=getDirectory().Encoder(u.getVec().toString());//Make Sure It's Awesome
			//int[] intVec=FCM_operations.compress(u);
			matrix.add(getDirectory().EncoderList(u.getVec().toString()));//make sure all vectors the same dimention
			seconds+=u.getFactor();
		}
		
		maxDimentionMatrix(matrix);
		int i=0;
		for(ArrayList<Integer> vec:matrix) {
			int[] temp=crossOver(vec);
			setVectorsMatrixIndex(i, temp);
			i++;
		}
		setAVGTime(seconds/getNumberOfVectors());
		
	}
	/**
	 * set the vector to the maximum dimension we have (fill up the rest to zeros
	 * @param vecs
	 * @return
	 */
	private int[] crossOver(ArrayList<Integer> vecs) {
		int[] temp=new int[getNumberOfDimention()];
		int i=0;
		for(int x:vecs) {
			temp[i]=x;
			i++;
		}
		for(;i<getNumberOfDimention();i++) {
			temp[i]=0;
		}
		return temp;
	}
	/**
	 * search for the maximum dimension and set it 
	 * @param matrix
	 */
	private void maxDimentionMatrix(ArrayList<ArrayList<Integer>> matrix) {
		int max=0;
		for(ArrayList<Integer> ul:matrix) {
			if(max<ul.size())
				max=ul.size();
		}
		setNumberOfDimention(max);
	}
	private void setVectorsMatrixIndex(int index,int[] vec) {
		this.data_vectors[index]=vec;
	}
	public int[][] getData_vectors() {
		return data_vectors;
	}
	public void setData_vectors(int[][] data_vectors) {
		this.data_vectors = data_vectors;
	}
	public ArrayList<U_vector> get_vectors_data_point() {
		return _vectors_data_point;
	}
	public void set_vectors_data_point(ArrayList<U_vector> _vectors_data_point) {
		this._vectors_data_point = _vectors_data_point;
	}
	public double[][] getCentroid() {
		return centroid;
	}
	public void setCentroid(double[][] centroid) {
		this.centroid = centroid;
	}
	public double [][] getMatrixOfCofficents() {
		return MatrixOfCofficents;
	}
	public void setMatrixOfCofficents(double [][] matrixOfCofficents) {
		MatrixOfCofficents = matrixOfCofficents;
	}
	public int getNumberOfClusters() {
		return NumberOfClusters;
	}
	public void setNumberOfClusters(int numberOfClusters) {
		NumberOfClusters = numberOfClusters;
	}
	public double getEpsilon() {
		return epsilon;
	}
	public void setEpsilon(double epsilon) {
		this.epsilon = epsilon;
	}
	public double getFuzziness() {
		return fuzziness;
	}
	public void setFuzziness(double fuzziness) {
		this.fuzziness = fuzziness;
	}
	public int getNumberOfVectors() {
		return NumberOfVectors;
	}
	public void setNumberOfVectors(int numberOfVectors) {
		NumberOfVectors = numberOfVectors;
	}
	public boolean isReady() {
		return ready;
	}
	public void setReady(boolean ready) {
		this.ready = ready;
	}
	public int getNumberOfDimention() {
		return NumberOfDimention;
	}
	public void setNumberOfDimention(int numberOfDimention) {
		NumberOfDimention = numberOfDimention;
	}
	public ArrayList<ArrayList<Integer>> getClusters() {
		return clusters;
	}
	public void setClusters(ArrayList<ArrayList<Integer>> clusters) {
		this.clusters = clusters;
	}
	public double[][] getLow_high() {
		return low_high;
	}
	public void setLow_high(double[][] low_high) {
		this.low_high = low_high;
	}
	public LZW getDirectory() {
		return Directory;
	}
	public void setDirectory(LZW directory) {
		Directory = directory;
	}
	public double getAVGTime() {
		return AVGTime;
	}
	public void setAVGTime(double aVGTime) {
		AVGTime = aVGTime;
	}
	
}
