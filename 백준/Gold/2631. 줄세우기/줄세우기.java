import java.util.*;
import java.io.*;

public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[N];
		Arrays.fill(dp, 1);
		
		int longest = 0;
		for (int i=0; i<N; i++) {
			
			for (int j=i-1; j>=0; j--) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			
			longest = Math.max(longest, dp[i]);
		}
		
		
		
		System.out.println(N-longest);
	}
	
}

// 3 7 5 2 6 1 4
