package Maze;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Villain {
	private int villainX, villainY;
	private Image villian;

	public Villain(){
	ImageIcon img3 = new ImageIcon("badGuyBlue.png");
	villian = img3.getImage();

	villainX = 7;
	villainY = 4;

	}
	public Villain(String color){
		ImageIcon img3 = new ImageIcon("badGuy" + color + ".png");
		villian = img3.getImage();

		villainX = 9;
		villainY = 8;
	}
	public Image getVillain(){return villian;}//end of get villain
	public int getVillainX(){return villainX;}
	public int getVillainY(){return villainY;}

	public void villainMove(int dx, int dy){
		villainX += dx;
		villainY += dy;
	}//end of villainMove 

	public void setVillain(int x, int y){
		villainX = x;
		villainY = y;
	}
}