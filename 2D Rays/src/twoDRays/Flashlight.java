package twoDRays;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
//import javax.swing.Timer;


public class Flashlight extends JPanel implements KeyListener,MouseListener, MouseMotionListener,ActionListener {
	
	MapGenerator map;
	//Oval light;
	Point p= MouseInfo.getPointerInfo().getLocation();
	
	//private Timer timer;
	
	private boolean linesDrawn=false;
	
	
	public Flashlight() {
		map = new MapGenerator(6);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
	}
	
	
	public void paint (Graphics g) {
		requestFocus(true);
		//Backround
		g.setColor(Color.black);
		g.fillRect(0,0,692,592);
		
		//drawLines
		map.drawLines((Graphics2D)g);
		linesDrawn=true;
		
		//drawLight
		g.setColor(Color.white);
		g.fillOval(p.x,p.y, 5, 5);
		
		//drawRays 
		map.drawRays((Graphics2D)g, p.x, p.y);
		
		
		
	}
	
	public int findC(int x,int y) {
		return -(x+y);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			map= new MapGenerator(6);
			linesDrawn=false;
			repaint();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		p.x=e.getX();
		p.y=e.getY();
		repaint();
	}

	
}
