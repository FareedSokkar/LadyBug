package UserUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import WorkSpace.MainSetUp;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class SearchChoiceUI extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	private static JRadioButton rdbtnStatic;
	/**
	 * Create the panel.
	 */
	public SearchChoiceUI() {
		setForeground(Color.WHITE);
		setBounds(0, 30, 800, 500);
		setLayout(null);
		//=====================================================
		ImageIcon mgx1=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/static.png")),80,80);
		JLabel label = new JLabel(mgx1);
		label.setForeground(Color.WHITE);
		label.setBounds(137, 134, 80, 80);
		add(label);
		
		ImageIcon mgx2=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/dynamic.png")),80,80);
		JLabel label2 = new JLabel(mgx2);
		label2.setForeground(Color.WHITE);
		label2.setBounds(137, 279, 80, 80);
		add(label2);
		
		JLabel lblChooseYourMethod = new JLabel("Choose Your Method of Choice.");
		lblChooseYourMethod.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseYourMethod.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblChooseYourMethod.setForeground(Color.WHITE);
		lblChooseYourMethod.setBounds(137, 32, 535, 51);
		add(lblChooseYourMethod);
		
		rdbtnStatic = new JRadioButton("Static");
		rdbtnStatic.setSelected(true);
		rdbtnStatic.setContentAreaFilled(false);
		rdbtnStatic.setForeground(Color.WHITE);
		JRadioButton rdbtnDynamic = new JRadioButton("Dynamic");
		rdbtnDynamic.setContentAreaFilled(false);
		rdbtnDynamic.setForeground(Color.WHITE);
		rdbtnStatic.setBounds(264, 150, 109, 23);
		rdbtnDynamic.setBounds(264, 290, 109, 23);
		ButtonGroup group = new ButtonGroup();
        group.add(rdbtnStatic);
        group.add(rdbtnDynamic);
		add(rdbtnDynamic);
		add(rdbtnStatic);
		
		JLabel lblInTheStatic = new JLabel("In the Static Choice, everything been pre-choosen!");
		lblInTheStatic.setForeground(Color.WHITE);
		lblInTheStatic.setBounds(264, 183, 408, 14);
		add(lblInTheStatic);
		
		JLabel lblInTheDynamic = new JLabel("In The Dynamic strategy it depend on the bugfix!");
		lblInTheDynamic.setForeground(Color.WHITE);
		lblInTheDynamic.setBounds(264, 332, 397, 14);
		add(lblInTheDynamic);
		//Imag BTN
		ImageIcon btnmg2=MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/pro.png")),25,25);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(300, 410, 112, 25);
		add(panel);
		panel.setLayout(null);
		JButton btnSearchProcessimg=new JButton(btnmg2);
		btnSearchProcessimg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainSetUp.LoadUpBugfix();
				switch(getSelectedButtonText(group)) {
				case "Static":
					SearchUI.setDynamic(false);
					break;
				case "Dynamic":
					SearchUI.setDynamic(true);
					break;	
				}
				MainSetUp.RechangePanels(new SearchUI());
				MainSetUp.display(SearchUI.isDynamic()?"Dynamic choice selected":"Static Choice selected");
			}
		});
		btnSearchProcessimg.setBounds(0, 0, 25, 25);
		panel.add(btnSearchProcessimg);
		btnSearchProcessimg.setBorder(BorderFactory.createEmptyBorder());
		btnSearchProcessimg.setContentAreaFilled(false);
		JButton btnProcceed = new JButton("Procceed");
		btnProcceed.setBackground(Color.BLACK);
		btnProcceed.setBounds(35, 2, 77, 23);
		panel.add(btnProcceed);
		btnProcceed.setBorder(BorderFactory.createEmptyBorder());
		btnProcceed.setContentAreaFilled(false);
		btnProcceed.setForeground(Color.WHITE);
		btnProcceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainSetUp.LoadUpBugfix();
				switch(getSelectedButtonText(group)) {
				case "Static":
					SearchUI.setDynamic(false);
					break;
				case "Dynamic":
					SearchUI.setDynamic(true);
					break;	
				}
				MainSetUp.RechangePanels(new SearchUI());
				MainSetUp.display(SearchUI.isDynamic()?"Dynamic choice selected":"Static Choice selected");
			}
		});
		//pack();
		
		
		//===Keep At the end===================================
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/BG.png")));
		lblNewLabel.setBounds(0, 0, 794, 561);
		add(lblNewLabel);
	}
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

}
