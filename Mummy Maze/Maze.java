package Maze;

import javax.swing.*;

public class Maze {

	public static void main(String[] a){
		new Maze();

	}//end of main

	public Maze(){
		JFrame frame = new JFrame("Maze Game");
		frame.setDefaultCloseOperation(3);
		frame.setSize(350,350);
		frame.add(new Board() );
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}//end of Maze constructor
}//end of class Maze