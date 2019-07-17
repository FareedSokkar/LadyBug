package UserUI;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;

import WorkSpace.MainSetUp;

import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class SearchUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JSlider slider_1 ;
	private JSlider slider ;
	private JSlider slider_2;
	private static boolean dynamic=false;
	private JLabel labelx;

	/**
	 * Create the panel.
	 */
	public SearchUI() {
		setBackground(Color.BLACK);

		setBounds(0,30,794,500);
		setLayout(null);
		JLabel lblSec = new JLabel("50 sec");
		lblSec.setForeground(Color.RED);
		lblSec.setBounds(272, 120, 53, 14);
		add(lblSec);
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String st=null;;
				switch(comboBox.getSelectedItem().toString()) {
				case "Seconds":st=" sec";break;
				case "Minutes":st=" min";break;
				case "Hours":st=" hr";break;
				}
				lblSec.setText(Integer.toString(slider_2.getValue())+st);
			}
		});
		comboBox.setBackground(Color.BLACK);
		comboBox.setForeground(Color.WHITE);
		
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Seconds", "Minutes", "Hours"}));
		comboBox.setBounds(625, 117, 83, 20);
		add(comboBox);
		
		ImageIcon mg=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/perc.png")),25,25);
		JLabel lbl = new JLabel(mg);
		lbl.setBounds(105, 151, 25,25);
		add(lbl);
		JLabel label = new JLabel("Percent of vectors:");
		label.setForeground(Color.WHITE);
		label.setBounds(132, 156, 130, 14);
		add(label);
		
		JLabel label_1 = new JLabel("70%");
		label_1.setForeground(Color.RED);
		label_1.setBounds(272, 156, 53, 14);
		add(label_1);
		
		JLabel label_2 = new JLabel("0.1%");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(326, 157, 42, 14);
		add(label_2);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				label_1.setText(Double.toString(slider.getValue()/10.0)+"%");
			}
		});
		slider.setMinimum(1);
		slider.setMaximum(1000);
		slider.setForeground(Color.WHITE);
		slider.setBackground(Color.BLACK);
		slider.setBounds(367, 151, 200, 26);
		add(slider);
		
		JLabel label_3 = new JLabel("100%");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(572, 157, 43, 14);
		add(label_3);
		
		ImageIcon mg2=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/delta.png")),25,25);
		JLabel lbl2 = new JLabel(mg2);
		lbl2.setBounds(105, 187, 25,25);
		add(lbl2);
		JLabel label_4 = new JLabel("Converage (Delta):");
		label_4.setForeground(Color.WHITE);
		label_4.setBounds(132, 192, 130, 14);
		add(label_4);
		
		JLabel label_5 = new JLabel("5%");
		label_5.setForeground(Color.RED);
		label_5.setBounds(272, 192, 53, 14);
		add(label_5);
		
		JLabel label_6 = new JLabel("0.1%");
		label_6.setForeground(Color.WHITE);
		label_6.setBounds(328, 192, 42, 14);
		add(label_6);
		
		slider_1 = new JSlider();
		slider_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				label_5.setText(Double.toString(slider_1.getValue()/10.0)+"%");
			}
		});
		slider_1.setMinimum(1);
		slider_1.setMaximum(1000);
		slider_1.setForeground(Color.WHITE);
		slider_1.setBackground(Color.BLACK);
		slider_1.setBounds(368, 186, 200, 25);
		add(slider_1);
		
		JLabel label_7 = new JLabel("100%");
		label_7.setForeground(Color.WHITE);
		label_7.setBounds(574, 192, 41, 14);
		add(label_7);
		
		ImageIcon mg3=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/run.png")),25,25);
		JLabel lbl3 = new JLabel(mg3);
		lbl3.setBounds(105, 115, 25,25);
		add(lbl3);
		JLabel label_8 = new JLabel("Run time:");
		label_8.setForeground(Color.WHITE);
		label_8.setBounds(132, 120, 130, 14);
		add(label_8);
		
		JLabel label_9 = new JLabel("10");
		label_9.setForeground(Color.WHITE);
		label_9.setBounds(326, 120, 42, 14);
		add(label_9);
		
		JLabel label_10 = new JLabel("1000");
		label_10.setForeground(Color.WHITE);
		label_10.setBounds(572, 120, 43, 14);
		add(label_10);
		
		
		
		slider_2 = new JSlider();
		slider_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				String st=null;;
				switch(comboBox.getSelectedItem().toString()) {
				case "Seconds":st=" sec";break;
				case "Minutes":st=" min";break;
				case "Hours":st=" hr";break;
				}
				lblSec.setText(Integer.toString(slider_2.getValue())+st);
			}
		});
		slider_2.setMinimum(10);
		slider_2.setMaximum(1000);
		slider_2.setForeground(Color.WHITE);
		slider_2.setBackground(Color.BLACK);
		slider_2.setBounds(367, 114, 200, 26);
		add(slider_2);
		
		JLabel lblDynamic = new JLabel("The bugfix is loaded.");
		lblDynamic.setHorizontalAlignment(SwingConstants.CENTER);
		lblDynamic.setForeground(Color.GREEN);
		lblDynamic.setBounds(177, 226, 445, 14);
		lblDynamic.setVisible(isDynamic());
		add(lblDynamic);
		
		//Imag BTN
				ImageIcon btnmg2=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/search.png")),25,25);
				
				JPanel panel = new JPanel();
				panel.setBackground(Color.BLACK);
				panel.setBounds(338, 244, 112, 25);
				add(panel);
				panel.setLayout(null);
				JButton btnProcceed = new JButton("Search");
				JButton btnSearchProcessimg=new JButton(btnmg2);
				btnSearchProcessimg.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						label_1.setForeground(Color.GREEN);
						label_5.setForeground(Color.GREEN);
						lblSec.setForeground(Color.GREEN);
						comboBox.setEnabled(false);
						clickpareto(btnSearchProcessimg,btnProcceed);
					}
				});
				btnSearchProcessimg.setBounds(0, 0, 25, 25);
				panel.add(btnSearchProcessimg);
				btnSearchProcessimg.setBorder(BorderFactory.createEmptyBorder());
				btnSearchProcessimg.setContentAreaFilled(false);
				btnProcceed.setBackground(Color.BLACK);
				btnProcceed.setBounds(35, 2, 77, 23);
				panel.add(btnProcceed);
				btnProcceed.setBorder(BorderFactory.createEmptyBorder());
				btnProcceed.setContentAreaFilled(false);
				btnProcceed.setForeground(Color.WHITE);
				btnProcceed.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						label_1.setForeground(Color.GREEN);
						label_5.setForeground(Color.GREEN);
						lblSec.setForeground(Color.GREEN);
						comboBox.setEnabled(false);
						clickpareto(btnSearchProcessimg,btnProcceed);
					}
				});
				
				JLabel lblParetoSearch = new JLabel("Pareto Search");
				lblParetoSearch.setHorizontalAlignment(SwingConstants.CENTER);
				lblParetoSearch.setForeground(Color.WHITE);
				lblParetoSearch.setFont(new Font("Tahoma", Font.BOLD, 27));
				lblParetoSearch.setBounds(242, 46, 309, 33);
				add(lblParetoSearch);
		
				ImageIcon imageIcon = new ImageIcon(MainSetUp.class.getResource("/res/Giphy/g1.gif"));
				//imageIcon=new ImageIcon(imageIcon.getImage().getScaledInstance(98, 134, BufferedImage.TYPE_INT_RGB));
				labelx = new JLabel(imageIcon);
				//labelx.setIcon(imageIcon);
				labelx.setBounds(325, 290,142, 142);
				labelx.setVisible(false);
				add(labelx);
				
				
		//===Keep At the end===================================
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.setIcon(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/BG.png")));
				lblNewLabel.setBounds(0, 0, 794, 561);
				add(lblNewLabel);
		
	}
	private void clickpareto(JButton btnSearchProcessimg,JButton btnProcceed) {
		slider.setEnabled(false);
		slider_1.setEnabled(false);
		slider_2.setEnabled(false);
		btnSearchProcessimg.setEnabled(false);
		btnProcceed.setEnabled(false);
		labelx.setVisible(true);
		Thread thread = new Thread(){public void run(){
			int time=slider_2.getValue();
			switch(comboBox.getSelectedItem().toString()) {
			case "Seconds":break;
			case "Minutes":time=time*60;break;
			case "Hours":time=time*3600;break;
			}
			double delta=slider_1.getValue()/1000.0;
			double perc=slider.getValue()/100.0;
			MainSetUp.doTheSearchProcess(time, perc, delta, dynamic);
			//==========================================================
			int j=1;
			while(MainSetUp.getSetEnd().get(j-1).size()==0) {
				j++;
			}
			MainSetUp.RechangePanels(new ResultsUI());
			 for(int i=1;i<=MainSetUp.getSetEnd().size();i++) {
				 if(MainSetUp.getSetEnd().get(i-1).size()!=0) 
					 ResultsUI.setNum(i);
			 }
			 for(int i=1;i<=MainSetUp.getSetEnd().get(j-1).size();i++) {
				 ResultsUI.display(Integer.toString(i));
			 }
			 ResultsUI.selectFirst();
			labelx.setVisible(false);

			}
		};
		thread.start();
	}
	public static boolean isDynamic() {
		return dynamic;
	}
	public static void setDynamic(boolean dynamic) {
		SearchUI.dynamic = dynamic;
	}
}
