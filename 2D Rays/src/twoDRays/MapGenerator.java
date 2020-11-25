package twoDRays;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;

public class MapGenerator {
	
	Line2D [] lines;
	Color c=new Color((int)(Math.random() * 0x1000000));//new Color(1f,0f,0f,.3f );
	
	
	public MapGenerator(int linesSize){
		lines=(Line2D[])new Line2D[linesSize];
		Random rand=new Random();
		int x1;
		int y1;
		int x2;
		int y2;
		for(int i=0;i<linesSize;i++) {
			x1=rand.nextInt(600)+1;
			y1=rand.nextInt(700)+1;
			x2=rand.nextInt(600)+1;
			y2=rand.nextInt(700)+1;
			
			lines[i]=new Line2D.Double(x1,y1,x2,y2);
			
		}
		
	}
	
	public void drawLines(Graphics2D g) {
		for(int i=0;i<lines.length;i++) {
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.white);
			g.draw(new Line2D.Double(lines[i].getP1(),lines[i].getP2()));
		}
	}
	
	public void drawRays(Graphics2D g,int x,int y) {
		Line2D temp;
		for(int i=0;i<360;i+=1) {
			double distance=1000;
			boolean hits=false;
			temp=new Line2D.Double(x,y,(x +(Math.cos(Math.toRadians(i))) * 1000),(y +(Math.sin(Math.toRadians(i))) * 1000));
			Point2D tokalo=new Point2D.Double();
			for(int j=0;j<lines.length;j++) {
				
				if(temp.intersectsLine(lines[j])) {
					
					Point2D point= lineLineIntersection(lines[j].getP1(),lines[j].getP2(),temp.getP1(),temp.getP2());
					if(point.distance(x,y)<=distance) {
						//System.out.println(point.distance(x,y)+" "+distance);
						distance=point.distance(x,y);
						tokalo.setLocation(point);
						hits=true;
						
					}
				}
			}
			if(hits) {
				g.setStroke(new BasicStroke(1));
				g.setColor(c);
				g.draw(new Line2D.Double(x,y,tokalo.getX(),tokalo.getY()));
			}
			else {
				Line2D tempTop=new Line2D.Double(700,0,700,600);
				Line2D tempBottom= new Line2D.Double(0, 0 , 700, 0);
				Line2D tempRight=new Line2D.Double(700,600, 0, 600);
				Line2D tempLeft=new Line2D.Double(0,600,0,0);
				
				if(temp.intersectsLine(tempTop)) {
					Point2D point= lineLineIntersection(tempTop.getP1(),tempTop.getP2(),temp.getP1(),temp.getP2());
					g.setStroke(new BasicStroke(1));
					g.setColor(c);
					g.draw(new Line2D.Double(x,y,point.getX(),point.getY()));
				}
				else if(temp.intersectsLine(tempBottom)){
					Point2D point= lineLineIntersection(tempBottom.getP1(),tempBottom.getP2(),temp.getP1(),temp.getP2());
					g.setStroke(new BasicStroke(1));
					g.setColor(c);
					g.draw(new Line2D.Double(x,y,point.getX(),point.getY()));
					
				}
				else if(temp.intersectsLine(tempRight)){
					Point2D point= lineLineIntersection(tempRight.getP1(),tempRight.getP2(),temp.getP1(),temp.getP2());
					g.setStroke(new BasicStroke(1));
					g.setColor(c);
					g.draw(new Line2D.Double(x,y,point.getX(),point.getY()));
					
				}
				else if(temp.intersectsLine(tempLeft)) {
					Point2D point= lineLineIntersection(tempLeft.getP1(),tempLeft.getP2(),temp.getP1(),temp.getP2());
					g.setStroke(new BasicStroke(1));
					g.setColor(c);
					g.draw(new Line2D.Double(x,y,point.getX(),point.getY()));
					
				}
				
			}
		}
	}
	
	static Point2D lineLineIntersection(Point2D A, Point2D B, Point2D C, Point2D D) 
    { 
        // Line AB represented as a1x + b1y = c1 
        double a1 = B.getY() - A.getY(); 
        double b1 = A.getX() - B.getX(); 
        double c1 = a1*(A.getX()) + b1*(A.getY()); 
       
        // Line CD represented as a2x + b2y = c2 
        double a2 = D.getY() - C.getY(); 
        double b2 = C.getX() - D.getX(); 
        double c2 = a2*(C.getX())+ b2*(C.getY()); 
       
        double determinant = a1*b2 - a2*b1; 
       
        if (determinant == 0) 
        { 
            // The lines are parallel. This is simplified 
            // by returning a pair of FLT_MAX 
            return null; 
        } 
        else
        { 
            double x = (b2*c1 - b1*c2)/determinant; 
            double y = (a1*c2 - a2*c1)/determinant; 
            Point2D temp=new Point2D.Double();
        	temp.setLocation(x, y);
            return temp; 
        }
    }
	

}
