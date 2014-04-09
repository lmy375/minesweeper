
import java.io.*;

import javax.swing.JOptionPane;

/**
 * User data class 
 * @author Moon
 *
 */
public class UserData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5883129466532959879L;
	/**
	 * Mines setting data
	 */
	public final static int BEGINNER =1;
	public final static int INTERMEDIATE =2;
	public final static int ADVANCED= 3;
	public final static int CUSTOM=0;
	
	
	public static int level=BEGINNER;
	public static int height= 9;
	public static int width= 9;
	public static int minesNumber= 10;
	
	/**
	 * statistics data
	 */
	public static int money= 100;
	public static int score= 0;
	public static int highestScore= 0 ;
	public static int highestMoney= 0 ;

	public static int timeBeginner=9999;
	public static int timeIntermediate=9999;
	public static int timeAdvanced=9999;
	
	/**
	 * bomb data
	 */
	public static int numberXBomb=3;
	public static int numberOBomb=3;
	public static int numberIBomb=3;
	public static int number_Bomb=3;	
		
	public static boolean bombAllowed= false;
	
	/**
	 * frame data
	 */
	public static boolean showMessage=true;
	
	/**
	 * save data to file UserData.sav
	 */
	public static void saveData(){
		try{
			FileOutputStream dataFile = new FileOutputStream("Userdata.sav");
			DataOutputStream data = new DataOutputStream(dataFile);
			
			data.writeInt(level);
			data.writeInt(height);			
			data.writeInt(width);
			data.writeInt(minesNumber);
			
			data.writeInt( money);
			data.writeInt(score);
			data.writeInt(highestScore);
			data.writeInt(highestMoney);
			
			data.writeInt(timeBeginner);
			data.writeInt(timeIntermediate);
			data.writeInt(timeAdvanced);
			
			data.writeInt(numberXBomb);
			data.writeInt(numberOBomb);
			data.writeInt(numberIBomb);
			data.writeInt(number_Bomb);
			
			data.writeBoolean(bombAllowed);
			data.writeBoolean(showMessage);
		}
		catch(FileNotFoundException ie){
		//	JOptionPane.showMessageDialog(null, "Error: File not found");
		}
		catch(IOException ie){
		//	JOptionPane.showMessageDialog(null, "Error: IOException");
		}
	}
	
	/**
	 * load data from Userdata.sav
	 */
	public static void loadData(){
		try{
			FileInputStream dataFile = new FileInputStream("Userdata.sav");
			DataInputStream data = new DataInputStream(dataFile);
			level= data.readInt();
			height=data.readInt();			
			width=data.readInt();
			minesNumber=data.readInt();
			
			money=data.readInt( );
			score=data.readInt();
			highestScore=data.readInt();
			highestMoney=data.readInt();
			
			timeBeginner=data.readInt();
			timeIntermediate=data.readInt();
			timeAdvanced=data.readInt();
			
			numberXBomb=data.readInt();
			numberOBomb=data.readInt();
			numberIBomb=data.readInt();
			number_Bomb=data.readInt();
			
			bombAllowed=data.readBoolean();
			showMessage=data.readBoolean();
		}catch(IOException ie){
		 //   JOptionPane.showMessageDialog(null, "Error: IOException");
		}
	}
	

}