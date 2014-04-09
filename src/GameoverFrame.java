import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * show when finished a game
 * @author Moon
 *
 */
public class GameoverFrame extends JFrame implements ActionListener{
	
	JButton exit;
	JButton playAgain;
	JLabel result;
	JLabel moneyGained;
	JLabel scoreGained;
	JLabel timeUsed;
	MainFrame main;
	
	/**
	 * set the labels according to the result of the game
	 * @param win
	 * @param level
	 * @param money
	 * @param score
	 * @param time
	 * @param m
	 */
	public GameoverFrame(boolean win, int level , int money, int score, int time, MainFrame m){
		super();
		main=m;
		if(win){
			setTitle("Game won");
			result = new JLabel("      Congratulations! You win !    ");
		}
		else{
			setTitle("Game lost");
			result = new JLabel("      You lose. Better luck next time !    ");
		}
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400,200);
		this.setLocation(200, 200);
		setResizable(false);
		
		moneyGained = new JLabel("      Money gained:  "+money);
		scoreGained = new JLabel("      Score gained:  "+score);
		timeUsed =    new JLabel("      Time used:     "+time);
		
		exit = new JButton("Exit");
		playAgain = new JButton("Play again");
		exit.addActionListener(this);
		playAgain.addActionListener(this);
		
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(5,1,0,10));
		
		pane.add(result);
		pane.add(moneyGained);
		pane.add(scoreGained);
		pane.add(timeUsed);
		
		JPanel buttonPane = new JPanel();
		buttonPane.add(exit);
		buttonPane.add(playAgain);
		
		pane.add(buttonPane);
		
		setContentPane(pane);
		pack();
		setVisible(true);		
	}
	
	
	public void actionPerformed(ActionEvent ae){//unfinished
		Object src= ae.getSource();
		if(src==exit)
			System.exit(0);
		if(src==playAgain){
			main.dispose();
			main= new MainFrame();
			this.dispose();
		}
		
	}

}
