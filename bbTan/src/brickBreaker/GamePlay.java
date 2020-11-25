package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
	private  boolean play = false;
	private int score = 0;
	
	private int totalBricks =28;
	
	private Timer timer;
	private int delay = 3;
	
	private int playerX = 310;
	
	private int ballposX = 120;
	private int ballposY = 350;
	private float ballXdir = -1;
	private float ballYdir = -1;
	
	private MapGenerator map;
	
	public GamePlay() {
		map = new MapGenerator(4,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	public void paint (Graphics g) {
		requestFocus(true);
		//backround 
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		//darwing map bricks
		map.draw((Graphics2D)g);
		
		//score
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD, 25));
		g.drawString(""+score,590,30);
		
		//borders
		g.setColor(Color.cyan);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0 , 692, 3);
		g.fillRect(684, 0, 3, 592);
		
		//the padle
		g.setColor(Color.green);
		g.fillRect(playerX,550, 100, 8);
		
		
		//ball
		g.setColor(Color.red);
		g.fillOval(ballposX,ballposY, 20, 20);
		
		if(ballposY > 570) {
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD, 30));
			g.drawString("Game Over, Score: " + score ,190,300 );
			
			g.setFont(new Font("serif",Font.BOLD, 20));
			g.drawString("Press Enter to Restart ",230,350 );
		}
		
		if(totalBricks<=0) {
			delay--;
			totalBricks=28;
			timer = new Timer(delay, this);
			timer.start();
			map = new MapGenerator(4,7);
			
			repaint();
			
		}
		
		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(new Rectangle (ballposX, ballposY,15,15).intersects(new Rectangle(playerX,550,100,8))) {
			ballYdir = -ballYdir;
		}
		
		Random rand=new Random();
		
		A: for(int i = 0; i<map.map.length; i++) {
			for(int j=0;j<map.map[0].length;j++) {
				if(map.map[i][j] > 0) {
					int brickX = j * map.brickWindth + 80;
					int brickY = i * map.brickHeight + 50;
					int brickWindth = map.brickWindth;
					int brickHeight = map.brickHeight;
					
					Rectangle rect= new Rectangle(brickX,brickY,brickWindth,brickHeight);
					Rectangle rectBall = new Rectangle(ballposX,ballposY, 20, 20);
					Rectangle rectBrick= rect;
					
					if(rectBall.intersects(rectBrick)) {
						map.setBrickValue(0, i, j);
						totalBricks--;
						score+=5;
						
						if(ballposX + 19 <= rectBrick.x || ballposX + 1 >= rectBrick.x + rectBrick.width ) {
							ballXdir = -ballXdir;
						}
						else {
							ballYdir = -ballYdir;
						}
						break A;
					}
				}
			}
		}
		
		if(play) {
			ballposX += ballXdir;
			ballposY += ballYdir;
			if(ballposX < 0)
				ballXdir = -ballXdir;
			if(ballposY < 0)
				ballYdir = -ballYdir;
			if(ballposX > 670) {
				ballXdir = -ballXdir;
			}
			
		}
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX = 600;
			}
			else {
				moveRight();
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX <= 10) {
				playerX = 10;
			}
			else {
				moveLeft();
			}
			
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballposX=120;
				ballposY=350;
				ballXdir=-1;
				ballYdir=-1;
				playerX=310;
				score=0;
				totalBricks=28; 
				delay=3;
				map = new MapGenerator(4,7);
				
				repaint();
			}
		}
		
	}
	public void moveRight(){
		play=true;
		playerX+=45;
	}
	public void moveLeft(){
		play=true;
		playerX-=45;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		 
		
	}
	

}
