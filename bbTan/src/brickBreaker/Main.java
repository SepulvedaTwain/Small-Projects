package brickBreaker;

import javax.swing.JFrame;

public class Main {
	public static void main (String [] args) {
		JFrame obj = new JFrame();
		GamePlay gamePlay = new GamePlay();
		obj.setBounds(0,10,700,600);
		obj.setTitle("BBTan CopyCat");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
	}

}
