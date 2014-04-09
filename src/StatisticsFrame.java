import javax.swing.*;
import java.awt.*;

/**
 * statistic frame 
 * show data of records 
 * @author Moon
 *
 */
public class StatisticsFrame extends JFrame{
	/**
	 * Bomb statistics
	 */
	JLabel numberXBomb;
	JLabel numberOBomb;
	JLabel numberIBomb;
	JLabel number_Bomb;
	
	/**
	 * money & score statistics 
	 */
	
	JLabel money;
	JLabel score;
	JLabel highestScore;
	JLabel highestMoney;
	
	/**
	 * time statistics 
	 */
	JLabel timeBeginner;
	JLabel timeIntermediate;
	JLabel timeAdvanced;
	
		
	/**
	 * set the frame
	 */
	public StatisticsFrame(){
		super("Statistics");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 300);
		
		/**
		 * Bomb pane
		 */
		JPanel bombPane = new JPanel();
		bombPane.setLayout(new GridLayout(2,2));
		bombPane.setBorder(BorderFactory.createTitledBorder("Numbers of Bombs"));
		
		numberXBomb = new JLabel("<X>Bomb: "+UserData.numberXBomb);
		numberOBomb= new JLabel("<O>Bomb: "+UserData.numberOBomb);
		numberIBomb = new JLabel("<I>Bomb: "+UserData.numberIBomb);
		number_Bomb= new JLabel("<_>Bomb: "+UserData.number_Bomb);
		
		bombPane.add(numberXBomb);
		bombPane.add(numberOBomb);
		bombPane.add(numberIBomb);
		bombPane.add(number_Bomb);
		
		/**
		 * Score and money pane
		 */
		JPanel scorePane = new JPanel();
		scorePane.setLayout(new GridLayout(4,1));
		scorePane.setBorder(BorderFactory.createTitledBorder("Score & Money"));
		money = new JLabel("All Money Owned: "+ UserData.money);
		highestMoney = new JLabel("Highest Money Once Gained: "+ UserData.highestMoney);
		score = new JLabel("All Score Gained: "+UserData.score);
		highestScore = new JLabel("Highest Score Once Gained: "+ UserData.highestScore);
		
		scorePane.add(score);
		scorePane.add(highestScore);
		scorePane.add(money);
		scorePane.add(highestMoney);
		
		/**
		 * Time record pane
		 */
		JPanel timePane = new JPanel();
		timePane.setLayout(new GridLayout(3,1));
		timePane.setBorder(BorderFactory.createTitledBorder("Time Record"));
		timeBeginner = new JLabel("Beginner Level: "+ UserData.timeBeginner);
		timeIntermediate = new JLabel("Intermediate Level: "+ UserData.timeIntermediate);
		timeAdvanced = new JLabel("Advanced Level: "+ UserData.timeAdvanced);
		
		timePane.add(timeBeginner);
		timePane.add(timeIntermediate);
		timePane.add(timeAdvanced);
		
		/**
		 * main pane
		 */
		JPanel mainPane = new JPanel();
		mainPane.setLayout(new BorderLayout());
		mainPane.add("North", scorePane);
		mainPane.add("Center", bombPane);
		mainPane.add("South", timePane);
		
		setContentPane(mainPane);
		pack();
		setVisible(true);		
	}
}
