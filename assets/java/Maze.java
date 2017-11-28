/**
 * Sean Chapman
 * 3/27/16
 * 
 * This program creates a randomly generated maze using
 * a recursive backtracker implementation of the 
 * depth-first search algorithm.
 * 
 * Steps:
 * 1: Pick initial cell and mark it as visited
 * 2: While there are unvisited cells
 * 			1: If the current cell has unvisited neighbors
 * 					1: Randomly pick an unvisited neighbor
 * 					2: Push the current cell to a stack
 * 					3: Remove wall between current cell and chosen cell
 * 					4: Make the chosen cell the current cell and mark it as visited
 * 			2: Else if the stack is not empty
 * 					1: Pop a cell from the stack
 * 					2: Make it the current cell
 * 
 * More information can be found on https://en.wikipedia.org/wiki/Maze_generation_algorithm#Recursive_backtracker
 * 
 */

import java.util.*;

public class Maze {
	private ArrayList<Cell> unvisited = new ArrayList<Cell>();
	private Stack<Cell> visited = new Stack<Cell>();
	private ArrayList<Cell> neighbors = new ArrayList<Cell>();
	private Cell currentCell;
	private Cell chosenCell;
	
	public static void main(String[] args) {
		Maze maze = new Maze();
		maze.initialize();
		maze.Algorithm();
		Frame mazeFrame = new Frame(maze.visited);
	}
	
	//Initialize unvisited ArrayList
	public void initialize() {
		for(int i = 0; i < 40; i++) {
			for (int j = 0; j < 40; j++) {
				unvisited.add(new Cell(i,j));
			}
		}
	}
	
	
	public void Algorithm() {
		
		//Pick a start cell and set it to visited
		int startIndex = (int)(Math.random()*1600)-1;
		currentCell = unvisited.get(startIndex);
		 
		
		currentCell.setObserved(true);
		unvisited.remove(currentCell);
		
		//While there are unvisited cells
		while (!unvisited.isEmpty()) {
			
			//get current cell's neighbors and add it to the neighbors arraylist
			for (int i = 0; i < unvisited.size(); i++) {
				//gets the top cell
				if (currentCell.getY() > 0 && unvisited.get(i).getY() == currentCell.getY()-1
						&& unvisited.get(i).getX() == currentCell.getX()){
					neighbors.add(unvisited.get(i));
					
					currentCell.setLoc("top");
					unvisited.get(i).setLoc("bottom");
					
				}
				//gets the right cell
				if (currentCell.getX() < 39 && unvisited.get(i).getX() == currentCell.getX()+1
						&& unvisited.get(i).getY() == currentCell.getY()){
					neighbors.add(unvisited.get(i));
					
					currentCell.setLoc("right");
					unvisited.get(i).setLoc("left");
				}
				//gets the bottom cell
				if (currentCell.getY() < 39 && unvisited.get(i).getY() == currentCell.getY()+1
						&& unvisited.get(i).getX() == currentCell.getX()){
					neighbors.add(unvisited.get(i));
					
					currentCell.setLoc("bottom");
					unvisited.get(i).setLoc("top");
				}
				//gets the left cell
				if (currentCell.getX() > 0 && unvisited.get(i).getX() == currentCell.getX()-1
						&& unvisited.get(i).getY() == currentCell.getY()){
					neighbors.add(unvisited.get(i));
					
					currentCell.setLoc("left");
					unvisited.get(i).setLoc("right");
				}
			}
			
			//remove any visited neighbors
			for (int i = 0; i < neighbors.size();i++) {
				if (neighbors.get(i).getObserved() == true) {
					neighbors.remove(i);
				}
			}
			if (neighbors.size() > 0) {
				//get a random neighbor
				int randIndex = (int)(Math.random()*neighbors.size()-1);
				chosenCell = neighbors.get(randIndex);
			
				//push current cell to stack
				//System.out.println("push");
				visited.push(currentCell);
			
				//Remove wall between current cell and chosen cell
				if (currentCell.getLoc() == "top") {
					currentCell.setDelTop(true);
				}
				if (currentCell.getLoc() == "right") {
					currentCell.setDelRight(true);
				}
				if (currentCell.getLoc() == "bottom") {
					currentCell.setDelBottom(true);
				}
				if (currentCell.getLoc() == "left") {
					currentCell.setDelLeft(true);	
				}
				if (chosenCell.getLoc() == "top") {
					chosenCell.setDelTop(true);
				}
				if (chosenCell.getLoc() == "right") {
					chosenCell.setDelRight(true);
				}
				if (chosenCell.getLoc() == "bottom") {
					chosenCell.setDelBottom(true);
				}
				if (chosenCell.getLoc() == "left") {
					chosenCell.setDelLeft(true);
				}
			
				//make the chosen cell the current cell and mark it as visited
				currentCell = chosenCell;
				currentCell.setObserved(true);
				unvisited.remove(currentCell);
				
			} else if (!visited.isEmpty()) {
				currentCell = visited.pop();
			}
		}
	}
}
