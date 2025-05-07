import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine()); 
		for (int t=0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] coins = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int n = 0; n<N; n++) {
				coins[n] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());
			int result = solution(N, coins, M);
			bw.write(result + "\n");
		}
		
		bw.flush();
	}

	private static int solution(int n, int[] coins, int m) {
		int[] dp = new int[m+1]; // dp[a] : 금액 a를 만드는 경우의 수
		dp[0] = 1;
		
		for (int coin : coins) {
			for (int i=0; i<m+1; i++) {
				if (i-coin >= 0)
					dp[i] += dp[i-coin];
			}
		}
		
		return dp[m];
	}

}
