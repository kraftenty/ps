import java.util.*;
import java.io.*;

public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		// 홀수칸은 0
		
		int[] dp = new int[31];
		dp[0] = 1;
		dp[2] = 3;
		for (int i=4; i<=N; i+=2) {
			dp[i] = dp[i-2]*3; // 기본문양
			for (int j=i-4; j>=0; j--) {
				dp[i] += dp[j]*2;
			}
		}
		
		System.out.println(dp[N]);
	}
	
}