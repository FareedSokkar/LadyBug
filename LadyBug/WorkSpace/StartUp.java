package WorkSpace;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Entities.U_vector;

public class StartUp {
	
	private ArrayList<U_vector> _vectors_data_point;
	public StartUp() throws IOException, InterruptedException {
		set_vectors_data_point(new ArrayList<U_vector>());
		File f = new File("Clusters/ClusteredVectors.txt");
		if(f.exists() && !f.isDirectory()) { 
		    /*
		     * SettUp from a clustered file
		     */
			MainSetUp.setFirsttime(false);
			MainSetUp.setDoneCluster(true);
		}else {
			
				File file = new File("Tests/PCount.txt");
				if(file.exists()&& !file.isDirectory()) {
					BufferedReader br;
					br = new BufferedReader(new FileReader(file));
					//Start pending all vectors
					String folderTest,PNm,TestsNm;
					while((folderTest=br.readLine())!=null) {
						//Each Line format: P#:##...#
						PNm=folderTest.substring(1, folderTest.indexOf(':'));
						TestsNm=folderTest.substring(folderTest.indexOf(':')+1, folderTest.length());
						pendVectors(PNm,TestsNm);
					}
					br.close();
					MainSetUp.setFirsttime(true);
					MainSetUp.setDoneCluster(false);
					//saveInputAsFile();
				}else {
					//Error
				}
				
	  
		}
	}
	private void pendVectors(String pNm,String testsNM)  throws IOException {
		BufferedReader br;
		U_vector tmp;
		String TimeFolder,vecTime;
		BufferedReader timBr=new BufferedReader(new FileReader("Tests/P"+pNm+"/Times.txt"));
		for(int i=0;i<Integer.parseInt(testsNM);i++) {
			br = new BufferedReader(new FileReader("Tests/P"+pNm+"/"+pNm+"_"+i+".txt"));
			String folderTest;
			TimeFolder=timBr.readLine();
			vecTime=TimeFolder.substring(TimeFolder.indexOf(':')+2, TimeFolder.length());
			tmp=new U_vector(Integer.parseInt(vecTime),pNm+"_"+i);
			while((folderTest=br.readLine())!=null) {
				tmp.addVectorElement(folderTest);				
			}
			get_vectors_data_point().add(tmp);
			
		}
		timBr.close();
	}
	public ArrayList<U_vector> get_vectors_data_point() {
		return _vectors_data_point;
	}
	public void set_vectors_data_point(ArrayList<U_vector> _vectors_data_point) {
		this._vectors_data_point = _vectors_data_point;
	}

}
