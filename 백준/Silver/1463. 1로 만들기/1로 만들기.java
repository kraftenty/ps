import java.util.*;
import java.io.*;

public class Main {
	static int[] dp;
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		for (int i=2; i<=N; i++) {
			dp[i] = dp[i-1] + 1;
			if (i % 3 == 0) {
				dp[i] = Math.min(dp[i/3] + 1, dp[i]);
			} 
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i/2] + 1, dp[i]);
			}
		}
		
		System.out.println(dp[N]);
	}
}