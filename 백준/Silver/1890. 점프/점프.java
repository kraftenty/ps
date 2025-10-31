import java.util.*;
import java.io.*;


public class Main {

	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int y=0; y<N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		long[][] dp = new long[N][N];
		dp[0][0] = 1;
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (dp[y][x]==0 || board[y][x]==0) continue;
				
				int hop = board[y][x];
				if (x+hop<N) {
					dp[y][x+hop] += dp[y][x];
				}
				if (y+hop<N) {
					dp[y+hop][x] += dp[y][x];
				}
			}
		}
		
		System.out.println(dp[N-1][N-1]);
	}
}
	