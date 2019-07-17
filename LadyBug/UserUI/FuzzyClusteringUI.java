package UserUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import Preprocessing.FCM_operations;
import WorkSpace.MainSetUp;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class FuzzyClusteringUI extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private JButton btnStart = new JButton("Start");
	//public JPanel panel = new JPanel();
	public JLabel labelx;
	/**
	 * Create the panel.
	 */
	public FuzzyClusteringUI(int x_index,int y_index,int width,int hight) {
		setBackground(Color.BLACK);
		//setResizable(false);
		//setBounds(0, 0, width, hight);
		setBounds(0,30,800,500);
		setLayout(null);
		int S=MainSetUp.getLoadedVectors().get_vectors_data_point().size();
		/*panel.setBounds(0, 0, 800, 561);
		panel.setBackground(new Color(51, 51, 51));
		add(panel);
		panel.setLayout(null);*/
		//Tests Number
		ImageIcon mg=MainSetUp.resizeImage(new ImageIcon(FuzzyClusteringUI.class.getResource("/res/Giphy/icons/upright.png")),25,25);
		JLabel lbl = new JLabel(mg);
		lbl.setBounds(169, 106, 25,25);
		add(lbl);
		JLabel lblTestsNumbers = new JLabel("Tests Numbers:");
		lblTestsNumbers.setForeground(new Color(255, 255, 255));
		lblTestsNumbers.setBounds(199, 111, 99, 14);
		add(lblTestsNumbers);
		//Time
		ImageIcon mg2=MainSetUp.resizeImage(new ImageIcon(FuzzyClusteringUI.class.getResource("/res/Giphy/icons/first.png")),25,25);
		JLabel lbl2 = new JLabel(mg2);
		lbl2.setBounds(170, 141, 25,25);
		add(lbl2);
		JLabel lblFirstTime = new JLabel("First Time:");
		lblFirstTime.setForeground(new Color(255, 255, 255));
		lblFirstTime.setBounds(200, 146, 74, 14);
		add(lblFirstTime);
		//Ready Status
		ImageIcon mg3=MainSetUp.resizeImage(new ImageIcon(FuzzyClusteringUI.class.getResource("/res/Giphy/icons/status.png")),25,25);
		JLabel lbl3 = new JLabel(mg3);
		lbl3.setBounds(415, 140,25,25);
		add(lbl3);
		JLabel lblReadyStatus = new JLabel("Ready Status:");
		lblReadyStatus.setForeground(new Color(255, 255, 255));
		lblReadyStatus.setBounds(445, 145, 93, 14);
		add(lblReadyStatus);
		
		//Button
		JPanel strpanel=new JPanel();
		strpanel.setBackground(Color.BLACK);
		strpanel.setBounds(243, 253, 309, 30);
		strpanel.setLayout(null);
		btnStart.setBackground(new Color(255, 255, 255));
		btnStart.setForeground(Color.WHITE);
		btnStart.setBounds(0,0, 309, 30);
		btnStart.setBorder(BorderFactory.createEmptyBorder());
		btnStart.setContentAreaFilled(false);
		strpanel.add(btnStart);
		JLabel slbl=new JLabel(MainSetUp.resizeImage(new ImageIcon(FuzzyClusteringUI.class.getResource("/res/Giphy/icons/start.png")),30,30));
		slbl.setBounds(0,0, 30, 30);
		strpanel.add(slbl);
		add(strpanel);
		
		JLabel lblfirst = new JLabel("Online");
		lblfirst.setForeground(new Color(255, 255, 255));
		lblfirst.setBounds(548, 145, 102, 14);
		add(lblfirst);
		JLabel lblNum = new JLabel(""+S);
		lblNum.setForeground(new Color(255, 255, 255));
		lblNum.setBounds(308, 111, 96, 14);
		add(lblNum);
		
		JLabel lblready = new JLabel("True");
		lblready.setForeground(new Color(255, 255, 255));
		lblready.setBounds(303, 146, 102, 14);
		add(lblready);
		
		JLabel LBLFVAL = new JLabel("5%");
		LBLFVAL.setForeground(Color.RED);
		LBLFVAL.setBounds(308, 184, 42, 14);
		add(LBLFVAL);
		
		JLabel LBLCVAL = new JLabel("5%");
		LBLCVAL.setForeground(Color.RED);
		LBLCVAL.setBounds(307, 220, 43, 14);
		add(LBLCVAL);
		
		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				LBLFVAL.setText(Double.toString(slider.getValue()/10.0)+"%");
			}
		});
		slider.setForeground(new Color(255, 255, 255));
		slider.setBackground(Color.BLACK);
		slider.setBounds(411, 176, 200, 26);
		add(slider);
		
		
		slider.setMinimum(1);
		slider.setMaximum(1000);
		
		//Fuzzy
		ImageIcon mg4=MainSetUp.resizeImage(new ImageIcon(FuzzyClusteringUI.class.getResource("/res/Giphy/icons/fuz.png")),25,25);
		JLabel lbl4 = new JLabel(mg4);
		lbl4.setBounds(170, 179, 25,25);
		add(lbl4);
		JLabel lblFuzzienessLevel = new JLabel("Fuzzieness Level:");
		lblFuzzienessLevel.setForeground(new Color(255, 255, 255));
		lblFuzzienessLevel.setBounds(201, 184, 104, 14);
		add(lblFuzzienessLevel);
		
		JLabel label = new JLabel("0.1%");
		label.setForeground(new Color(255, 255, 255));
		label.setBounds(370, 182, 42, 14);
		add(label);
		
		JLabel label_1 = new JLabel("100%");
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setBounds(616, 182, 35, 14);
		add(label_1);
		
		JSlider slider_1 = new JSlider();
		slider_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				LBLCVAL.setText(Double.toString(slider_1.getValue()/10.0)+"%");
			}
		});
		slider_1.setMinimum(1);
		slider_1.setMaximum(1000);
		slider_1.setForeground(Color.WHITE);
		slider_1.setBackground(Color.BLACK);
		slider_1.setBounds(412, 211, 200, 25);
		add(slider_1);
		
		JLabel label_2 = new JLabel("0.1%");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(372, 217, 42, 14);
		add(label_2);
		
		JLabel label_3 = new JLabel("100%");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(618, 217, 33, 14);
		add(label_3);
		
		//Converge
		ImageIcon mg5=MainSetUp.resizeImage(new ImageIcon(FuzzyClusteringUI.class.getResource("/res/Giphy/icons/conv.png")),25,25);
		JLabel lbl5 = new JLabel(mg5);
		lbl5.setBounds(172, 214, 25,25);
		add(lbl5);
		JLabel lblConvergeLevel = new JLabel("Converge Level:");
		lblConvergeLevel.setForeground(Color.WHITE);
		lblConvergeLevel.setBounds(202, 219, 98, 14);
		add(lblConvergeLevel);
		
		
		
		ImageIcon imageIcon = new ImageIcon(FuzzyClusteringUI.class.getResource("/res/Giphy/g1.gif"));
		//imageIcon=new ImageIcon(imageIcon.getImage().getScaledInstance(98, 134, BufferedImage.TYPE_INT_RGB));
		labelx = new JLabel(imageIcon);
		//labelx.setIcon(imageIcon);
		labelx.setBounds(325, 290,142, 142);
		labelx.setVisible(false);
		add(labelx);
		
		JLabel lblClusteringTests = new JLabel("Clustering Tests");
		lblClusteringTests.setHorizontalAlignment(SwingConstants.CENTER);
		lblClusteringTests.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblClusteringTests.setForeground(Color.WHITE);
		lblClusteringTests.setBounds(243, 39, 309, 33);
		add(lblClusteringTests);
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FuzzyClusteringUI.class.getResource("/res/Giphy/BG.png")));
		lblNewLabel.setBounds(0, 0, 794, 561);
		add(lblNewLabel);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				MainSetUp.ToolbarDisEnb(false);
				btnStart.setEnabled(false);
				slider.setEnabled(false);
				slider_1.setEnabled(false);
				LBLCVAL.setForeground(Color.GREEN);
				LBLFVAL.setForeground(Color.GREEN);
				MainSetUp.display("Disable Button");
				labelx.setVisible(true);
				 Thread thread = new Thread(){public void run(){
					 FCM_operations.StartLookingForClustersNumber(slider.getValue()/1000.0, slider_1.getValue()/1000.0);
					 labelx.setVisible(false);
					 //MainSetUp.getClientGUI().dispose();
					 MainSetUp.RechangePanels(new ShowClusters());//MainSetUp.setClientGUI(new DoneClusteringUI());
					 //====================================
					 ShowClusters.setNum(MainSetUp.getWorkSystem().getClusters().size());
					 
					 for(Integer vc:MainSetUp.getWorkSystem().getClusters().get(0))
						 ShowClusters.display(Integer.toString(vc));
					 ShowClusters.selectFirst();
					 //=======================================
					 MainSetUp.ToolbarDisEnb(true);
						
					    }
					  };
					  thread.start();
				
			}
		});
	}
}
