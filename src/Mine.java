import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

/**
 * mine class
 * @author Moon
 *
 */
public class Mine extends JButton{
	boolean isMine;
	boolean isFlag ;
	boolean isSwept;
	int num;
	static ImageIcon icon = new ImageIcon("flag.gif");
	
	/**
	 * isNotMine isNotFlag isNotSwept set as default
	 */
	public Mine(){
		super();
		isMine = false;
		isFlag = false;
		isSwept = false;
		num =0;
	}
	
	
	/**
	 * set color according to the number 
	 * and set font bold
	 */
	public void setFontAndColor(){
		
		switch(num){
		case 1: this.setForeground(Color.blue); break;
		case 2: this.setForeground(Color.GREEN); break;
		case 3: this.setForeground(Color.RED); break;
		case 4: this.setForeground(Color.orange); break;
		case 5: this.setForeground(Color.yellow); break;
		case 6: this.setForeground(Color.pink); break;
		case 7: this.setForeground(Color.cyan); break;
		case 8: this.setForeground(Color.black); break;
		}
		Font numFont= new Font("Cambria", Font.BOLD , 15);
		setFont(numFont);
		
	}
	
	
	/**
	 * sweep single mine
	 */
	
	public void LeftClick(){
		if(!isSwept&&!isFlag)
			{
			isSwept=true;
			if(num!=0){
				this.setText(num+"");
				this.setBackground(Color.lightGray);
			}
			else	
				this.setBackground(Color.lightGray);
		}		
	}
	
	/**
	 * set flag
	 */
	public  void RightClick(){
		if(!isSwept){
			if(isFlag){
				isFlag=false;
				MainFrame.flagsNumber++;
				this.setText("");
				this.setFontAndColor();
			}
			else{
				isFlag=true;
				MainFrame.flagsNumber--;
				this.setText("¡Ì");
				this.setForeground(Color.red);
			}
		}
		
	}
	
}
