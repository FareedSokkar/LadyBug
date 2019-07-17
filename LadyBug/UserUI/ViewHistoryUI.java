package UserUI;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Choice;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import WorkSpace.MainSetUp;

import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ItemEvent;

public class ViewHistoryUI extends JPanel {

	/**
	 * 
	 */
	private static JTextArea serverConsole;
	private static Choice choice;
	private static final long serialVersionUID = 9L;
	private static String[] list;
	/**
	 * Create the panel.
	 */
	public ViewHistoryUI() {
		setBackground(Color.BLACK);
		setBounds(0, 30, 800, 500);
		setLayout(null);
		
		JLabel label = new JLabel(MainSetUp.resizeImage(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/icons/history.png")),25,25));
		label.setBounds(216, 112, 25, 25);
		add(label);
		
		JLabel lblFile = new JLabel("File");
		lblFile.setForeground(Color.WHITE);
		lblFile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblFile.setBounds(245, 118, 66, 14);
		add(lblFile);
		
		choice = new Choice();
		choice.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				loadSelectedFile();
			}
		});
		choice.setForeground(Color.WHITE);
		choice.setBackground(Color.BLACK);
		choice.setBounds(313, 115, 281, 20);
		add(choice);
		
		JLabel lblViewHistory = new JLabel("View History");
		lblViewHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewHistory.setForeground(Color.WHITE);
		lblViewHistory.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblViewHistory.setBounds(248, 54, 309, 33);
		add(lblViewHistory);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new MatteBorder(1, 1, 1, 1,Color.BLACK));
		scrollPane.setBounds(215, 148, 380, 300);
		scrollPane.getVerticalScrollBar().setBackground(Color.BLACK);
		scrollPane.getHorizontalScrollBar().setBackground(Color.BLACK);
		scrollPane.setBackground(Color.BLACK);
		add(scrollPane);
		serverConsole = new JTextArea();
		serverConsole.setForeground(Color.WHITE);
		serverConsole.setBackground(Color.BLACK);
		serverConsole.setBounds(215, 150, 380, 300);
		///contentPane.add(serverConsole);
		scrollPane.setViewportView(serverConsole);
		serverConsole.setEditable(false);
		
		
		//========================
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainSetUp.class.getResource("/res/Giphy/BG.png")));
		lblNewLabel.setBounds(0, 0, 794, 561);
		add(lblNewLabel);

	}
	public static void setchoiceFiles() {
		//choice.removeAll();
		for(String st:getList()) {
			choice.add(st);
		}
		loadSelectedFile();
	}
	private static void loadSelectedFile() {
		File file = new File("Results/"+MainSetUp.getUsername()+"/"+choice.getSelectedItem());
		if(file.exists()&& !file.isDirectory()) {
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				//Start pending all vectors
				String folderTest;
				StringBuilder pn=new StringBuilder();
				while((folderTest=br.readLine())!=null) {
					//Each Line format: P#:##...#
					pn.append(folderTest+"\n");
				}
				br.close();
				serverConsole.setText(null);
				serverConsole.append(pn.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static String[] getList() {
		return list;
	}
	public static void setList(String[] list) {
		ViewHistoryUI.list = list;
	}

}
