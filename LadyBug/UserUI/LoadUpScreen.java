package UserUI;

import java.awt.Color;
import java.awt.Dimension;
//import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import WorkSpace.MainSetUp;
import WorkSpace.StartUp;

public class LoadUpScreen extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextArea serverConsole;
	public JLabel label_1;
	public JPanel p;
	public JButton homeimg;
	public JButton accountimg;
	public JButton setimg;
	
	/**
	 * Create the application.
	 */
	public LoadUpScreen() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 * @throws MalformedURLException 
	 */
	private void initialize() {
		setResizable(false);
		setBounds(0, 0, 800, 630);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		getContentPane().setLayout(null);
		
		//Tool Bar
		JPanel toolpanel = new JPanel();
		toolpanel.setBackground(Color.BLACK);
		toolpanel.setBounds(0,0,794,30);
		toolpanel.setLayout(null);
		getContentPane().add(toolpanel);
		
		//=====left botton buttons - Account , settings and home
		homeimg=new JButton(MainSetUp.resizeImage(new ImageIcon(FuzzyClusteringUI.class.getResource("/res/Giphy/icons/home.png")),30,30));
		homeimg.setBounds(0, 0, 30, 30);
		homeimg.setBorder(BorderFactory.createEmptyBorder());
		homeimg.setContentAreaFilled(false);
		homeimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainSetUp.RechangePanels(new UserMainMenuUI());
			}
		});
		toolpanel.add(homeimg);
		accountimg=new JButton(MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/account.png")),30,30));
		accountimg.setBounds(734, 0, 30, 30);
		accountimg.setBorder(BorderFactory.createEmptyBorder());
		accountimg.setContentAreaFilled(false);
		accountimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		toolpanel.add(accountimg);
		setimg=new JButton(MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/settings.png")),30,30));
		setimg.setBounds(764, 0, 30, 30);
		setimg.setBorder(BorderFactory.createEmptyBorder());
		setimg.setContentAreaFilled(false);
		setimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		toolpanel.add(setimg);
		homeimg.setEnabled(false);
		accountimg.setEnabled(false);
		setimg.setEnabled(false);
		//============
		p=new JPanel();
		p.setBounds(0,30, 800, 500);
		p.setBackground(new Color(0,0,0,0));
		p.setLayout(null);
		JLabel lbl=new JLabel("Setting Up the working space, please wait...");
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		lbl.setBounds(145, 14, 458, 124);
		lbl.setForeground(Color.WHITE);
		p.add(lbl);
		JLabel label = new JLabel(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/giphy2.gif")));
		label.setBounds(225, 50, 350, 438);
		MainSetUp.setUserMenu(p);//settup
		p.add(label);
		JLabel lblWelcomeToLadybug = new JLabel("Welcome to LADYBUG - Fix it yourself");
		lblWelcomeToLadybug.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToLadybug.setForeground(Color.WHITE);
		lblWelcomeToLadybug.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));
		lblWelcomeToLadybug.setBounds(110, 14, 523, 30);
		p.add(lblWelcomeToLadybug);
		
		label_1 = new JLabel("99%");
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 22));
		label_1.setBounds(370, 452, 60, 30);
		p.add(label_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/BG.png")));
		lblNewLabel.setBounds(0, 0, 794, 561);
		p.add(lblNewLabel);
		getContentPane().add(p);
		
		JPanel panel = new JPanel();
		panel.setBounds(0,530,794,70);
		panel.setLayout(null);
		getContentPane().add(panel);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1,Color.BLACK));
		scrollPane.setBounds(0, 0, 794, 70);
		scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		scrollPane.getHorizontalScrollBar().setBackground(Color.BLACK);
		scrollPane.setBackground(Color.BLACK);
		panel.add(scrollPane);
		serverConsole = new JTextArea();
		serverConsole.setForeground(Color.GREEN);
		serverConsole.setBackground(Color.BLACK);
		serverConsole.setBounds(0, 0, 794, 70);
		///contentPane.add(serverConsole);
		scrollPane.setViewportView(serverConsole);
		serverConsole.setEditable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Setting Up EveryThing
		try {
			StartUp startUp=new StartUp();
			MainSetUp.setLoadedVectors(startUp);
			
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//Exist and save Error Log
			MainSetUp.getClientGUI().dispose();
		}
		
		
		
	}

}
