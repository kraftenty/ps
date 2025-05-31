import java.io.*;
import java.util.*;

public class Main {

	static int N, M;
	static int[] m, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		m = new int[N];
		c = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) m[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) c[i] = Integer.parseInt(st.nextToken());

		// 비용의 최대 합은 100 * 100
		int maxCost = 100 * 100;
		int[] dp = new int[maxCost + 1]; // dp[i] = 비용이 i일 때 확보 가능한 최대 메모리

		for (int i = 0; i < N; i++) {
			int cost = c[i];
			int memory = m[i];
			// 뒤에서부터 해야 같은앱 중복선택안함
			for (int j = maxCost; j >= cost; j--) {
				dp[j] = Math.max(dp[j], dp[j - cost] + memory);
			}
		}

		
		for (int i = 0; i <= maxCost; i++) {
			if (dp[i] >= M) {
				System.out.println(i);
				break;
			}
		}
	}
}