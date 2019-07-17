package Preprocessing;

public class NCD {
	/**
	 * This function calculate the Normalized Compression Distance
	 * The value of the result will be in between 0<=X<=1 
	 * @param first
	 * @param second
	 * @return double
	 */
	public static double normalizedCompressionDistance(int[] first,int[] second) {
		//NCD(X,Y)=(Z(XY)-min{Z(X),Z(Y)})/(max{Z(X),Z(Y)})
		//Get all the Compressed values needed
		double firstNorma=(vectorNorma(first));
		double secondNorma=(vectorNorma(second));
		double catNorma=(vectorNorma(combineVectors(first, second)));
		//return the NCD
		return (((catNorma)-(Math.min(firstNorma, secondNorma)))/(Math.max(firstNorma, secondNorma)));
	}
	/**
	 * This function calculate the Normalized Compression Distance
	 * The value of the result will be in between 0<=X<=1 
	 * @param centroid
	 * @param test
	 * @return double
	 */
	public static double normalizedCompressionDistance(double[] centroid,int[] test) {
		//NCD(X,Y)=(Z(XY)-min{Z(X),Z(Y)})/(max{Z(X),Z(Y)})
		//Get all the Compressed values needed
		double firstNorma=(vectorNorma(centroid));
		double secondNorma=(vectorNorma(test));
		double catNorma=(vectorNorma(combineVectors(centroid, test)));
		//return the NCD
		return (((catNorma)-(Math.min(firstNorma, secondNorma)))/(Math.max(firstNorma, secondNorma)));
	}
	/**
	 *  This function calculate the vector Norma
	 * @param vec: double array type
	 * @return double after calculating the vector Norma
	 **/
	private static double vectorNorma(double[] vec) {
		double sum=0;
		for(double x:vec) {
			sum+=(Math.pow(x, 2));
		}
		return Math.sqrt(sum);
	}
	/**
	 * @param vec: int[] type
	 * @return double after calculating the vector Norma
	 * This function calculate the vector Norma**/
	private static double vectorNorma(int[] vec) {
		int sum=0;
		for(int x:vec) {
			sum+=(Math.pow(x, 2));
		}
		return Math.sqrt(sum);
	}
		/**
	 * This function Combine two integer arrays into one
	 * @param first
	 * @param second
	 * @return int[]
	 */
	private static int[] combineVectors(int[] first,int[] second) {
		int[] combined=new int[first.length+second.length];
		System.arraycopy(first, 0, combined, 0, first.length);
		System.arraycopy(second, 0, combined, first.length,second.length);
		return combined;
	}
	/**
	 * This function Combine two arrays into one double[] array
	 * @param first
	 * @param second
	 * @return double []
	 */
	private static double[] combineVectors(double[] first,int[] second) {
		double[] combined=new double[first.length+second.length];
		System.arraycopy(first, 0, combined, 0, first.length);
		System.arraycopy(copyFromIntArray(second), 0, combined, first.length,second.length);
		return combined;
	}
	/**
	 * this function convert from int[] to double []
	 * @param source
	 * @return double []
	 */
	private static double[] copyFromIntArray(int[] source) {
	    double[] dest = new double[source.length];
	    for(int i=0; i<source.length; i++) {
	        dest[i] = source[i];
	    }
	    return dest;
	}
}
