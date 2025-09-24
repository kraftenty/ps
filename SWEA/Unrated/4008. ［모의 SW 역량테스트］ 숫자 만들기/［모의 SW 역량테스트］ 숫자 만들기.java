import java.util.*;
import java.io.*;

class Solution {
	
	static int N;
	static int[] ops;		// 연산자
	static int[] nums;		// 숫자
	static int maxVal;
	static int minVal;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			ops = new int[4];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i=0; i<4; i++) {
				ops[i] = Integer.parseInt(st.nextToken());
			}
			nums = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			maxVal = Integer.MIN_VALUE;
			minVal = Integer.MAX_VALUE;
			
			dfs(1, nums[0]);
			
			System.out.printf("#%d %d\n", tc, maxVal - minVal);
		}
	}
	
	static void dfs(int depth, int val) {
		if (depth == N) {
			maxVal = Math.max(maxVal, val);
			minVal = Math.min(minVal, val);
			return;
		}
		
		for (int i=0; i<4; i++) {
			if (ops[i] > 0) {
				ops[i]--; // 사용
				dfs(depth+1, calc(val, nums[depth], i));
				ops[i]++; // 복구
			}
		}
	}
	
	static int calc(int a, int b, int op) {
		// 0=+, 1=-, 2=*, 3=/
		switch(op) {
		case 0:
			return a+b;
		case 1:
			return a-b;
		case 2:
			return a*b;
		case 3:
			return a/b;
		}
		return -1;
	}
	
}