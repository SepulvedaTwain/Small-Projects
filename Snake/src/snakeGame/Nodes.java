package snakeGame;

public class Nodes {
	int col;
	int row;
	public Nodes connectedWith;
	public char next;
	public boolean snake;
	public boolean ladder;
	public boolean highter;
	
	public Nodes(int row,int col){
		this.row=row;
		this.col=col;
		connectedWith=null;
		next='\0';
		snake=false;
		ladder=false;
		highter=false;
	}

}
