import java.util.*;
import java.io.*;


public class Main {
	
	final static int MAX = 1000001;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		dp = new int[N+1];
		for (int i=1; i<=N; i++) {
			dp[i] = i;
		}
		for (int i=2; i<=N; i++) {
			if (i%2 == 0) {
				dp[i] = Math.min(dp[i], dp[i/2] + 1);
			} 
			
			if (i%3 == 0) {
				dp[i] = Math.min(dp[i], dp[i/3] + 1);
			}
			
			dp[i] = Math.min(dp[i], dp[i-1] + 1);
		}
		
		sb.append(dp[N]-1).append("\n");
		
		while (N != 0) {
			sb.append(N).append(" ");
			
			if (N%3==0 && dp[N]==dp[N/3]+1) {
				N /= 3;
			} else if (N%2==0 && dp[N]==dp[N/2]+1) {
				N /= 2;
			} else {
				N--;
			}
		}
		
		System.out.println(sb.toString());
	}
	
}