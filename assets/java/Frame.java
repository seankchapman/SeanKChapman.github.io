import javax.swing.*;
import java.awt.*;
import java.util.*;

//Sean Chapman

public class Frame extends JFrame{
	private Stack<Cell> cells;
	public Frame(Stack<Cell> cells) {
		this.cells = cells;
		setResizable(false);
		setSize(500,500);
		setVisible(true);
		setFocusable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Depth-First Search Maze");
	}

	public void paint(Graphics g) {
		super.paint(g);

		//Prints a gray box that fills up the frame. This acts as the background
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, 500, 500);

		//Grid background on which the walls will be printed
		g.setColor(Color.BLACK);
		g.fillRect(50,50,400,400);

		//Paints each cell
		for (int i = 0; i < cells.size();i++) {
			cells.get(i).paint(g);

			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		//Paint the border
		g.setColor(Color.BLACK);
		g.drawRect(50, 50, 400, 400);
	}
}
