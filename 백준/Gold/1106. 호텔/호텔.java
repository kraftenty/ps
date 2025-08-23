import java.io.*;
import java.util.*;

public class Main {

	static int C; // 늘려야 하는 고객 수
	static int N; // 홍보할 수 있는 도시의 개수
	static long[] dp; // n명의 고객을 모으는 데 필요한 최소비용


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		dp = new long[C + 101];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0; // 0명의 고객을 모으는 비용 = 0

		
		for (int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int customer = Integer.parseInt(st.nextToken());
			
			for (int i=customer; i < C+101; i++) {
				dp[i] = Math.min(dp[i], dp[i-customer] + cost);
			}
			
		}
			
		
//		System.out.println(Arrays.toString(dp));
		long minCost = Long.MAX_VALUE;
		for (int i=C; i <C+101; i++) {
			minCost = Math.min(minCost, dp[i]);
		}
		
		System.out.println(minCost);
		 
	}
}
