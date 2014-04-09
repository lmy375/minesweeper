import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class HelpFrame extends JFrame implements ActionListener{
	JButton close;
	JTextArea helpContent;
	public HelpFrame(){
		super("Help");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(300, 400);
		helpContent = new JTextArea(20,20);
		helpContent.setText("Help:\n\n" +
				"\n  1.MenuBar" +
				"\n  (1)Game" +
				"\n      1)New Game" +
				"\n            Start a new game." +
				"\n      2)Statistics" +
				"\n            Show the statistics of your record." +
				"\n      3)Options" +
				"\n            Show option frame for you to change the " +
				"\n            setting of the game." +
				"\n      4)Shop" +
				"\n            Buy bombs." +
				"\n      5)Exit" +
				"\n            Exit the game." +
				"\n  (2)Help" +
				"\n      1)View help" +
				"\n            Show the help." +
				"\n      2)About" +
				"\n            Show information of the game." +
				"\n      3)Show Start Messgae" +
				"\n            Show about frame when start.\n\n" +
				"\n  2.Main pane of game" +
				"\n    1)Flags left and time used is showed on the" +
				"\n      left below the pane." +
				"\n    2)Left click to sweep the block." +
				"\n    3)Scoll click to sweep around 8 blocks." +
				"\n    4)Click buttons on the right below and left" +
				"\n      click the block to use bombs. Use bombs to" +
				"\n      sweep multiple block with setting flag on" +
				"\n      mines automaticly." +
				"\n    5) 4 bombs are offered." +
				"\n        X bomb to sweep X shape blocks." +
				"\n        O bomb to sweep O shape blocks." +
				"\n        I bomb to sweep I shape blocks." +
				"\n        _ bomb to sweep _ shape blocks." +
				"\n    6)You can buy bombs in the shop using money" +
				"\n      gained when finished a game."
				);
		helpContent.setEditable(false);
		close = new JButton("Close");
		close.addActionListener(this);
		
		JPanel pane = new JPanel();
		JScrollPane scroll = new JScrollPane(helpContent, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setLayout(new BorderLayout());
		pane.add("Center", scroll);
		pane.add("South", close);
		setContentPane(pane);
		setResizable(false);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae){
		Object src = ae.getSource();
		if(src==close)
			this.dispose();
	}

}
