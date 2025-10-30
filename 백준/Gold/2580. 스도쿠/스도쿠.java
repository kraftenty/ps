import java.util.*;
import java.io.*;


public class Main {
	
	static int N = 9;
	static int[][] board;
	static List<int[]> holes = new ArrayList<>();
	static boolean endFlag = false;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[N][N];
		for (int y=0; y<N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				if (board[y][x] == 0) {
					holes.add(new int[] {y, x});
				}
			}
		}
		
		dfs(0);
	}
	
	static void dfs(int depth) {
		if (endFlag) {
			return;
		}
		// 종료조건
		if (depth == holes.size()) {
			printBoard();
			endFlag = true;
			return;
		}
		
		int[] hole = holes.get(depth);
		int hy = hole[0];
		int hx = hole[1];
		
		for (int num=1; num<=9; num++) {
			if (check(hy, hx, num)) {
				board[hy][hx] = num;
				dfs(depth+1);
				board[hy][hx] = 0;
			}
		}
	}
	
	static boolean check(int hy, int hx, int num) {
		if (board[hy][hx]!=0) return false;
		
		// 가로
		for (int x=0; x<N; x++) {
			if (board[hy][x]==num) return false;
		}
		
		// 세로
		for (int y=0; y<N; y++) {
			if (board[y][hx]==num) return false;
		}
		
		// 정사각형
		int sy=(hy/3)*3;
		int sx=(hx/3)*3;
		for (int y=sy; y<sy+3; y++) {
			for (int x=sx; x<sx+3; x++) {
				if (board[y][x]==num) return false;
			}
		}
		
		return true;
	}
	
	static void printBoard() {
		StringBuilder sb = new StringBuilder();
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				sb.append(board[y][x]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
