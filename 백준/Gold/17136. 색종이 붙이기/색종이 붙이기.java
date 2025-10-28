import java.util.*;
import java.io.*;

public class Main {

	final static int N = 10;
	static int[][] board;
	static int[] stock = {0, 5, 5, 5, 5, 5};
	static int minCnt = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[N][N];
		for (int y=0; y<N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, 0);
		
		System.out.println(minCnt == Integer.MAX_VALUE ? -1 : minCnt);
	}
	
	static void dfs(int y, int x, int cnt) {
		// 가지치기
		if (cnt >= minCnt) {
			return;
		}
		
		// 종료조건
		if (y >= 10) {
			minCnt = Math.min(minCnt, cnt);
			return;
		}
		

		if (x >= 10) {
			dfs(y+1, 0, cnt);
			return;
		}
		
		if (board[y][x] == 0) {
			dfs(y, x+1, cnt);
		}
		
		for (int len=5; len>=1; len--) {
			if (canAttach(y, x, len) && stock[len]>0) {
				stock[len]--;
				fill(y, x, len, 0);
				
				dfs(y, x+1, cnt+1);
				
				// 복구
				stock[len]++;
				fill(y, x, len, 1);
			}
		}
		
		
	}
	
	static boolean canAttach(int sy, int sx, int len) {
		if (sy + len > N || sx + len > N) {
			return false;
		}
		
		for (int y=sy; y<sy+len; y++) {
			for (int x=sx; x<sx+len; x++) {
				if (board[y][x]==0) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	static void fill(int sy, int sx, int len, int val) {
		for (int y=sy; y<sy+len; y++) {
			for (int x=sx; x<sx+len; x++) {
				board[y][x] = val;
			}
		}
	}
} 