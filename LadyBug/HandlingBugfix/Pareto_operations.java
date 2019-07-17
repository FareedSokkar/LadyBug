package HandlingBugfix;

import java.util.ArrayList;

import Entities.P_searchVector;
import Entities.S_selectiveVectors;
import Entities.U_vector;
import Preprocessing.NCD;
import WorkSpace.MainSetUp;

public class Pareto_operations {
	/**
	 * @param ps:ArrayList<P_searchVector> type
	 * @param s:S_selectiveVectors type
	 * @return ArrayList<P_searchVector> type
	 * This is the Pareto Multi-objective searching algorithm ,it searches for the most relevent tests to our bugfixes and returnes them. **/
	public static ArrayList<P_searchVector> pareto(ArrayList<P_searchVector> ps,S_selectiveVectors choice) {
		int sum=0,val;
		double gradient,epsilone=0.0,diff;
		gradient=calculateBestGradient(ps);
		diff=calculateBestEpsiloneDiff(ps);
		ArrayList<P_searchVector> searched = null;
		do{//while(sum<(choice.getAvgTime()*choice.getSelectList().size())) {
			searched=getValuesInRange(gradient,epsilone,ps);
			sum=sumTime(searched,choice);
			epsilone+=diff;
			val=choice.getAvgTime()*choice.getSelectList().size();
		}while(sum<val);
		return searched;
	}
	/**
	 * @param s:S_selectiveVectors type
	 * @param bugfix:U_vector type
	 * @return ArrayList<P_searchVector> type
	 * This function prepare the Search set for the Pareto Algorithm for easier use.**/
	public static ArrayList<P_searchVector> readyTheSearchParty(U_vector bugfix,S_selectiveVectors staticChoice){
		ArrayList<P_searchVector> pSearch=new ArrayList<P_searchVector>();
		int i=0,j=0;
		for(ArrayList<U_vector> cls:staticChoice.getSelectList()) {
			j=0;
			for(U_vector vec:cls) {
				pSearch.add(new P_searchVector(i, j, ((vec.getFactorPareto()*1.0)/(Math.max(vec.getFactor(),staticChoice.getAvgTime())*1.0)),
						NCD.normalizedCompressionDistance(MainSetUp.getDirectory().Encoder(vec.getVec().toString()), MainSetUp.getDirectory().Encoder(bugfix.getVec().toString()))));
				j++;
			}
			i++;
		}
		return pSearch;
	}
	/**
	 * This function calculate and returns the most suitable gradient to this P_searchVector List
	 * @param ps
	 * @return double
	 */
	private static double calculateBestGradient(ArrayList<P_searchVector> ps) {
		double sumFact1=0.0;
		double sumFact2=0.0;
		for(P_searchVector vec:ps) {
			sumFact1+=vec.getClosseness();
			sumFact2+=vec.getTime_abs();
		}
		return (sumFact1/sumFact2);
	}
	/**
	 * This function calculate and returns the most suitable offset to this P_searchVector List
	 * @param ps
	 * @return double
	 */
	private static double calculateBestEpsiloneDiff(ArrayList<P_searchVector> ps) {
		double sum=1.0;
		int num=0;
		for(int i=0;i<ps.size();i++) {
			for(int j=i+1;j<ps.size();j++) {
						sum+=Math.abs(ps.get(i).getTime_abs()-ps.get(j).getTime_abs()); 
						num++;
			}
		}
		return (sum/num);//AVG of all differences
	}
	/**
	 * @param ps:ArrayList<P_searchVector> type
	 * @param s:S_selectiveVectors type
	 * @return int type
	 * This function sum up each time of each test vector in ps ArrayList.
	 * **/
	private static int sumTime(ArrayList<P_searchVector> ps,S_selectiveVectors choice) {
		int sum=0;
		for(P_searchVector psv:ps) {
			sum+=choice.getSelectList().get(psv.getIndex_x()).get(psv.getIndex_y()).getFactor();
		}
		return sum;
	}
	/**
	 * @param gradient:double type
	 * @param epsilone:double type
	 * @param ps:ArrayList<P_searchVector> type
	 * @return ArrayList<P_searchVector> type
	 * This function search through the ps ArrayList and return all the values in range of [gradient*X-epsilone,gradient*X+epsilone].
	 * **/
	private static ArrayList<P_searchVector> getValuesInRange(double gradient,double epsilone,ArrayList<P_searchVector> ps) {
		ArrayList<P_searchVector> setSearched=new ArrayList<P_searchVector>();
		for(P_searchVector psv:ps) {
			if(Math.abs((psv.getTime_abs()-(gradient*psv.getClosseness())))<=epsilone) {//g*c-e<=T<=g*c+e
				setSearched.add(new P_searchVector(psv.getIndex_x(),psv.getIndex_y(),psv.getTime_abs(),psv.getClosseness()));
			}
		}
		return setSearched;
	}
}
