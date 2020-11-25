package snakeGame;

import javax.swing.JFrame;


public class Main {
	public static void main (String [] args) {
		JFrame obj = new JFrame();
		SnakeGame snakePlay = new SnakeGame();
		obj.setBounds(0,10,700,600);
		obj.setTitle("Snakes & Ladders");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(snakePlay);
	}
}
