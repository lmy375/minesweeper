import javax.swing.*;

/**
 * menu class 
 * @author Moon
 *
 */
public class Menu extends JMenuBar{
	JMenu game;
	
	JMenuItem newGame;
	JMenuItem Statistics;
	JMenuItem shop;
	JMenuItem options;
	JMenuItem exit;
	
	JMenu help;
	
	JMenuItem viewHelp;
	JMenuItem about;
	JCheckBox showMessage;

	
	public Menu(){
		
	
	game = new JMenu("Game");
	
	newGame = new JMenuItem("New Game");
	Statistics = new JMenuItem("Statistics");
	shop = new JMenuItem("Shop");
	options = new JMenuItem("Options");
	exit = new JMenuItem("Exit");
	
	
	
	game.add(newGame);
	game.addSeparator();
	game.add(Statistics);
	game.addSeparator();
	game.add(options);
	game.add(shop);
	game.addSeparator();
	game.add(exit);
	

	help = new JMenu("Help");
	
	viewHelp = new JMenuItem("View Help");
	about = new JMenuItem("About");
	showMessage = new JCheckBox("Show Start Message");
	if(UserData.showMessage) showMessage.setSelected(true);
	
	help.add(viewHelp);
	help.add(about);
	help.addSeparator();
	help.add(showMessage);
	
	add(game);
	add(help);
	
	
	}
	

}
