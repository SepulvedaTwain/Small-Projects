package twoDRays;

import javax.swing.JFrame;

public class Main {
	public static void main(String [] args) {
		JFrame obj=new JFrame();
		Flashlight flashlight=new Flashlight();
		obj.setBounds(0,10,700,600);
		obj.setTitle("2D Rays");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(flashlight);
	}
	
	
}
