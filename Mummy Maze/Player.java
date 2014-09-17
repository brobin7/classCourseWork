package Maze;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Player {

	//tile are for collision
	private int tileX, tileY;//, villainX, villainY;
	private Image player, playerFinished;//, villian;

	public Player(){
		//sets up player image
		ImageIcon img = new ImageIcon("C:\\test\\tutorial\\slickback.jpg");
		player = img.getImage();
		ImageIcon img2 = new ImageIcon("playerFinished.png");
		playerFinished = img2.getImage();
		//ImageIcon img3 = new ImageIcon("badGuyBlue.png");
		//villian = img3.getImage();

		//tile starting point
		tileX = 1;
		tileY = 1;
		//villainX = 7;
		//villainY = 4;

	}//end of Player constructor

	//get methods
	public Image getPlayer(){return player;}//end of getPlayer
	public Image getPlayerFinished(){return playerFinished;}//end of getPlayerFinished
	//public Image getVillain(){return villian;}//end of get villain
	public int getTileX(){return tileX;}
	public int getTileY(){return tileY;}
	//public int getVillainX(){return villainX;}
	//public int getVillainY(){return villainY;}

	public void move(int dx, int dy){
		tileX += dx;
		tileY += dy;
	}//end of move 
	/*public void villainMove(int dx, int dy){
		villainX += dx;
		villainY += dy;
	}//end of villainMove 
	
	public void setVillain(int x, int y){
		villainX = x;
		villainY = y;
	}
	public int randomNum(){
		int ranNum = (int)(Math.random() * 4) +1;
		return ranNum;
	}*/
}