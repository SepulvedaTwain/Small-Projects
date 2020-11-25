package snakeGame;

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
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;


public class SnakeGame extends JPanel implements KeyListener, ActionListener {
	
	private boolean dice=false;
	private int diceVal;
	
	private int testC=0;
	
	private int player1X=100;
	private int player1Y=491;
	private boolean player1right=true;
	private boolean won1=false;
	
	private int player2X=100;
	private int player2Y=512;
	private boolean player2right=true;
	private boolean won2=false;
	
	private boolean player1=true;
	
	private boolean snlDrawn=false;
	
	private MapGenerator map;
	
	public SnakeGame() {
		map = new MapGenerator(10,10);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
	}
	
	public void paint (Graphics g) {
		requestFocus(true);
		//backround 
		g.setColor(Color.white);
		g.fillRect(1,1,692,592);
		
		//blocks
		map.draw((Graphics2D)g);
		
		//title
		g.setColor(Color.black);
		g.setFont(new Font("serif",Font.BOLD, 25));
		g.drawString("Snakes & Ladders",230,30);
		
		//press to roll dice
		g.setColor(Color.black);
		g.setFont(new Font("serif",Font.BOLD, 25));
		g.drawString("Press \"r\" to roll the dice ",100,554);
		
		//press Enter to Restart
		g.setColor(Color.black);
		g.setFont(new Font("serif",Font.ITALIC, 20));
		g.drawString("Restart: Enter ",5,30);
		
		//dice
		if(dice) {
			g.setColor(Color.black);
			g.setFont(new Font("serif",Font.BOLD, 25));
			g.drawString("Dice : "+ diceVal,400,554);
		}
		
		//player 1
		g.setColor(Color.red);
		g.fillOval(player1X,player1Y, 10, 10);
		
		//player 2
		g.setColor(Color.blue);
		g.fillOval(player2X,player2Y, 10, 10);
		
		//snakes and Ladders
		map.drawSnL((Graphics2D)g,snlDrawn);
		snlDrawn=true;
		
		//draw won1
		if(won1 &&  !won2) {
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD, 25));
			g.drawString("Player1 won",500,30);
		}
		if(won2 && !won1) {
			g.setColor(Color.blue);
			g.setFont(new Font("serif",Font.BOLD, 25));
			g.drawString("Player2 won",500,30);
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		Random rand=new Random();
		if(e.getKeyCode() == KeyEvent.VK_R) {
			diceVal=rand.nextInt(6) +1;
			dice=true;
			if(player1) {
				for(int x=0;x<diceVal;x++)
					movePlayer(player1);
				checkSnL(player1);
				player1=false;
			}
			else {
				for(int x=0;x<diceVal;x++) 
					movePlayer(player1);
				checkSnL(player1);
					player1=true;
			}
			repaint();
    	}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			map = new MapGenerator(10,10);
			snlDrawn=false;
			player1X=100;
			player1Y=491;
			won1=false;
			
			player2X=100;
			player2Y=522;
			won2=false;
			repaint();
		}
			
		
	}
	public void checkSnL(boolean player1) {
		if(player1) {
			A: for(int  i= 0; i<10; i++) {
				for(int j=0;j<10;j++) {
						int brickX = j * map.brickWindth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWindth = map.brickWindth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect= new Rectangle(brickX,brickY,brickWindth,brickHeight);
						Rectangle rectPlayer = new Rectangle(player1X,player1Y, 10, 10);
						Rectangle rectBrick= rect;
						if(rectPlayer.intersects(rectBrick)) {
							if((map.nodes[i][j].connectedWith!=null && map.nodes[i][j].snake==true&& map.nodes[i][j].highter==false)
									|| (map.nodes[i][j].connectedWith!=null && map.nodes[i][j].ladder==true&& map.nodes[i][j].highter==true) ) {
								player1X=map.nodes[i][j].connectedWith.col * map.brickWindth + 80 + 10;
								player1Y=map.nodes[i][j].connectedWith.row * map.brickHeight + 50 + 10;
								repaint();
								break A;
							}
						}
					}
				}
		}
		else {
			B: for(int  i= 0; i<10; i++) {
				for(int j=0;j<10;j++) {
						int brickX = j * map.brickWindth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWindth = map.brickWindth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect= new Rectangle(brickX,brickY,brickWindth,brickHeight);
						Rectangle rectPlayer = new Rectangle(player2X,player2Y, 10, 10);
						Rectangle rectBrick= rect;
						if(rectPlayer.intersects(rectBrick)) {
							if((map.nodes[i][j].connectedWith!=null && map.nodes[i][j].snake==true&& map.nodes[i][j].highter==false)
									|| (map.nodes[i][j].connectedWith!=null && map.nodes[i][j].ladder==true&& map.nodes[i][j].highter==true) ) {
								player2X=map.nodes[i][j].connectedWith.col * map.brickWindth + 80 + 10;
								player2Y=map.nodes[i][j].connectedWith.row * map.brickHeight + 50 + 30;
								repaint();
								break B;
							}
						}
					}
				}
			
		}
		
	}
	public void movePlayer(boolean player1) { //na ftiaksw ta nodes na exoyn next na teleiwnw
		int moveX=50;
		int moveY=-48;
		if(player1) {
			A: for(int  i= 0; i<10; i++) {
				for(int j=0;j<10;j++) {
						int brickX = j * map.brickWindth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWindth = map.brickWindth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect= new Rectangle(brickX,brickY,brickWindth,brickHeight);
						Rectangle rectPlayer = new Rectangle(player1X,player1Y, 10, 10);
						Rectangle rectBrick= rect;
						if(rectPlayer.intersects(rectBrick)) {
							
								if(map.nodes[i][j].next=='r') {
									player1X+=moveX;
									break A;
								}
								else if(map.nodes[i][j].next=='l') {
									player1X-=moveX;
									if(j-1==0 && i==0) {
										won1=true;
									}
									break A;
								}
								else if(map.nodes[i][j].next=='u') {
									if(!won1)
										player1Y+=moveY;
									break A;
										
								}
								else if(i==0 && j==9) {
									player1X-=moveX;
									break A;
								}
								
								
							
						}
					}
				}
			repaint();	
		}
		else {
			B: for(int  i= 0; i<10; i++) {
				for(int j=0;j<10;j++) {
						int brickX = j * map.brickWindth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWindth = map.brickWindth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect= new Rectangle(brickX,brickY,brickWindth,brickHeight);
						Rectangle rectPlayer = new Rectangle(player2X,player2Y, 10, 10);
						Rectangle rectBrick= rect;
						if(rectPlayer.intersects(rectBrick)) {
								if(map.nodes[i][j].next=='r') {
									player2X+=moveX;
									break B;
								}
								else if(map.nodes[i][j].next=='l') {
									player2X-=moveX;
									if(j-1==0 && i==0) {
										won2=true;
									}
									break B;
								}
								else if(map.nodes[i][j].next=='u') {
									if(!won2)
										player2Y+=moveY;
									break B;
										
								}
								else if(i==0 && j==9) {
									player2X-=moveX;
									break B;
								}
								
								
							
						}
					}
				}
			repaint();	
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void wait(int ms)
	{
	    try
	    {
	        Thread.sleep(ms);
	    }
	    catch(InterruptedException ex)
	    {
	        Thread.currentThread().interrupt();
	    }
	}

}
