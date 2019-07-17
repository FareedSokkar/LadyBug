package UserUI;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import WorkSpace.MainSetUp;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class LOGINUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	private JTextField txtUser;
	private JPasswordField passwordField;

	
	public LOGINUI() {
		getContentPane().setBackground(Color.BLACK);
		setResizable(false);
		setBounds(0, 0, 800, 630);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		getContentPane().setLayout(null);
		//USER
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(194, 276, 120, 25);
		getContentPane().add(panel);
		panel.setLayout(null);
		JLabel userlbl=new JLabel(MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/user.png")),25,25));
		userlbl.setBounds(0, 0, 25, 25);
		panel.add(userlbl);
		JLabel lblUser = new JLabel("USER:");
		lblUser.setBounds(25, 0, 95, 25);
		panel.add(lblUser);
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUser.setForeground(Color.WHITE);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(194, 312, 120, 25);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		//PASS
		JLabel passlbl=new JLabel(MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/pass.png")),25,25));
		passlbl.setBounds(0, 0, 25, 25);
		panel_1.add(passlbl);
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(25, 0, 95, 25);
		panel_1.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setForeground(Color.WHITE);
		//==================================================
		JLabel locklbl=new JLabel(MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/logo.png")),100,100));
		locklbl.setBounds(353, 120, 100, 100);
		getContentPane().add(locklbl);
		
		txtUser = new JTextField();
		txtUser.setEditable(false);
		txtUser.setEnabled(false);
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUser.setText("USER");
		txtUser.setForeground(Color.WHITE);
		txtUser.setBackground(Color.BLACK);
		txtUser.setBounds(324, 276, 282, 25);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setEditable(false);
		passwordField.setEnabled(false);
		passwordField.setText("USER");
		passwordField.setBackground(Color.BLACK);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setForeground(Color.WHITE);
		passwordField.setBounds(324, 312, 282, 20);
		getContentPane().add(passwordField);
		//==================================================
		//Search Buttons=========================================
		ImageIcon btnmg2=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/login.png")),25,25);
		JPanel btnpanel = new JPanel();
		btnpanel.setBorder(new LineBorder(Color.WHITE, 1, true));
		btnpanel.setBackground(Color.BLACK);
		btnpanel.setBounds(324, 383, 176, 25);
		getContentPane().add(btnpanel);
		btnpanel.setLayout(null);
		JButton btnloginimg=new JButton(btnmg2);
		btnloginimg.setBounds(0, 0, 25, 25);
		btnpanel.add(btnloginimg);
		btnloginimg.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if((txtUser.getText()).equals("USER")) {
							MainSetUp.LoadUpScreen();
						}else {
							
						}
					}
				});
		btnloginimg.setBorder(BorderFactory.createEmptyBorder());
		btnloginimg.setContentAreaFilled(false);
		JButton btnlogin = new JButton("Login");
		btnlogin.setBounds(26, 0, 150, 23);
		btnpanel.add(btnlogin);
		btnlogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if((txtUser.getText()).equals("USER")) {
							MainSetUp.LoadUpScreen();
							MainSetUp.setUsername(txtUser.getText());
						}else {
							
						}
					}
				});
		btnlogin.setForeground(Color.WHITE);
		btnlogin.setBorder(BorderFactory.createEmptyBorder());
		btnlogin.setContentAreaFilled(false);
		//==================================================
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/BG.png")));
		lblNewLabel.setBounds(0, 0, 794, 561);
		getContentPane().add(lblNewLabel);
		
	}
}
