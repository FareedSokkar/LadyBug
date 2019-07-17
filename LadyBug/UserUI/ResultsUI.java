package UserUI;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;


import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import Entities.U_vector;
import WorkSpace.MainSetUp;

import java.awt.Choice;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

public class ResultsUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;

	/**
	 * Create the panel.
	 */
	private static JTextArea serverConsole;
	private static Choice choice;
	private static Choice choice_1;
	private static JLabel lblTime,lblID;
	
	public ResultsUI() {
		setBackground(Color.BLACK);
		setBounds(0, 30, 800, 500);
		setLayout(null);
		
		ImageIcon mgx1=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/id.png")),25,25);
		JLabel label = new JLabel(mgx1);
		label.setBounds(415, 89, 25, 25);
		add(label);
		
		JLabel label_1 = new JLabel("Vector ID:");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_1.setBounds(444, 95, 66, 14);
		add(label_1);
		
		lblTime=new JLabel("###");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTime.setBounds(510, 95, 100, 14);
		add(lblTime);
		
		ImageIcon mgx2=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/time.png")),25,25);
		JLabel label_2 = new JLabel(mgx2);
		label_2.setBounds(415, 116, 25, 25);
		add(label_2);
		
		JLabel label_3 = new JLabel("Run time:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		label_3.setBounds(444, 122, 66, 14);
		add(label_3);
		
		lblID=new JLabel("###");
		lblID.setForeground(Color.WHITE);
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblID.setBounds(510, 122, 100, 14);
		add(lblID);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1,Color.BLACK));
		scrollPane.setBounds(415, 148, 190, 300);
		scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		scrollPane.getHorizontalScrollBar().setBackground(Color.BLACK);
		scrollPane.setBackground(Color.BLACK);
		add(scrollPane);
		serverConsole = new JTextArea();
		serverConsole.setForeground(Color.WHITE);
		serverConsole.setBackground(Color.BLACK);
		serverConsole.setBounds(300, 150, 190, 300);
		///contentPane.add(serverConsole);
		scrollPane.setViewportView(serverConsole);
		serverConsole.setEditable(false);

		ImageIcon mg=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/set.png")),25,25);
		JLabel lbl = new JLabel(mg);
		lbl.setBounds(175, 89, 25,25);
		add(lbl);
		
		JLabel lblCluster = new JLabel("Cluster");
		lblCluster.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCluster.setForeground(Color.WHITE);
		lblCluster.setBounds(204, 95, 66, 14);
		add(lblCluster);
		
		choice = new Choice();
		choice.setBackground(Color.BLACK);
		choice.setForeground(Color.WHITE);
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int index=choice.getSelectedIndex();
				
				//ArrayList<Integer> cn=MainSetUp.getSetEnd().get(index);
				choice_1.removeAll();
				for(int i=1;i<=MainSetUp.getSetEnd().get(index).size();i++) {
					choice_1.add(Integer.toString(i));
				 }
				int cl=Integer.parseInt(choice.getSelectedItem());
				int vc=Integer.parseInt(choice_1.getSelectedItem());
				int tmp=MainSetUp.getSetEnd().get(cl-1).get(vc-1);
				U_vector vec=MainSetUp.getStaticChoice().getClusterSelectives(cl-1).get(tmp);
				serverConsole.setText(null);
				for(Object ob:vec.getVec())
					serverConsole.append(ob.toString()+"\n");
				serverConsole.setCaretPosition(0);
				lblTime.removeAll();
				lblTime.setText(Integer.toString(vec.getFactor())+" Seconds");
				lblID.removeAll();
				lblID.setText(vec.getID()+" xFF");
				
			}
		});
		choice.setBounds(272, 92, 93, 20);
		add(choice);
		
		ImageIcon mg2=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/vec.png")),25,25);
		JLabel lbl2 = new JLabel(mg2);
		lbl2.setBounds(175, 116, 25,25);
		add(lbl2);
		
		JLabel lblVector = new JLabel("Vector");
		lblVector.setForeground(Color.WHITE);
		lblVector.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVector.setBounds(204, 122, 66, 14);
		add(lblVector);
		
		choice_1 = new Choice();
		choice_1.setForeground(Color.WHITE);
		choice_1.setBackground(Color.BLACK);
		choice_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int cl=Integer.parseInt(choice.getSelectedItem());
				int vc=Integer.parseInt(choice_1.getSelectedItem());
				int tmp=MainSetUp.getSetEnd().get(cl-1).get(vc-1);
				U_vector vec=MainSetUp.getStaticChoice().getClusterSelectives(cl-1).get(tmp);
				serverConsole.setText(null);
				for(Object ob:vec.getVec())
					serverConsole.append(ob.toString()+"\n");
				serverConsole.setCaretPosition(0);
				lblTime.removeAll();
				lblTime.setText(Integer.toString(vec.getFactor())+" Seconds");
				lblID.removeAll();
				lblID.setText(vec.getID()+" xFF");
			}
		});
		choice_1.setBounds(272, 119, 93, 20);
		add(choice_1);
		//IMAGE BUTTON View Clusters
		ImageIcon btnmg=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/view.png")),25,25);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(188, 186, 176, 25);
		add(panel);
		panel.setLayout(null);
		JButton btnViewClusteredFileimg=new JButton(btnmg);
		btnViewClusteredFileimg.setBounds(0, 0, 25, 25);
		panel.add(btnViewClusteredFileimg);
		btnViewClusteredFileimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File("Tests/Bugfix/Result.txt"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnViewClusteredFileimg.setBorder(BorderFactory.createEmptyBorder());
		btnViewClusteredFileimg.setContentAreaFilled(false);
		
		JButton btnViewClusteredFile = new JButton("View Clustered File");
		btnViewClusteredFile.setBounds(26, 0, 150, 23);
		panel.add(btnViewClusteredFile);
		btnViewClusteredFile.setForeground(Color.WHITE);
		btnViewClusteredFile.setBorder(BorderFactory.createEmptyBorder());
		btnViewClusteredFile.setContentAreaFilled(false);
		btnViewClusteredFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().open(new File("Tests/Bugfix/Result.txt"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//========================================================
		JLabel lblClusteredTests = new JLabel("Search Results");
		lblClusteredTests.setHorizontalAlignment(SwingConstants.CENTER);
		lblClusteredTests.setForeground(Color.WHITE);
		lblClusteredTests.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblClusteredTests.setBounds(244, 30, 309, 33);
		add(lblClusteredTests);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/BG.png")));
		lblNewLabel.setBounds(0, 0, 794, 561);
		add(lblNewLabel);
	}
	public static void selectFirst() {
		choice.select(0);
		choice_1.select(0);
		int cl=Integer.parseInt(choice.getSelectedItem());
		int vc=Integer.parseInt(choice_1.getSelectedItem());
		U_vector vec=MainSetUp.getStaticChoice().getClusterSelectives(cl-1).get(MainSetUp.getSetEnd().get(cl-1).get(vc-1));
		serverConsole.setText(null);
		for(Object ob:vec.getVec())
			serverConsole.append(ob.toString()+"\n");
		serverConsole.setCaretPosition(0);
		lblTime.removeAll();
		lblTime.setText(Integer.toString(vec.getFactor())+" Seconds");
		lblID.removeAll();
		lblID.setText(vec.getID()+" xFF");
	}
	public static void setNum(int nm) {
			choice.add(Integer.toString(nm));
	}
	public static void display(String s)
	{
		choice_1.add(s);
		//serverConsole.setCaretPosition(serverConsole.getDocument().getLength());
	}

}
