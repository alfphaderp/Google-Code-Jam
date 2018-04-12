package googlecodejam2018.qualification.gogopher;

/*
 * Go, Gopher!
 * 
 * Visible test set - PASS
 * Hidden test set - PASS
 */

import java.util.Scanner;

public class Solution {
	static int[][] cells;
	static Scanner in;
	
	public static class Coord {
		private int x, y;
		
		public Coord(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
	}
	
	public static void main(String[] args) {
		in = new Scanner(System.in);
		int t = in.nextInt();
		
		for(int i = 1; i < t + 1; i++) {
			int a = in.nextInt();
			int w, h;
			boolean solved = false;
			
			if(a == 20) {
				w = 5;
				h = 4;
				cells = new int[5][4];
			}
			else {
				w = 20;
				h = 10;
				cells = new int[20][10];
			}
			
			while(!solved) {
				Coord c = findOptimalPosition(w, h);
				solved = deploy(c.getX(), c.getY());
			}
		}
		
		in.close();
	}
	
	public static Coord findOptimalPosition(int w, int h) {
		int minLevel = 9;
		int posX = 1;
		int posY = 1;
		
		for(int j = 1; j <= w - 2; j++) {
			for(int k = 1; k <= h - 2; k++) {
				int temp = level(j, k);
				if(temp < minLevel) {
					minLevel = temp;
					posX = j;
					posY = k;
				}
			}
		}
		
		return new Coord(posX, posY);
	}
	
	public static int level(int x, int y) {
		return cells[x - 1][y - 1] + cells[x - 1][y] + cells[x - 1][y + 1] +
			   cells[x][y - 1] + cells[x][y] + cells[x][y + 1] +
			   cells[x + 1][y - 1] + cells[x + 1][y] + cells[x + 1][y + 1];
	}
	
	public static boolean deploy(int x, int y) {
		int realX = x + 1;
		int realY = y + 1;
		System.out.println(realX + " " + realY);
		
		int newX = in.nextInt() - 1;
		int newY = in.nextInt() - 1;
		
		if(newX == -1 && newY == -1) {
			return true;
		}
		
		cells[newX][newY] = 1;
		return false;
	}
}