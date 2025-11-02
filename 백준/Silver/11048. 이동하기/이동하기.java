import java.util.*;
import java.io.*;


public class Main {
	
	static int R;
	static int C;

	static int[][] board;
	static int[][] dp;
	static boolean[][] v;
	
	final static int[] dy = {1, 0, 1};
	final static int[] dx = {0, 1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R= Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		dp = new int[R][C];
		v = new boolean[R][C];
		for (int y=0; y<R; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<C; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(dfs(0, 0));
	}
	
	static int dfs(int y, int x) {
		if (y==R-1 && x==C-1) {
			return board[R-1][C-1];
		}
		
		
		// 이미 계산된경우
		if (v[y][x]) return dp[y][x];
		v[y][x] = true;

		int maxVal = 0;
		
		for (int i=0; i<3; i++) {
			int ny=y+dy[i];
			int nx=x+dx[i];
			
			if (ny<0 || ny>=R || nx<0 || nx>=C) continue;
			
			maxVal = Math.max(maxVal, dfs(ny, nx));
		}
		
		dp[y][x] = board[y][x] + maxVal;
		
		return dp[y][x];
	}

	
}
	