import javax.swing.*;
import java.awt.*;
import java.awt.event.*;



/**
 * a welcome frame showed when start or click "about"
 * @author Moon
 *
 */
public class MessageFrame extends JFrame implements ActionListener,ItemListener{
	
	JCheckBox showMessage;
	JLabel version;
	JLabel jre;
	JLabel author;
	JButton ok;
	
	
	public MessageFrame(){
		super("Welcome , "+System.getenv("USERNAME"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationByPlatform(true);
		
		
		version= new JLabel("Version: MineSweeper 1.0 beta\n");
		jre = new JLabel("JRE : JavaSE-1.7\n");
		author= new JLabel("Author:  Moon");
		showMessage = new JCheckBox("Show this when starts");
		showMessage.setSelected(true);
		
		ok = new JButton("OK");
		
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(5,1));
		pane.add(version);
		pane.add(jre);
		pane.add(author);
		pane.add(showMessage);
		pane.add(ok);
		
		showMessage.addItemListener(this);
		ok.addActionListener(this);
		
		setContentPane(pane);
		setVisible(true);		
		pack();
	}
	
	
	public void actionPerformed(ActionEvent ae){
		Object src = ae.getSource();
		if (src==ok) this.dispose();
	}
	/**
	 * set whether to show this when starts next time
	 */
	public void itemStateChanged(ItemEvent ie){
		if(ie.getSource()==showMessage){
			if(ie.getStateChange()==ItemEvent.SELECTED)
				UserData.showMessage=true;
			if(ie.getStateChange()==ItemEvent.DESELECTED)
				UserData.showMessage=false;
		}
	}
}
