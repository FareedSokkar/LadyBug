package UserUI;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import WorkSpace.MainSetUp;
import java.awt.Font;

public class UserMainMenuUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8L;

	/**
	 * Create the panel.
	 */
	public UserMainMenuUI() {
		setBackground(Color.BLACK);
		setBounds(0, 30, 800, 500);
		setLayout(null);
		//Core=========================
		//Lock warning
		JLabel locklbl=new JLabel(MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/lock.png")),50,50));
		locklbl.setBounds(575, 154, 50, 50);
		add(locklbl);
		locklbl.setVisible(false);
		JLabel locklblhis=new JLabel(MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/lock.png")),50,50));
		locklblhis.setBounds(575, 276, 50, 50);
		add(locklblhis);
		locklblhis.setVisible(false);
		JLabel dolbl=new JLabel(MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/do.png")),50,50));
		dolbl.setBounds(575, 215, 50, 50);
		add(dolbl);
		dolbl.setVisible(false);
		//========================
		JPanel kpanel = new JPanel();
		kpanel.setBackground(Color.BLACK);
		kpanel.setBounds(310, 383, 197, 30);
		add(kpanel);
		kpanel.setLayout(null);
		JLabel warninghis=new JLabel("History is Empty!");
		warninghis.setBounds(34, 0, 163, 30);
		kpanel.add(warninghis);
		warninghis.setForeground(Color.RED);
		JLabel hislbl=new JLabel(MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/warning.png")),30,30));
		hislbl.setBounds(0, 0, 30, 30);
		kpanel.add(hislbl);
		hislbl.setVisible(false);
		warninghis.setVisible(false);
		kpanel.setVisible(false);
		//========================
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(310, 348, 197, 30);
		add(panel);
		panel.setLayout(null);
		JLabel warning=new JLabel("Database is not clustered!");
		warning.setBounds(34, 0, 163, 30);
		panel.add(warning);
		warning.setForeground(Color.RED);
		//===========
		//arrow warning
		JLabel arrowlbl=new JLabel(MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/warning.png")),30,30));
		arrowlbl.setBounds(0, 0, 30, 30);
		panel.add(arrowlbl);
		arrowlbl.setVisible(false);
		warning.setVisible(false);
		panel.setVisible(false);
		//===========
		
		ImageIcon btnmg2=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/searchmn.png")),50,50);
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(221, 154, 352, 50);
		add(panel_1);
		panel_1.setLayout(null);
		JButton btnSearchProcess = new JButton("Search Process");
		JButton btnSearchProcessimg=new JButton(btnmg2);
		btnSearchProcessimg.setBounds(0, 0, 50, 50);
		panel_1.add(btnSearchProcessimg);
		btnSearchProcessimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MainSetUp.isDoneCluster()) {
				    MainSetUp.RechangePanels(new SearchChoiceUI());
				}else {
					locklbl.setVisible(true);
					warning.setVisible(true);
					arrowlbl.setVisible(true);
					dolbl.setVisible(true);
					panel.setVisible(true);
					btnSearchProcessimg.setEnabled(false);
					btnSearchProcess.setEnabled(false);
					
				}
				
			}
		});
		btnSearchProcessimg.setBorder(BorderFactory.createEmptyBorder());
		btnSearchProcessimg.setContentAreaFilled(false);
		btnSearchProcess.setFont(new Font("Tahoma", Font.PLAIN, 26));
		btnSearchProcess.setBounds(50, 0, 302, 50);
		panel_1.add(btnSearchProcess);
		btnSearchProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(MainSetUp.isDoneCluster()) {
				    MainSetUp.RechangePanels(new SearchChoiceUI());
				}else {
					locklbl.setVisible(true);
					warning.setVisible(true);
					arrowlbl.setVisible(true);
					dolbl.setVisible(true);
					panel.setVisible(true);
					btnSearchProcessimg.setEnabled(false);
					btnSearchProcess.setEnabled(false);
					
				}
			}
		});
		btnSearchProcess.setForeground(Color.WHITE);
		btnSearchProcess.setBorder(BorderFactory.createEmptyBorder());
		btnSearchProcess.setContentAreaFilled(false);
		//=======================================
		ImageIcon btnmg=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/cluster.png")),50,50);
		JPanel panel_ = new JPanel();
		panel_.setBackground(Color.BLACK);
		panel_.setBounds(221, 215, 352, 50);
		add(panel_);
		panel_.setLayout(null);
		JButton clstimg=new JButton(btnmg);
		clstimg.setBounds(0, 0, 50, 50);
		panel_.add(clstimg);
		clstimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Rectangle x=MainSetUp.getBounds();
				//MainSetUp.RechangePanels(new UserMainMenuUI());
				MainSetUp.RechangePanels(new FuzzyClusteringUI(x.x,x.y,x.width,x.height));
			}
		});
		clstimg.setBorder(BorderFactory.createEmptyBorder());
		clstimg.setContentAreaFilled(false);
		JButton clstbtn = new JButton("Cluster the database");
		clstbtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
		clstbtn.setBounds(50, 0, 302, 50);
		panel_.add(clstbtn);
		clstbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rectangle x=MainSetUp.getBounds();
				//MainSetUp.RechangePanels(new UserMainMenuUI());
				MainSetUp.RechangePanels(new FuzzyClusteringUI(x.x,x.y,x.width,x.height));
				
			}
		});
		clstbtn.setForeground(Color.WHITE);
		clstbtn.setBorder(BorderFactory.createEmptyBorder());
		clstbtn.setContentAreaFilled(false);
		
		//=======================================
				ImageIcon btnmg3=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/history.png")),50,50);
				JPanel panel_2 = new JPanel();
				panel_2.setBackground(Color.BLACK);
				panel_2.setBounds(221, 276, 352, 50);
				add(panel_2);
				panel_2.setLayout(null);
				JButton historyimg=new JButton(btnmg3);
				historyimg.setBounds(0, 0, 50, 50);
				panel_2.add(historyimg);
				JButton historybtn = new JButton("View search history");
				historyimg.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//MainSetUp.display("Results/"+MainSetUp.getUsername());
						File folder=new File("Results/"+MainSetUp.getUsername());
						if(folder.exists() && folder.list().length>0) {
							ViewHistoryUI.setList(folder.list());
							ViewHistoryUI.setchoiceFiles();
							MainSetUp.RechangePanels(new ViewHistoryUI());
							
						}else {
							locklblhis.setVisible(true);
							hislbl.setVisible(true);
							warninghis.setVisible(true);
							kpanel.setVisible(true);
							historyimg.setEnabled(false);
							historybtn.setEnabled(false);
							
						}
						
					}
				});
				historyimg.setBorder(BorderFactory.createEmptyBorder());
				historyimg.setContentAreaFilled(false);
				historybtn.setFont(new Font("Tahoma", Font.PLAIN, 26));
				historybtn.setBounds(50, 0, 302, 50);
				panel_2.add(historybtn);
				historybtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//MainSetUp.display("Results/"+MainSetUp.getUsername());
						File folder=new File("Results/"+MainSetUp.getUsername());
						if(folder.exists() && folder.list().length>0) {
							MainSetUp.RechangePanels(new ViewHistoryUI());
							ViewHistoryUI.setList(folder.list());
							ViewHistoryUI.setchoiceFiles();
						}else {
							locklblhis.setVisible(true);
							hislbl.setVisible(true);
							warninghis.setVisible(true);
							kpanel.setVisible(true);
							historyimg.setEnabled(false);
							historybtn.setEnabled(false);
							
						}
					}
				});
				historybtn.setForeground(Color.WHITE);
				historybtn.setBorder(BorderFactory.createEmptyBorder());
				historybtn.setContentAreaFilled(false);
		
		//Keep Last ======== Background
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/BG.png")));
		lblNewLabel.setBounds(0, 0, 794, 561);
		add(lblNewLabel);
	}

}
