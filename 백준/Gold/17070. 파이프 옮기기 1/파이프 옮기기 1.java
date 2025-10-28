import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static boolean[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new boolean[N][N];
		for (int y=0; y<N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken())==1 ? true : false;
			}
		}
		
		if (board[N-1][N-1]) {
			System.out.println(0);
			return;
		}
		
		int[][][] dp = new int[N][N][3]; // d)0=가로, 1=대각, 2세로
		dp[0][1][0] = 1; // dp[y][x][d]의 경우의 수 = n개
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (board[y][x]) continue;
				
				// 가로
				if (dp[y][x][0] > 0) {
					if (inArea(y, x+1, false)) dp[y][x+1][0] += dp[y][x][0]; // 가로
					if (inArea(y+1, x+1, true)) dp[y+1][x+1][1] += dp[y][x][0]; // 대각
				}
				
				// 대각
				if (dp[y][x][1] > 0) {
					if (inArea(y, x+1, false)) dp[y][x+1][0] += dp[y][x][1]; // 가로
					if (inArea(y+1, x+1, true)) dp[y+1][x+1][1] += dp[y][x][1]; // 대각
					if (inArea(y+1, x, false)) dp[y+1][x][2] += dp[y][x][1]; // 세로
				}
				
				// 세로
				if (dp[y][x][2] > 0) {
					if (inArea(y+1, x+1, true)) dp[y+1][x+1][1] += dp[y][x][2]; // 대각
					if (inArea(y+1, x, false)) dp[y+1][x][2] += dp[y][x][2]; // 세로
				}
			}
		}
		
		System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
	}
	
	static boolean inArea(int y, int x, boolean isWide) {
		boolean b = y<N && x<N && !board[y][x];
		if (isWide && b) {
			b = b && (y-1<N) && (x-1<N) && !board[y-1][x] && !board[y][x-1];
		}
		return b;
	}

} 