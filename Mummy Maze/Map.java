package Maze;
import java.util.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
public class Map {


	private String[] map = new String[10];
	private String[] map2 = new String[10];
	private String[] map3 = new String[10];

	private Image grass, wall, ladder, finish, water;

	public Map(){
		//defines grass and wall and finish images
		ImageIcon img = new ImageIcon("C:\\test\\tutorial\\grass.jpg");
		grass = img.getImage();
		img = new ImageIcon("C:\\test\\tutorial\\wall.jpg");
		wall = img.getImage();
		img = new ImageIcon("C:\\test\\tutorial\\finish.jpg");
		finish = img.getImage();
		img = new ImageIcon("C:\\test\\tutorial\\ladder.jpg");
		ladder = img.getImage();
		img = new ImageIcon("C:\\test\\tutorial\\water.jpg");
		water = img.getImage();
		//methods below to open, read and close file
		openFile();
		openFile2();
		openFile3();
	}
	//get methods
	public Image getGrass(){return grass;}
	public Image getWall(){return wall;}
	public Image getFinish(){return finish;}
	public Image getLadder(){return ladder;}
	public Image getWater(){return water;}
	
	
	public String getMap(int x, int y){
		StringBuffer index = new StringBuffer();
		index.append(map[y].substring(x, x+1 ));
		return index.toString();	
	}//end of getMap

	public String getMap2(int x, int y){
		StringBuffer index = new StringBuffer();
		index.append(map2[y].substring(x, x+1 ));
		return index.toString();	
	}
	
	public String getMap3(int x, int y){
		StringBuffer index = new StringBuffer();
		index.append(map3[y].substring(x, x+1 ));
		return index.toString();	
	}
	
	public void openFile3(){
		try{
			File file = new File("C:\\test\\tutorial\\map3.txt");
			Scanner scan = new Scanner(file);
			while(scan.hasNext() ){
				for(int i = 0; i < map3.length; i++)
					map3[i] = scan.next();
			}
			scan.close();
		}catch (Exception e){System.out.print("Error Loading map");}
	}
	
	public void openFile2(){
		try{
			File file = new File("C:\\test\\tutorial\\map2.txt");
			Scanner scan = new Scanner(file);
			while(scan.hasNext() ){
				for(int i = 0; i < map2.length; i++)
					map2[i] = scan.next();
			}
			scan.close();
		}catch (Exception e){System.out.print("Error Loading map");}
	}
	public void openFile(){
		try{
			File file = new File("C:\\test\\tutorial\\map.txt");
			Scanner scan = new Scanner(file);
			while(scan.hasNext() ){
				for(int i = 0; i < map.length; i++)
					map[i] = scan.next();
			}
			scan.close();
		}catch (Exception e){System.out.print("Error Loading map");}
	}//end of openFile


}//end of class Map