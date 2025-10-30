import java.util.*;
import java.io.*;


public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1]; // dp[i] = i일때 제곱수항의 최소개수
		for (int i=0; i<=N; i++) {
			dp[i]=i;
		}
		
		for (int i=0; i<N; i++) {
			for (int j=1; j<N; j++) {
				int idx = i + j*j;
				if (idx>N) break;
				dp[idx] = Math.min(dp[idx], dp[i]+1);
			}
		}
		
		System.out.println(dp[N]);
	}
}
