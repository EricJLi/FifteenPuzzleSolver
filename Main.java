package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	
	static ArrayList<Integer> moves = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		Board board = new Board();
		bfs(board);
		for (Integer i: moves) {
			if (i == 1) {
				System.out.print("U");
			}
			else if (i == 2) {
				System.out.println("D");
			}
			else if (i == 3) {
				System.out.println("L");
			}
			else if (i == 4) {
				System.out.println("R");
			}
		}
	}
	
	public static void bfs(Board board) {
		if (board.check()) {
			return;
		}
		
		Queue<Integer> queue = new LinkedList<Integer>();
		ArrayList<ArrayList<ArrayList<Integer>>> visited = new ArrayList<ArrayList<ArrayList<Integer>>>();
		boolean result = false;
		int firstMove = 0;
		while (!result) {
			firstMove = ThreadLocalRandom.current().nextInt(1, 5);
			if (firstMove == 1) {
				result = board.up();
			}
			else if (firstMove == 2) {
				result = board.down();
			}
			else if (firstMove == 3) {
				result = board.left();
			}
			else if (firstMove == 4) {
				result = board.right();
			}
		}
		int visitedCounter = 0;
		ArrayList<Integer> moveTemp = new ArrayList<Integer>();
		queue.add(firstMove);
		int [][] currentState = board.getState();
		ArrayList<ArrayList<Integer>> TwoD = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < 4; x++) {
			ArrayList<Integer> OneD = new ArrayList<Integer>();
			for (int y = 0; y < 4; y++) {
				OneD.add(currentState[x][y]);
			}
			TwoD.add(OneD);
		}
		visited.add(TwoD);
		visitedCounter++;
		while (!queue.isEmpty()) {
			int foo = queue.remove();
			boolean possibleMove = true;
			if (foo == 1) {
				possibleMove = board.up();
			}
			else if (foo == 2) {
				possibleMove = board.down();
			}
			else if (foo == 3) {
				possibleMove = board.left();
			}
			else if (foo == 4) {
				possibleMove = board.right();
			}
			
			if (board.check()) {
				for (Integer v: moveTemp) {
					moves.add(v);
				}
				return;
			}
			
			int[][] currentBoardState = board.getState();
			boolean repeat = false;
			for (int m = 0; m < visitedCounter; m++) {
				boolean equal = true;
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
						if (visited.get(m).get(i).get(j) != currentBoardState[i][j]) {
							equal = false;
						}
					}
				}
				if (equal) {
					repeat = true;
					break;
				}
			}
			
			if (possibleMove && !repeat) {
				queue.add(1);
				queue.add(2);
				queue.add(3);
				queue.add(4);
				moveTemp.add(foo);
				currentState = board.getState();
				TwoD = new ArrayList<ArrayList<Integer>>();
				TwoD.clear();
				for (int x = 0; x < 4; x++) {
					ArrayList<Integer> OneD = new ArrayList<Integer>();
					for (int y = 0; y < 4; y++) {
						OneD.add(currentState[x][y]);
					}
					TwoD.add(OneD);
				}
				visited.add(TwoD);
				visitedCounter++;
			}
		}
		
	}
}
