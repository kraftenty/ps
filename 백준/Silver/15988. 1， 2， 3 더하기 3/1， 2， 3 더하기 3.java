import java.util.*;
import java.io.*;


public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		long[] dp = new long[1_000_001];
		dp[1]=1;
		dp[2]=2;
		dp[3]=4;
		for (int i=4; i<1_000_001; i++) {
			dp[i] = (dp[i-1]+dp[i-2]+dp[i-3])%1_000_000_009;
		}
		
		
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine());
			sb.append(dp[N]).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
