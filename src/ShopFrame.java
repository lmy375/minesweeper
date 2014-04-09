import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * shop frame for user to buy bombs 
 * @author Moon
 *
 */
public class ShopFrame extends JFrame implements ActionListener{
	
	/**
	 * price of bombs 
	 */
	final static int priceXBomb = 20;
	final static int priceOBomb = 20;
	final static int priceIBomb = 20;
	final static int price_Bomb = 20;
	
	JLabel money;
	JLabel xBomb;
	JButton buyXBomb;
	JLabel oBomb;
	JButton buyOBomb;
	JLabel iBomb;
	JButton buyIBomb;
	JLabel _Bomb;
	JButton buy_Bomb;

	
	public ShopFrame(){
		super("Shop");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		/**
		 * money pane
		 */
		JPanel mPane = new JPanel();
		money = new JLabel("Your Money:  "+UserData.money);
		mPane.add(money);
		mPane.setBorder(BorderFactory.createTitledBorder("Money"));
		
		
		/**
		 * XBomb pane
		 */
		JPanel xPane= new JPanel();
		xBomb = new JLabel("Owned: "+UserData.numberXBomb+
				" Price: "+priceXBomb);
		buyXBomb = new JButton("Buy One");
		buyXBomb.addActionListener(this);
		xPane.add(xBomb);
		xPane.add(buyXBomb);
		xPane.setBorder(BorderFactory.createTitledBorder("<X>Bomb"));
		
		/**
		 * OBomb pane
		 */
		JPanel oPane= new JPanel();
		oBomb = new JLabel("Owned: "+UserData.numberOBomb+
				" Price: "+priceOBomb);
		buyOBomb = new JButton("Buy One");
		buyOBomb.addActionListener(this);
		oPane.add(oBomb);
		oPane.add(buyOBomb);
		oPane.setBorder(BorderFactory.createTitledBorder("<O>Bomb"));
		
		/**
		 * IBomb pane
		 */
		JPanel iPane= new JPanel();
		iBomb = new JLabel("Owned: "+UserData.numberIBomb+
				" Price: "+priceIBomb);
		buyIBomb = new JButton("Buy One");
		buyIBomb.addActionListener(this);
		iPane.add(iBomb);
		iPane.add(buyIBomb);
		iPane.setBorder(BorderFactory.createTitledBorder("<I>Bomb"));
		
		/**
		 * _Bomb pane
		 */
		JPanel _Pane= new JPanel();
		_Bomb = new JLabel("Owned: "+UserData.number_Bomb+
				" Price: "+price_Bomb);
		buy_Bomb = new JButton("Buy One");
		buy_Bomb.addActionListener(this);
		_Pane.add(_Bomb);
		_Pane.add(buy_Bomb);
		_Pane.setBorder(BorderFactory.createTitledBorder("<_>Bomb"));
		
		/**
		 * main pane
		 */
		JPanel mainPane = new JPanel();
		mainPane.setLayout(new GridLayout(5,1));
		mainPane.add(mPane);
		mainPane.add(xPane);
		mainPane.add(oPane);
		mainPane.add(iPane);
		mainPane.add(_Pane);
		
		setContentPane(mainPane);
		setVisible(true);
		pack();
	}
	
	
	/**
	 * buy bombs 
	 */
	public void actionPerformed(ActionEvent ae){
		Object src= ae.getSource();
		if(src==buyXBomb){
			if(UserData.money<priceXBomb){
				JOptionPane.showMessageDialog(null, "More money required");
			}
			else{
				UserData.money-=priceXBomb;
				UserData.numberXBomb++;
				money.setText("Your Money:  "+UserData.money);
				xBomb.setText("Owned: "+UserData.numberXBomb+
				" Price: "+priceXBomb);
			}
		}
		
		if(src==buyOBomb){
			if(UserData.money<priceOBomb){
				JOptionPane.showMessageDialog(null, "More money required");
			}
			else{
				UserData.money-=priceOBomb;
				UserData.numberOBomb++;
				money.setText("Your Money:  "+UserData.money);
				oBomb.setText("Owned: "+UserData.numberOBomb+
				" Price: "+priceOBomb);
			}
		}
		
		if(src==buyIBomb){
			if(UserData.money<priceIBomb){
				JOptionPane.showMessageDialog(null, "More money required");
			}
			else{
				UserData.money-=priceIBomb;
				UserData.numberIBomb++;
				money.setText("Your Money:  "+UserData.money);
				iBomb.setText("Owned: "+UserData.numberIBomb+
				" Price: "+priceIBomb);
			}
		}
		
		if(src==buy_Bomb){
			if(UserData.money<price_Bomb){
				JOptionPane.showMessageDialog(null, "More money required");
			}
			else{
				UserData.money-=price_Bomb;
				UserData.number_Bomb++;
				money.setText("Your Money:  "+UserData.money);
				_Bomb.setText("Owned: "+UserData.number_Bomb+
				" Price: "+price_Bomb);
			}
		}
		
	}
	
	
	

}
