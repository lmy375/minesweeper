import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

/**
 * main frame of the game 
 * 
 * the data of block of mines
 * 
 * @author Moon
 *
 */
public class MainFrame extends JFrame implements ActionListener, MouseListener, ItemListener, WindowListener{
	Menu gameMenu;
	Mine[][] mines;
	
	JLabel timer;
	int timerNumber;
	JLabel flags;	
	static int flagsNumber;
	JLabel bombUsing;
	
	
	JButton XBomb;
	JButton OBomb;
	JButton IBomb;
	JButton _Bomb;
	
	int blockLeft;
	boolean win;
	boolean start;
	
	OptionsFrame options;
	StatisticsFrame statistics;
	ShopFrame shop;
	MessageFrame message;
	HelpFrame help;
	
	
	Timer gameTimer;
	
	public int usingBomb;
	
	public static final int usingNoBomb=0;
	public static final int usingXBomb=1;
	public static final int usingOBomb=2;
	public static final int usingIBomb=3;
	public static final int using_Bomb=4;
	
	
	/**
	 * Constructor of MainFrame
	 */
	public MainFrame(){
		super("MineSweeper");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);
		//this.setLocationRelativeTo(null);
		
		/**
		 * set menu bar
		 */
		
		gameMenu = new Menu();
		
		this.setJMenuBar(gameMenu);
		this.addWindowListener(this);
		
		/**
		 * add ActionListener of the GameMenu
		 */
		
		gameMenu.newGame.addActionListener(this);
		gameMenu.Statistics.addActionListener(this);
		gameMenu.shop.addActionListener(this);
		gameMenu.options.addActionListener(this);
		gameMenu.exit.addActionListener(this);
		gameMenu.about.addActionListener(this);
		gameMenu.showMessage.addItemListener(this);
		gameMenu.viewHelp.addActionListener(this);
		
	 /**
	  * Item pane
	  * 
	  * 
	  * 
	  * time and flags pane
	  */
	 		 	
//	 	JPanel timePane = new JPanel();
	// 	timePane.setLayout(new GridLayout(1,2));
	 	timer= new JLabel("Time:0");
	 	flagsNumber = UserData.minesNumber;
	 	flags = new JLabel("Flags:"+flagsNumber);
	// 	timePane.add(timer);
	// 	timePane.add(flags);
	 	
	 	/**
	 	 * bomb button pane
	 	 */
	 	
	 	JPanel bombPane = new JPanel();
	 	bombPane.setLayout(new GridLayout(1,4));
	 	bombUsing = new JLabel("Ready");
	 	XBomb = new JButton("X");
	 	XBomb.setMargin(new Insets(0,0,0,0));
	 	XBomb.addActionListener(this);
	 	OBomb = new JButton("O");
	 	OBomb.setMargin(new Insets(0,0,0,0));
	 	OBomb.addActionListener(this);
	 	IBomb = new JButton("I");
	 	IBomb.setMargin(new Insets(0,0,0,0));
	 	IBomb.addActionListener(this);
	 	_Bomb = new JButton("_");
	 	_Bomb.setMargin(new Insets(0,0,0,0));
	 	_Bomb.addActionListener(this);	 	
	 	
	 	bombPane.add(XBomb);
	 	bombPane.add(OBomb);
	 	bombPane.add(IBomb);
	 	bombPane.add(_Bomb);
	 	
	 	JPanel itemPane= new JPanel();
	 	itemPane.setLayout(new BorderLayout());
	// 	itemPane.add(timePane);]
	 	JPanel westPane = new JPanel();
	 	westPane.setLayout(new GridLayout(1,2));
	 	westPane.add(timer);
	 	westPane.add(flags);
	 	
	 	itemPane.add("West",westPane);
	 	itemPane.add("Center",bombUsing);
	 	itemPane.add("East", bombPane);
	
	 	/**
	 	 * set main pane
	 	 */
	 	
	 	JPanel mainPane = new JPanel();
	 	mainPane.setLayout(new BorderLayout());
	 	mainPane.add("Center",setBlocks());
	 	mainPane.add("South" , itemPane);
		
	 	setSize(UserData.width*25 , UserData.height*25+60);
	 	setContentPane(mainPane);
	// 	setResizable(false);// can't be resized
		setVisible(true);
		
		
		
	}
	
	
	public void setMines(int voidi , int voidj ){
		
		/**
		 * avoid to set mine in position i , j 
		 */
		mines[voidi][voidj].isMine=true;
		
		/**
		 * Make the border which is swept and is not mine 
		 */
		
		for(int i = 0 ;i<UserData.height+2;i++){
			mines[i][0].isMine=false;
			mines[i][0].isSwept=true;
			mines[i][UserData.width+1].isMine=false;
			mines[i][UserData.width+1].isSwept=true;
		}
		
		for(int j=0; j<UserData.width+2;j++){
			mines[0][j].isMine=false;
			mines[0][j].isSwept=true;
			mines[UserData.height+1][j].isMine=false;
			mines[UserData.height+1][j].isSwept=true;			
		}
		
		/**
		 * Set the mines randomly
		 */
		
		for(int n= 0;n< UserData.minesNumber;){
			Random r = new Random();
			int i = r.nextInt(UserData.height)+1;
			int j = r.nextInt(UserData.width)+1;
			if(!mines[i][j].isMine){
				mines[i][j].isMine=true;
				n++;
				continue;
			}
		}
		

		mines[voidi][voidj].isMine=false;
		
		/**
		 * Calculate the number of mines around
		 */
		
		for(int i = 1;i<UserData.height+1;i++)
			for(int j =1;j<UserData.width+1;j++){
				int n=0;				               
		        if(mines[i+1][j].isMine==true) n++;
		        if(mines[i-1][j].isMine==true) n++;
		        if(mines[i][j+1].isMine==true) n++;
		        if(mines[i][j-1].isMine==true) n++;
		        if(mines[i+1][j+1].isMine==true) n++;
		        if(mines[i-1][j-1].isMine==true) n++;
		        if(mines[i-1][j+1].isMine==true) n++;
		        if(mines[i+1][j-1].isMine==true) n++;
		        
		        mines[i][j].num=n;
			}
		
		/**
		 * set color of the font
		 */
		for(int i = 1;i<UserData.height+1;i++)
			for(int j = 1;j<UserData.width+1;j++){
				//mines[i][j].setMargin(new Insets(0,0,0,0));
				mines[i][j].setFontAndColor();
			//	mines[i][j].setBackground(Color.blue);
				//mines[i][j].addMouseListener(this);
				//pane.add(mines[i][j]);
			}
		
		/**
		 * set game timer
		 */

		gameTimer = new Timer();
		gameTimer.schedule(new ShowTime(timer), 0,1000);
		

		
	}
	
	
	/**
	 * setMines according to the UserData
	 * to prepare for setting mines
	 */
	public  JPanel setBlocks(){
		
		blockLeft=UserData.height*UserData.width;
		win = false;
		start= false;
		
		
		JPanel pane = new JPanel();
		pane.setLayout(new GridLayout(UserData.height, UserData.width));
		
		mines = new Mine[UserData.height+2][UserData.width+2];
		
		/**
		 * Define the mines
		 */
		
		for(int i = 0;i<UserData.height+2;i++)
			for(int j = 0;j<UserData.width+2; j++){
				mines[i][j]= new Mine();
			}
		
		
		/**
		 * setMargin of the button
		 * 
		 * add MouseListener
		 * add the mines to pane
		 * 
		 * 
		 */
		
		for(int i = 1;i<UserData.height+1;i++)
			for(int j = 1;j<UserData.width+1;j++){
				mines[i][j].setMargin(new Insets(0,0,0,0));
				//mines[i][j].setFontAndColor();
			//	mines[i][j].setBackground(Color.blue);
				mines[i][j].addMouseListener(this);
				pane.add(mines[i][j]);
			}
		
		/**
		 * return the finished Pane		
		 */
		
		return pane;
	}
	
	
	
	
	/**
	 * sweep the mine 
	 * isMine finish the game
	 * else open it
	 * @param i    
	 * @param j
	 */
	public void sweep(int i , int j){
		if(!mines[i][j].isSwept&&!mines[i][j].isFlag){
			if(mines[i][j].isMine) {
				win=false;
				gameOver();
			}
			else{ 
				mines[i][j].LeftClick();
				blockLeft--;
				if(blockLeft<=UserData.minesNumber){
					win=true;
					gameOver();
				}
				if(mines[i][j].num==0)			
					sweepAround(i,j);
			}
		}
	}
	
	
	/**
	 * sweep 8 mines around mines[i][j]
	 * @param i
	 * @param j
	 */
	
	public void sweepAround(int i ,int j){

		int flag=0;
		if(mines[i+1][j].isFlag==true) flag++;
        if(mines[i-1][j].isFlag==true) flag++;
        if(mines[i][j+1].isFlag==true) flag++;
        if(mines[i][j-1].isFlag==true) flag++;
        if(mines[i+1][j+1].isFlag==true) flag++;
        if(mines[i-1][j-1].isFlag==true) flag++;
        if(mines[i-1][j+1].isFlag==true) flag++;
        if(mines[i+1][j-1].isFlag==true) flag++;
        
        if(mines[i][j].num==flag&&mines[i][j].isSwept){
        	sweep(i+1,j);
        	sweep(i-1,j);
        	sweep(i,j+1);
        	sweep(i,j-1);
        	sweep(i+1,j+1);
        	sweep(i-1,j-1);
        	sweep(i-1,j+1);
        	sweep(i+1,j-1);
        }
     }
	
	/**
	 * set flag on the mine
	 * @param i
	 * @param j
	 */
	
	public void flag(int i , int j ){
		mines[i][j].RightClick();
		flags.setText("Flags: "+flagsNumber );
	}
	
	/**
	 * safe sweep mine
	 * isMine set flag
	 * else open it 
	 * @param i
	 * @param j
	 */
	public void safeSweep(int i , int j){
		if(i>0&&i<UserData.height+1&&j>0&&j<UserData.width+1){
			if(mines[i][j].isMine)
				flag(i,j);
			else
				sweep(i,j);
		}
	}
	
	/**
	 * safe sweep the X shape blocks 
	 * @param i
	 * @param j
	 */
	public void xSweep(int i , int j ){
		if(UserData.numberXBomb>0){
			safeSweep(i,j);
			safeSweep(i-1,j-1);
			safeSweep(i+1,j+1);
			safeSweep(i-1,j+1);
			safeSweep(i+1,j-1);
			usingBomb = usingNoBomb;
			UserData.numberXBomb--;
			bombUsing.setText("Ready");
		}else{
			JOptionPane.showMessageDialog(null, "No XBomb Left !");
			usingBomb= usingNoBomb;
			bombUsing.setText("Ready");
		}
		
	}
	
	/**
	 * safe sweep the O shape blocks 
	 * @param i
	 * @param j
	 */
	public void oSweep(int i , int j ){
		if(UserData.numberOBomb>0){
			
		safeSweep(i, j);
		safeSweep(i-1,j);
		safeSweep(i+1,j);
		safeSweep(i,j-1);
		safeSweep(i,j+1);
		
		usingBomb= usingNoBomb;
		UserData.numberOBomb--;
		bombUsing.setText("Ready");
		}
		else{
			JOptionPane.showMessageDialog(null,"No OBomb Left !");
			usingBomb= usingNoBomb;
			bombUsing.setText("Ready");
		}
	}
	
	/**
	 * safe sweep the _ shape blocks 
	 * @param i
	 * @param j
	 */
	public void _Sweep(int i,int j){
		if(UserData.number_Bomb>0){
		
		safeSweep(i,j);
		safeSweep(i,j-1);
		safeSweep(i,j+1);
		safeSweep(i,j+2);
		safeSweep(i,j-2);
		usingBomb = usingNoBomb;
		UserData.number_Bomb--;
		bombUsing.setText("Ready");
		
		}
		else{
			JOptionPane.showMessageDialog(null, "No _Bomb Left !");
			usingBomb= usingNoBomb;
			bombUsing.setText("Ready");
		}
	}
	/**
	 * safe sweep the I shape blocks 
	 * @param i
	 * @param j
	 */
	public void iSweep(int i , int j ){
		if(UserData.numberIBomb>0){
			safeSweep(i,j);
			safeSweep(i-1,j);
			safeSweep(i-2,j);
			safeSweep(i+1,j);
			safeSweep(i+2,j);
		}
		else{
			JOptionPane.showMessageDialog(null, "No IBomb Left");
			usingBomb= usingNoBomb;
			bombUsing.setText("Ready");
		}
	}
	
	
	/**
	 * new game
	 */
	public void newGame(){
		MainFrame m= new MainFrame();
		this.dispose();
	}
	
	/**
	 * game over
	 */
	public void gameOver(){
		/**
		 * cancel the timer
		 */
		gameTimer.cancel();
		
		/**
		 * reset the blocks according to the game
		 */
		for(int i =1;i<UserData.height+1;i++)
			for(int j=1;j<UserData.width+1;j++){
				mines[i][j].isSwept=true;
				if(mines[i][j].isMine&&!mines[i][j].isFlag)
					{
					mines[i][j].setText("¡Á");
					mines[i][j].setFont(new Font(null, Font.BOLD,25));
					mines[i][j].setForeground(Color.BLACK);
					}
				else if(mines[i][j].isFlag&&!mines[i][j].isMine){
					mines[i][j].setText("¡Á");
					mines[i][j].setFont(new Font(null, Font.BOLD,20));
					mines[i][j].setForeground(Color.orange);
				}
			}
		/**
		 * show Game Over Frame according to Boolean win
		 * calculate the score and money then save to UserData
		 */
		
		if(win){
//			System.out.println("you win");
			
			/**
			 * update the record in UserData
			 */
			
			int level = UserData.level;
			int score = UserData.minesNumber*200/(UserData.height*UserData.width)+UserData.minesNumber;
			int time= ShowTime.currentTime-1;
			int money = UserData.minesNumber;
			
			UserData.score+=score;
			UserData.money+=money;
			
			if(level == UserData.BEGINNER && time<UserData.timeBeginner)
				UserData.timeBeginner=time;
			if(level == UserData.INTERMEDIATE && time<UserData.timeIntermediate)
				UserData.timeIntermediate=time;
			if(level== UserData.ADVANCED && time<UserData.timeAdvanced)
				UserData.timeAdvanced=time;	
			if(money>UserData.highestMoney) 
				UserData.highestMoney=money;
			if(score>UserData.highestScore);
				UserData.highestScore=score;
			
			/**
			 * show the game over frame
			 */
			GameoverFrame g = new GameoverFrame(true,level, money,score,time,this);
		}
		else{
//			System.out.println("you lose");
			int level = UserData.level;
			int score = 0;
			int time = ShowTime.currentTime-1;
			int money=0;
			for(int i = 1;i<UserData.height+1;i++)
				for(int j =1;j<UserData.width+1;j++){
					if(mines[i][j].isMine&&mines[i][j].isFlag)
						money++;
				}
			
			UserData.money+=money;			
			if(money>UserData.highestMoney) 
				UserData.highestMoney=money;
			
			GameoverFrame g = new GameoverFrame(false, level,money, score, time, this);
		}
	}
	
	
	/**
	 * menu event 
	 * and bomb button event
	 */
	public void actionPerformed(ActionEvent ae){
		Object evt = ae.getSource();
		/**
		 * menu
		 */
		if(evt==gameMenu.newGame){
			newGame();			
		}
		if(evt==gameMenu.exit) 
			System.exit(0);
		if(evt==gameMenu.options)
			options = new OptionsFrame(this);
		if(evt==gameMenu.Statistics)
			statistics = new StatisticsFrame();
		if(evt==gameMenu.shop)
			shop = new ShopFrame();
		if(evt==gameMenu.about)
			message = new MessageFrame();
		if(evt==gameMenu.viewHelp)
			help = new HelpFrame();
		/**
		 * bomb
		 */
		if(evt==XBomb) {
			usingBomb = usingXBomb;
			bombUsing.setText("XBomb");
		}
		if(evt==OBomb) {
			usingBomb = usingOBomb;
			bombUsing.setText("OBomb");
		}
		if(evt==IBomb) {
			usingBomb = usingIBomb;
			bombUsing.setText("IBomb");
		}
		if(evt==_Bomb) {
			usingBomb = using_Bomb;
			bombUsing.setText("_Bomb");
		}
		
	}
	
/**
 * mouseAction of the mines
 */
	public void mouseClicked(MouseEvent me){
		
	}
	public void mouseEntered(MouseEvent me){
		
	}
	public void mouseExited(MouseEvent me){
		
	}
	public void mousePressed(MouseEvent me){
		
	}
	public void mouseReleased(MouseEvent me){
		Object src = me.getSource();
		for(int i=1;i<UserData.height+1;i++)
			for(int j = 1;j<UserData.width+1; j++){
				if(src==mines[i][j]){
						/**
						 * Left button of Mouse
						 */
						if (me.getButton()==MouseEvent.BUTTON1){
							/**
							 * when game is on 
							 * sweep according to the bomb
							 */
							if(start)
								switch(usingBomb){
								case usingXBomb: xSweep(i,j); break;
								case usingOBomb: oSweep(i,j); break;
								case usingIBomb: iSweep(i,j); break;
								case using_Bomb: _Sweep(i,j); break;
								case usingNoBomb: sweep(i,j); break;
								}
							/**
							 * when game is not started 
							 * set mines according to the first click position
							 */
							else{
								setMines(i,j);
								start=true;
								switch(usingBomb){
								case usingXBomb: xSweep(i,j); break;
								case usingOBomb: oSweep(i,j); break;
								case usingIBomb: iSweep(i,j); break;
								case using_Bomb: _Sweep(i,j); break;
								default:	 sweep(i,j); break;
								}
							}
						}
						/**
						 * middle button of the mouse
						 */
						if (me.getButton()==MouseEvent.BUTTON2) sweepAround(i,j);
						/**
						 * right button of the mouse
						 */
						if (me.getButton()==MouseEvent.BUTTON3) flag(i,j);
				}
					
			}
	}	
	
	/**
	 * 	item event to set whether to show welcome message
	 */
	public void itemStateChanged(ItemEvent ie){
		Object src = ie.getSource();
		if(src==gameMenu.showMessage){
			if(ie.getStateChange()==ItemEvent.SELECTED)
				UserData.showMessage=true;
			else if(ie.getStateChange()==ItemEvent.DESELECTED)
				UserData.showMessage=false;
		}
	}
	
	
	/**
	 * window event to save user data
	 */
	
	public void windowActivated(WindowEvent we){
	//	System.out.println("Actived");
	}
	public void windowClosed(WindowEvent we){
	//	System.out.println("Closed");
	}
	public void windowClosing(WindowEvent we){
	//	System.out.println("Closing");
		UserData.saveData();
		
	}
	public void windowDeactivated(WindowEvent we){
	//	System.out.println("Deactived");
	}
	public void windowDeiconified(WindowEvent we){
	//	System.out.println("Deiconified");
	}
	public void windowIconified(WindowEvent we){
	//	System.out.println("Iconified");
	}
	public void windowOpened(WindowEvent we){
	//	System.out.println("Opened");
	}

	
	
	


/**
 * the main method
 * @param args
 */
	public static void main(String[] args){
		/**
		 * loadData when start
		 */
		UserData.loadData();
		MainFrame m  = new MainFrame();
		/**
		 * show welcome message
		 */
		if(UserData.showMessage)
			new MessageFrame();
	/*	
	 * 
	 * for test
	 * 
		for(int i = 0 ; i<UserData.height+2;i++)
			for(int j =0 ;j<UserData.width+2; j++){
				if(m.mines[i][j].num==0)
				m.mines[i][j].setText(m.mines[i][j].num+"");
				if(m.mines[i][j].isMine) m.mines[i][j].setText("*");
			}
		m.gameOver();
	*/
	}
}



/**
 * class of timer
 * @author Moon
 *
 */

class ShowTime extends TimerTask{
	static JLabel timer;
	static int currentTime;
	public ShowTime(JLabel j){
		super();
		currentTime =0;
		timer=j;
		
	}
	public void run(){
		timer.setText("Time:"+currentTime);
		currentTime++;
	}
}