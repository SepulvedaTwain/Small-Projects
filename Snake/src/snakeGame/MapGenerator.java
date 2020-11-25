package snakeGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Random;

public class MapGenerator {
	public Nodes [][] nodes=(Nodes [][]) new Nodes[10][10];
	public int brickWindth;
	public int brickHeight;
	public MapGenerator(int row, int col) {
		for(int i=0; i<row;i++) {
			for(int j=0; j<col; j++) {
				
				nodes[i][j]=new Nodes(i,j);
			}
		}
		brickWindth = 500/col;
		brickHeight = 480/row;
		int everyTen=0;
		int turnaround=0;
		for(int i=9; i>=0;i--) {
			for(int j=9; j>=0; j--) {
				if(turnaround%2==0) {
					nodes[i][j].next='r';
					if(j==9) {
						nodes[i][j].next='u';
					}
				}
				else {
					nodes[i][j].next='l';
					if(j==0) {
						nodes[i][j].next='u';
					}
				}
				everyTen++;
				if(everyTen%10==0) {
					turnaround++;
				}
			}
			
		}
		
	}
	
	public void draw(Graphics2D g) {
		int count1=100;
		int everyTen=0;
		int turnaround=0;
		int count2=81;
		String num;
		
		for(int i=0; i<10; i++) {
			for(int j=0; j<10; j++) {
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(j * brickWindth + 80,i * brickHeight + 50, brickWindth, brickHeight);
					
					g.setColor(Color.black);
					g.setFont(new Font("serif",Font.PLAIN, 15));
					if(turnaround%2==0)
						num=String.valueOf(count1--);
					else
						num=String.valueOf(count2++);
					everyTen++;
					if(everyTen%10==0) {
						turnaround++;
						if(turnaround%2==0) {
							count2-=30;
						}
						else
							count1-=10;
					}
						
					
					g.drawString(num,j * brickWindth + 88,i * brickHeight + 81);
					
					g.setStroke(new BasicStroke(1));
					g.setColor(Color.black);
					g.drawRect(j * brickWindth + 80,i * brickHeight + 50, brickWindth, brickHeight);
			}
		}
	}
	
	public void drawSnL(Graphics2D g, boolean drawn) {
		if(!drawn) {
			Random rand = new Random();
			int sX1;
			int sY1;
			int sX2;
			int sY2;
			
			int lX1;
			int lY1;
			int lX2;
			int lY2;
			
			for(int i=0;i<5;i++) {
				sX1=rand.nextInt(10);
				sY1=rand.nextInt(10);
				sX2=rand.nextInt(10);
				sY2=rand.nextInt(10);
				
				
				lX1=rand.nextInt(10);
				lY1=rand.nextInt(10);
				lX2=rand.nextInt(10);
				lY2=rand.nextInt(10);
				
				if(nodes[sX1][sY1].connectedWith==null && nodes[sX2][sY2].connectedWith==null && nodes[sX1][sY1]!=nodes[sX2][sY2]) {
					nodes[sX1][sY1].snake=true;
					nodes[sX1][sY1].connectedWith=nodes[sX2][sY2];
					
					nodes[sX2][sY2].snake=true;
					nodes[sX2][sY2].connectedWith=nodes[sX1][sY1];
					
					if(sX2>sX1) {
						nodes[sX2][sY2].highter=true;
					}
					else if(sX1>sX2) {
						nodes[sX1][sY1].highter=true;
					}
					else {
						if(sY1>sY2)
							nodes[sX1][sY1].highter=true;
						else
							nodes[sX2][sY2].highter=true;
					}
				}
				
				if(nodes[lX1][lY1].connectedWith==null && nodes[lX2][lY2].connectedWith==null && nodes[lX1][lY1]!=nodes[lX2][lY2]) {
					nodes[lX1][lY1].ladder=true;
					nodes[lX1][lY1].connectedWith=nodes[lX2][lY2];
					
					nodes[lX2][lY2].ladder=true;
					nodes[lX2][lY2].connectedWith=nodes[lX1][lY1];
					
					if(lX2>lX1) {
						nodes[lX2][lY2].highter=true;
					}
					else if(lX1>lX2) {
						nodes[lX1][lY1].highter=true;
					}
					else {
						if(lY1<lY2)
							nodes[lX1][lY1].highter=true;
						else
							nodes[lX2][lY2].highter=true;
					}
				}
			}
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					if(nodes[i][j].connectedWith!=null) {
						if(nodes[i][j].snake==true) {
							g.setStroke(new BasicStroke(2));
							g.setColor(Color.green);
							g.draw(new Line2D.Double(j * (500/10) + 88,i * (480/10) + 81,nodes[i][j].connectedWith.col * (500/10) + 88,nodes[i][j].connectedWith.row * (480/10) + 81));
						}
						else if(nodes[i][j].ladder==true) {
							g.setStroke(new BasicStroke(2));
							g.setColor(Color.darkGray);
							g.draw(new Line2D.Double(j * (500/10) + 88,i * (480/10) + 81,nodes[i][j].connectedWith.col * (500/10) + 88,nodes[i][j].connectedWith.row * (480/10) + 81));
							
						}
						
						
					
					}
				}
			}
			
		}
		else {
			for(int i=0; i<10; i++) {
				for(int j=0; j<10; j++) {
					if(nodes[i][j].connectedWith!=null) {
						if(nodes[i][j].snake==true) {
							g.setStroke(new BasicStroke(2));
							g.setColor(Color.green);
							g.draw(new Line2D.Double(j * (500/10) + 88,i * (480/10) + 81,nodes[i][j].connectedWith.col * (500/10) + 88,nodes[i][j].connectedWith.row * (480/10) + 81));
						}
						else if(nodes[i][j].ladder==true) {
							g.setStroke(new BasicStroke(2));
							g.setColor(Color.darkGray);
							g.draw(new Line2D.Double(j * (500/10) + 88,i * (480/10) + 81,nodes[i][j].connectedWith.col * (500/10) + 88,nodes[i][j].connectedWith.row * (480/10) + 81));
							
						}
						
						
					
					}
				}
			}
		
		}
	}
	
}
