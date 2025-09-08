import java.io.*;
import java.util.*;


public class Main {

	static int N;
	static int[] S;	// 신맛
	static int[] B; // 쓴맛
	
	static int minVal = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		B = new int[N];
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0, 1, 0);
		
		System.out.println(minVal);
		
	}
	
	
	// cnt = 사용된 재료 개수
	// s = 신맛 합
	// b = 쓴맛 합
	static void dfs(int depth, int cnt, int s, int b) {
		if (cnt > 0) {
			minVal = Math.min(minVal, Math.abs(s - b));
		}
		
		// dfs종료조건
		if (depth == N) {
			return;
		}
		
		dfs(depth+1, cnt+1, s*S[depth], b+B[depth]);		// depth번째 재료 사용함
		dfs(depth+1, cnt, s, b);							// depth번째 재료 사용안함
	}
	
}
