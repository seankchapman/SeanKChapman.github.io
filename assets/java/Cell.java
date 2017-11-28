import java.awt.*;
import java.util.*;

public class Cell {
	private int x;
	private int y;
	private boolean observed;
	private ArrayList<Cell> neighbors = new ArrayList<Cell>();
	private String loc;
	
	//these booleans decide whether or not a wall is to be deleted
	private boolean delTop;
	private boolean delRight;
	private boolean delBottom;
	private boolean delLeft;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
		observed = false;
		loc = "";
	}
	
	public void paint(Graphics g) {
		if (observed == false) {
			g.setColor(Color.BLUE);
			g.fillRect(x*10 + 50, y*10 + 50, 10, 10);	
		}
		else if (observed == true) {
			g.setColor(Color.WHITE);
			g.fillRect(x*10+50, y*10+50, 10, 10);
			
			if (x == 0 && y == 0) {
				g.setColor(Color.GREEN);
				g.fillRect(x*10+50, y*10+50, 10, 10);
			}
			if (x == 39 && y == 39) {
				g.setColor(Color.RED);
				g.fillRect(x*10+50, y*10+50, 10, 10);
			}
			
			g.setColor(Color.BLACK);
			g.drawRect(x*10 + 50, y*10 + 50, 10, 10);
			
			//erase lines that are to be deleted by drawing over them
			g.setColor(Color.WHITE);
			if (delTop)
				g.drawLine(x*10+50, y*10+50, x*10+60, y*10+50);
			if (delRight)
				g.drawLine(x*10+60, y*10+50, x*10+60, y*10+60);
			if (delBottom)
				g.drawLine(x*10+50, y*10+60, x*10+60, y*10+60);
			if (delLeft)
				g.drawLine(x*10+50, y*10+50, x*10+50, y*10+60);
		}	
	}
	public boolean getObserved() {
		return observed;
	}

	public void setObserved(boolean observed) {
		this.observed = observed;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public boolean getDelTop() {
		return delTop;
	}

	public void setDelTop(boolean delTop) {
		this.delTop = delTop;
	}

	public boolean getDelRight() {
		return delRight;
	}

	public void setDelRight(boolean delRight) {
		this.delRight = delRight;
	}

	public boolean getDelBottom() {
		return delBottom;
	}

	public void setDelBottom(boolean delBottom) {
		this.delBottom = delBottom;
	}

	public boolean getDelLeft() {
		return delLeft;
	}

	public void setDelLeft(boolean delLeft) {
		this.delLeft = delLeft;
	}
}