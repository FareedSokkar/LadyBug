package WorkSpace;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.*;

import Entities.P_searchVector;
import Entities.S_selectiveVectors;
import Entities.U_vector;
import HandlingBugfix.Pareto_operations;
import HandlingBugfix.SelectiveChoice;
import Preprocessing.LZW;
import UserUI.LOGINUI;
import UserUI.LoadUpScreen;
import UserUI.UserMainMenuUI;

public class MainSetUp {

	private static JFrame clientGUI;
	private static JPanel userMenu;
	private static JPanel removeJpanel;
	private static StartUp loadedVectors;
	private static FuzzyCMeansVar workSystem;
	private static S_selectiveVectors staticChoice;
	private static ArrayList<P_searchVector> readyParty;
	private static U_vector Bugfix;
	private static LZW directory;
	private static int[][] low_highTime;
	private static ArrayList<ArrayList<Integer>> setEnd;
	private static boolean firsttime;
	private static boolean doneCluster;
	private static String username;
	public static void main(String[] args) {
		MainSetUp.setClientGUI(new LOGINUI());
		MainSetUp.getClientGUI().setVisible(true);
		//MainSetUp.setUserMenu(((LoadUpScreen)MainSetUp.getClientGUI()).p);
		
	}
	public static void LoadUpScreen() {
		MainSetUp.getClientGUI().dispose();
		Thread thread = new Thread(){public void run(){
			MainSetUp.setClientGUI(new LoadUpScreen());
			MainSetUp.getClientGUI().setVisible(true);
			MainSetUp.setUserMenu(((LoadUpScreen)MainSetUp.getClientGUI()).p);
			try {
				for(int i=0;i<=100;i++) {
					Thread.sleep(25);
					((LoadUpScreen)MainSetUp.getClientGUI()).label_1.setText(Integer.toString(i)+"%");
					if(i==0)
						MainSetUp.display("Starting preparing the workspace...");
					if(i==10) {
						MainSetUp.display("Reading Program Counter file!");
						MainSetUp.display("Reading file lines!");
					}
					if(i==20) 
						MainSetUp.display("Reading Program 1 file tests!");
					if(i==30)
						MainSetUp.display("Finished Program 1 file tests Reading!");
					if(i==40) 
						MainSetUp.display("Reading Program 2 file tests!");
					if(i==50)
						MainSetUp.display("Finished Program 2 file tests Reading!");
					if(i==60) 
						MainSetUp.display("Reading Program 3 file tests!");
					if(i==70)
						MainSetUp.display("Finished Program 3 file tests Reading!");
					if(i==80)
						MainSetUp.display("All vectors are set!");
					if(i==90)
						MainSetUp.display("Finished Program Counter file reading!");
					if(i==95)
						MainSetUp.display("Workspace is ready!");
				}
				//setTrue buttons
				MainSetUp.ToolbarDisEnb(true);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			MainSetUp.RechangePanels(new UserMainMenuUI());
			    }
			  };
			  thread.start();
		
	}
	public static void ToolbarDisEnb(boolean flag) {
		((LoadUpScreen)getClientGUI()).homeimg.setEnabled(flag);
		((LoadUpScreen)getClientGUI()).accountimg.setEnabled(flag);
		((LoadUpScreen)getClientGUI()).setimg.setEnabled(flag);
	}
	public static ImageIcon resizeImage(ImageIcon iicon ,int width,int height) {
		Image image = iicon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(width, height,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		iicon = new ImageIcon(newimg);  // transform it back
		return iicon;
	}
	public static void display(String s)
	{
		((LoadUpScreen)getClientGUI()).serverConsole.append(">"+s+"\n");
		((LoadUpScreen)getClientGUI()).serverConsole.setCaretPosition(((LoadUpScreen)getClientGUI()).serverConsole.getDocument().getLength());
		/*
		((FuzzyClusteringUI)getUserMenu()).serverConsole.append(s+"\n");
		((FuzzyClusteringUI)getUserMenu()).panel.validate();
		((FuzzyClusteringUI)getUserMenu()).panel.repaint();*/
	}
	public static void RechangePanels(JPanel panelNew) {
		MainSetUp.getClientGUI().remove(MainSetUp.getUserMenu());
		MainSetUp.setUserMenu(panelNew);
		MainSetUp.getClientGUI().add(MainSetUp.getUserMenu());
		MainSetUp.getUserMenu().revalidate();
		MainSetUp.getUserMenu().repaint();
	}
	public static void doTheSearchProcess(int time,double perc,double delta,boolean dynamic) {
		//PreProccessing
		ToolbarDisEnb(false);
		display("Setting up low high time matrix");
		setLow_highTime(SelectiveChoice.SetRangeValuesOfTime(getWorkSystem()));
		display("Preparing our regresions!");
		if(dynamic)
			setStaticChoice(SelectiveChoice.static_Choice(getWorkSystem(), time, perc, delta,getLow_highTime()));
		else
			setStaticChoice(SelectiveChoice.static_Choice(getWorkSystem(), time, perc, delta,getLow_highTime()));
		display("Moving our Dictoinary");
		setDirectory(getWorkSystem().getDirectory());
		display("Freeing up some space");
		//Save IMPORTANT
		//setWorkSystem(null);//Save First
		//setLoadedVectors(null);
		//IMPORTANT Save
		display("Converting time vector");
		SelectiveChoice.convertingTime(getStaticChoice());
		display("Ready the search party!");
		setReadyParty(Pareto_operations.readyTheSearchParty(getBugfix(),getStaticChoice()));
		display("Run pareto!");
		setReadyParty(Pareto_operations.pareto(getReadyParty(),getStaticChoice()));
		display("Write results to a file");
		writeResults(getReadyParty(),getStaticChoice());
		SaveSearchToHistory(getSetEnd(),getStaticChoice());
		ToolbarDisEnb(true);
		//====================================
	}
	private static void SaveSearchToHistory(ArrayList<ArrayList<Integer>> set,S_selectiveVectors choice) {
		String timedate =  new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new java.util.Date());
		//display(timedate);
		File folder=new File("Results/"+getUsername());
		if(!folder.exists()) {
			folder.mkdirs();
			display("Directory created for "+getUsername());
		}
		try (PrintWriter out = new PrintWriter("Results/"+getUsername()+"/"+getUsername()+"-"+timedate+".txt")) {
			int i=1;
			out.println("Clusters Numbers: "+choice.getSelectList().size());
			for(ArrayList<Integer> st:set) {
				out.println("Cluster Number "+i);
				out.print("Vectors: {");
				for(Integer vec:st) {
					out.print(MainSetUp.getStaticChoice().getClusterSelectives(i-1).get(vec).getID()+",");
				}
				out.println("}");
				i++;
			}
			display("File Created Successfully!");
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			display("Error:File Creation failed!");
			}
	}
	private static void writeResults(ArrayList<P_searchVector> ps,S_selectiveVectors choice) {
		//SetClusters Ready
		ArrayList<ArrayList<Integer>> set=new ArrayList<ArrayList<Integer>>(choice.getSelectList().size());
		for(int j=0;j<choice.getSelectList().size();j++)
			set.add(new ArrayList<Integer>());
		for(P_searchVector s:ps) {
			set.get(s.getIndex_x()).add(s.getIndex_y());
		}
		setSetEnd(set);
		try (PrintWriter out = new PrintWriter("Tests/Bugfix/Result.txt")) {
			int i=1;
			out.println("Clusters Numbers: "+choice.getSelectList().size());
			for(ArrayList<Integer> st:set) {
				out.println("Cluster Number "+i);
				out.print("Vectors: {");
				for(Integer vec:st) {
					out.print(MainSetUp.getStaticChoice().getClusterSelectives(i-1).get(vec).getID()+",");
				}
				out.println("}");
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void LoadUpBugfix() {
		File file = new File("Tests/Bugfix/1_62.txt");
		File file2=new File("Tests/Bugfix/BTimes.txt");
		
		if(file.exists()&& !file.isDirectory()) {
			BufferedReader br,Timebr;
			try {
				Timebr=new BufferedReader(new FileReader(file2));
				br = new BufferedReader(new FileReader(file));
				try {
						String folder,testNm="";
						String TimeFolder = Timebr.readLine();
						testNm=TimeFolder.substring(0, TimeFolder.indexOf(':')-1);
						String vecTime = TimeFolder.substring(TimeFolder.indexOf(':')+2, TimeFolder.length());
						U_vector tmp = new U_vector(Integer.parseInt(vecTime),testNm);
						while((folder=br.readLine())!=null) {
							tmp.addVectorElement(folder);				
						}
						MainSetUp.setBugfix(tmp);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	public static Rectangle getBounds() {
		return getClientGUI().getBounds();
	}
	public static JFrame getClientGUI() {
		return clientGUI;
	}
	public static void setClientGUI(JFrame clientGUI) {
		MainSetUp.clientGUI = clientGUI;
	}
	public static FuzzyCMeansVar getWorkSystem() {
		return workSystem;
	}
	public static void setWorkSystem(FuzzyCMeansVar workSystem) {
		MainSetUp.workSystem = workSystem;
	}
	public static JPanel getUserMenu() {
		return userMenu;
	}
	public static void setUserMenu(JPanel userMenu) {
		MainSetUp.userMenu = userMenu;
	}
	public static StartUp getLoadedVectors() {
		return loadedVectors;
	}
	public static void setLoadedVectors(StartUp loadedVectors) {
		MainSetUp.loadedVectors = loadedVectors;
	}
	public static JPanel getRemoveJpanel() {
		return removeJpanel;
	}
	public static void setRemoveJpanel(JPanel removeJpanel) {
		MainSetUp.removeJpanel = removeJpanel;
	}
	public static S_selectiveVectors getStaticChoice() {
		return staticChoice;
	}
	public static void setStaticChoice(S_selectiveVectors staticChoice) {
		MainSetUp.staticChoice = staticChoice;
	}
	public static LZW getDirectory() {
		return directory;
	}
	public static void setDirectory(LZW directory) {
		MainSetUp.directory = directory;
	}
	public static ArrayList<P_searchVector> getReadyParty() {
		return readyParty;
	}
	public static void setReadyParty(ArrayList<P_searchVector> readyParty) {
		MainSetUp.readyParty = readyParty;
	}
	public static U_vector getBugfix() {
		return Bugfix;
	}
	public static void setBugfix(U_vector bugfix) {
		Bugfix = bugfix;
	}
	public static int[][] getLow_highTime() {
		return low_highTime;
	}
	public static void setLow_highTime(int[][] low_highTime) {
		MainSetUp.low_highTime = low_highTime;
	}
	public static ArrayList<ArrayList<Integer>> getSetEnd() {
		return setEnd;
	}
	public static void setSetEnd(ArrayList<ArrayList<Integer>> setEnd) {
		MainSetUp.setEnd = setEnd;
	}
	public static boolean isFirsttime() {
		return firsttime;
	}
	public static void setFirsttime(boolean firsttime) {
		MainSetUp.firsttime = firsttime;
	}
	public static boolean isDoneCluster() {
		return doneCluster;
	}
	public static void setDoneCluster(boolean doneCluster) {
		MainSetUp.doneCluster = doneCluster;
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		MainSetUp.username = username;
	}
}
