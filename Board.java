package main;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Board {
	
	private int[][] state = new int [4][4];
	
	public Board() {
		boolean full = false;
		ArrayList<Integer> randoms = new ArrayList<Integer>();
		while (!full) {
			boolean original = true;
			int randomNum = ThreadLocalRandom.current().nextInt(0, 16);
			for (int x = 0; x < randoms.size(); x++) {
				if (randomNum == randoms.get(x)) {
					original = false;
				}
			}
			if (original == true) {
				randoms.add(randomNum);
			}
			if (randoms.size() == 16) {
				full = true;
			}
		}
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				state[x][y] = randoms.get(y + 4 * x);
			}
		}
	}
	
	public int[][] getState() {
		return state;
	}
	
	public void printBoard() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				System.out.print(state[x][y] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean check() {
		int[][] finalState = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,0}};
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (state[x][y] != finalState[x][y]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean up() {
		for (int y = 0; y < 4; y++) {
			if (state[3][y] == 0) {
				return false;
			}
		}
		int i = 0; //x
		int j = 0; //y
		
		outer:for (int h = 0; h < 4; h++) {
			for (int g = 0; g < 4; g++) {
				if (state[h][g] == 0) {
					i = h;
					j = g;
					break outer;
				}
			}
		}
		state[i][j] = state[i+1][j];
		state[i+1][j] = 0;
		return true;
	}
	
	public boolean down() {
		for (int y = 0; y < 4; y++) {
			if (state[0][y] == 0) {
				return false;
			}
		}
		int i = 0; //x
		int j = 0; //y
		
		outer:for (int h = 0; h < 4; h++) {
			for (int g = 0; g < 4; g++) {
				if (state[h][g] == 0) {
					i = h;
					j = g;
					break outer;
				}
			}
		}
		state[i][j] = state[i-1][j];
		state[i-1][j] = 0;
		return true;
	}
	
	public boolean left() {
		for (int y = 0; y < 4; y++) {
			if (state[y][3] == 0) {
				return false;
			}
		}
		int i = 0; //x
		int j = 0; //y
		
		outer:for (int h = 0; h < 4; h++) {
			for (int g = 0; g < 4; g++) {
				if (state[h][g] == 0) {
					i = h;
					j = g;
					break outer;
				}
			}
		}
		state[i][j] = state[i][j+1];
		state[i][j+1] = 0;
		return true;
	}
	
	public boolean right() {
		for (int y = 0; y < 4; y++) {
			if (state[y][0] == 0) {
				return false;
			}
		}
		int i = 0; //x
		int j = 0; //y
		
		outer:for (int h = 0; h < 4; h++) {
			for (int g = 0; g < 4; g++) {
				if (state[h][g] == 0) {
					i = h;
					j = g;
					break outer;
				}
			}
		}
		state[i][j] = state[i][j-1];
		state[i][j-1] = 0;
		return true;
	}
}
