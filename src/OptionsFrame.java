import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * option frame which shows all the setting
 * @author Moon
 *
 */
public class OptionsFrame extends JFrame implements ActionListener,FocusListener,ItemListener{
	JButton ok;
	JButton cancel;
	
	JRadioButton beginner;
	JRadioButton intermediate;
	JRadioButton advanced;
	JRadioButton custom;
	
	
	JLabel h;
	JLabel w;
	JLabel m;
	JTextField height;
	JTextField width;
	JTextField minesNumber;
	
	int heightInt;
	int widthInt;
	int minesNumberInt;
//	boolean isCustom= false;
	
	SkinButton skinButton[];
	MainFrame main;
	
	public OptionsFrame(MainFrame temp){
		super("Options");
		
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
//		this.setResizable(false);
		main=temp;
		/**
		 * Load data from UserData
		 */
		heightInt=UserData.height;
		widthInt=UserData.width;
		minesNumberInt=UserData.minesNumber;
		
		
		/**
		 * radio pane design
		 * 
		 */
				
		beginner = new JRadioButton("Beginner 9¡Á9 tile grid 10 mines" );
		intermediate = new JRadioButton("Intermediate 16¡Á16 tile grid 40 mines");
		advanced = new JRadioButton("Advanced 16¡Á30 tile grid 99 mines" );
		custom = new JRadioButton("Custom: ");
		
		beginner.addItemListener(this);
		intermediate.addItemListener(this);
		advanced.addItemListener(this);
		custom.addItemListener(this);
		
		JPanel radioPane= new JPanel();
		radioPane.setLayout(new GridLayout(4,1));
		radioPane.add(beginner);
		radioPane.add(intermediate);
		radioPane.add(advanced);
		radioPane.add(custom);

		/**
		 * Custom pane
		 */
		
		h= new JLabel("Height(9-24):");
		height = new JTextField("9");
		height.addFocusListener(this);
		w = new JLabel("Width(9-30):" );
		width = new JTextField("9");
		width.addFocusListener(this);
		m= new JLabel("Mines(10-668):");
		minesNumber = new JTextField("10");
		minesNumber.addFocusListener(this);
		
		h.setEnabled(false);
		height.setEnabled(false);
		w.setEnabled(false);
		width.setEnabled(false);
		m.setEnabled(false);
		minesNumber.setEnabled(false);
		
		
		JPanel customPane = new JPanel();
		customPane.setLayout(new GridLayout(3,2));
		customPane.add(h);
		customPane.add(height);
		customPane.add(w);
		customPane.add(width);
		customPane.add(m);
		customPane.add(minesNumber);
		
		/**
		 * Button group of radio buttons
		 */
		ButtonGroup buttons = new ButtonGroup();
		buttons.add(beginner);
		buttons.add(intermediate);
		buttons.add(advanced);
		buttons.add(custom);
		
		
		
		JPanel difficultyPane = new JPanel();
		difficultyPane.setLayout(new BorderLayout());
		difficultyPane.setBorder(BorderFactory.createTitledBorder("Difficulty"));
		difficultyPane.add("North",radioPane);
		difficultyPane.add("South",customPane);
		
	
		/**
		 * Button pane
		 */
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		ok=new JButton("OK");
		cancel = new JButton("Cancel");
		ok.addActionListener(this);
		cancel.addActionListener(this);
		buttonPane.add(ok);
		buttonPane.add(cancel);
		
		/**
		 * Skin pane
		 */
		UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();
		skinButton = new SkinButton[laf.length];
		JPanel skinPane = new JPanel();
		skinPane.setLayout(new GridLayout(laf.length,1));
		skinPane.setBorder(BorderFactory.createTitledBorder("Themes"));
		for(int i = 0 ;i< laf.length;i++){
			skinButton[i] = new SkinButton(laf[i]);
			skinButton[i].addActionListener(this);
			skinPane.add(skinButton[i]);
		}
		

		
		/**
		 * main pane
		 */
		JPanel mainPane= new JPanel();
		mainPane.setLayout(new BorderLayout());
		mainPane.add("North",skinPane);
		mainPane.add("Center",difficultyPane);
		mainPane.add("South", buttonPane);
		
		
		setContentPane(mainPane);
		pack();
		setVisible(true);

		
		
		
	}
	
	
	/**
	 * Action of ok and cancel buttons
	 */
	public void actionPerformed(ActionEvent ae){
		Object src = ae.getSource();
		if(src==ok){
				/*test
					System.out.println(heightInt+"  "+widthInt+"  "+minesNumberInt);
				*/	
				/**
				 * save the data
				 */
				UserData.height=heightInt;
				UserData.width=widthInt;
				UserData.minesNumber=minesNumberInt;
				main.dispose();
				MainFrame m = new MainFrame();
				this.dispose();
				
		}
		
		/**
		 * cancel the change
		 */
		if(src==cancel) this.dispose();
		
		/**
		 * set look and feel according to the system
		 */
		for(int i = 0 ;i<skinButton.length;i++){
			if(src==skinButton[i]){
				try{
					UIManager.setLookAndFeel(skinButton[i].className);
					SwingUtilities.updateComponentTreeUI(this);
					SwingUtilities.updateComponentTreeUI(main);
					pack();
				}catch(Exception e){
					
				}
			}
		}
	}
	
	
	/**
	 * Item action of radio buttons
	 */
	public void itemStateChanged(ItemEvent ie){
		Object src = ie.getSource();
		
		/**
		 * Custom setting
		 */
		if(src==custom){
			if(ie.getStateChange()==ItemEvent.DESELECTED){
//				isCustom=false;
				h.setEnabled(false);
				height.setEnabled(false);
				w.setEnabled(false);
				width.setEnabled(false);
				m.setEnabled(false);
				minesNumber.setEnabled(false);				
			}
			if(ie.getStateChange()==ItemEvent.SELECTED){
//				isCustom=true;
				UserData.level=UserData.CUSTOM;
				h.setEnabled(true);
				height.setEnabled(true);
				w.setEnabled(true);
				width.setEnabled(true);
				m.setEnabled(true);
				minesNumber.setEnabled(true);
			}
		}
		
		/**
		 * System setting
		 * beginner 9 9 10
		 * intermediate 16 16 40
		 * advanced 16 30 99
		 */
		if(src==beginner&&ie.getStateChange()==ItemEvent.SELECTED){
			UserData.level=UserData.BEGINNER;
			heightInt=9;
			widthInt=9;
			minesNumberInt=10;
		}
		
		if(src==intermediate&&ie.getStateChange()==ItemEvent.SELECTED){
			UserData.level=UserData.INTERMEDIATE;
			heightInt=16;
			widthInt=16;
			minesNumberInt=40;
		}
		
		if(src==advanced&&ie.getStateChange()==ItemEvent.SELECTED){
			UserData.level=UserData.ADVANCED;
			heightInt=16;
			widthInt=30;
			minesNumberInt=99;
		}
		
	}
	
	/**
	 * Focus action of text fields
	 * set custom height, width , minesNumber to UserData
	 * if set number is out of bounds 
	 * change it to default 
	 */
	public void focusGained(FocusEvent fe){
		
	}
	public void focusLost(FocusEvent fe){
		Object src = fe.getSource();
		
		if(src==height) {
			try{
			heightInt = Integer.parseInt(height.getText());
			if(heightInt>24){
				JOptionPane.showMessageDialog(null, 
						new JLabel("Too large number"), "Error", JOptionPane.ERROR_MESSAGE);
				heightInt=24;
				height.setText(heightInt+"");
			}
			if(heightInt<9){
				JOptionPane.showMessageDialog(null, 
						new JLabel("Too small number"), "Error", JOptionPane.ERROR_MESSAGE);
				heightInt=9;
				height.setText(heightInt+"");
				
				}
			}catch(NumberFormatException nfe){
				JOptionPane.showMessageDialog(null, 
						new JLabel("Integer required"), "Error", JOptionPane.ERROR_MESSAGE);
				height.setText(heightInt+"");				
			}
		}
		if(src==width){ 
			try{
				widthInt = Integer.parseInt(width.getText());
				if(widthInt>30){
					JOptionPane.showMessageDialog(null, 
							new JLabel("Too large number"), "Error", JOptionPane.ERROR_MESSAGE);
					widthInt=30;
					width.setText(widthInt+"");
				}
				if(widthInt<9){
					JOptionPane.showMessageDialog(null, 
							new JLabel("Too small number"), "Error", JOptionPane.ERROR_MESSAGE);
					widthInt=9;
					width.setText(widthInt+"");
					
				}
			}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null, 
							new JLabel("Integer required"), "Error", JOptionPane.ERROR_MESSAGE);
					width.setText(widthInt+"");
				}
		}
		if(src==minesNumber){ 
			try{
				minesNumberInt = Integer.parseInt(minesNumber.getText());
				if(minesNumberInt>668){
					minesNumberInt=10;
					JOptionPane.showMessageDialog(null, 
							new JLabel("Too large number"), "Error", JOptionPane.ERROR_MESSAGE);
					minesNumber.setText(minesNumberInt+"");
				}
				if(minesNumberInt<10){
					minesNumberInt=10;
					JOptionPane.showMessageDialog(null, 
							new JLabel("Too small number"), "Error", JOptionPane.ERROR_MESSAGE);
					
					minesNumber.setText(minesNumberInt+"");
					
				}
				if(minesNumberInt>heightInt*widthInt-50){
					minesNumberInt=10;
					JOptionPane.showMessageDialog(null, 
							new JLabel("Mines are too much compared to blocks"), "Error", JOptionPane.ERROR_MESSAGE);
					
					minesNumber.setText(minesNumberInt+"");
				}
			}catch(NumberFormatException nfe){
					JOptionPane.showMessageDialog(null, 
							new JLabel("Integer required"), "Error", JOptionPane.ERROR_MESSAGE);
					minesNumber.setText(minesNumberInt+"");
				}
		} 
	}	

}

/**
 * class skin button 
 * @author Moon
 *
 */
class SkinButton extends JButton{
	public String className;
	public SkinButton(UIManager.LookAndFeelInfo laf){
		super(laf.getName());
		className = laf.getClassName();
	}
}
