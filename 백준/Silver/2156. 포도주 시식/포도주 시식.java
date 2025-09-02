import java.io.*;
import java.util.*;


public class Main {
	
	static int n; // 포도주 잔 개수
	static int[] arr; // 포도주 잔 배열

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n+1];
		for (int i=1; i<=n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[n+1]; // i번째 포도주 잔까지 고려했을 때 마실 수 있는 최대 양
		dp[1] = arr[1];
		if (n == 1) {
			System.out.println(arr[1]);
			return;
		}
		dp[2] = arr[1] + arr[2];
		for (int i=3; i<=n; i++) {
			int val1 = dp[i-1]; // i번째 포도주 잔을 마시지 않는 경우
			int val2 = arr[i] + dp[i-2]; // i번째 잔을 마시고, i-1번째 잔은 마시지 않는 경우
			int val3 = arr[i] + arr[i-1] + dp[i-3]; // i번째 잔과 i-1번째 잔을 마시는 경우
			dp[i] = Math.max(val1, Math.max(val2, val3));
		}
		
		System.out.println(dp[n]);
		
	}

	
}

// 1	2	3	4	5	6
// 6	10	13	9	8	1