
import java.util.*;
import java.io.*;


class Solution {

	static int maxVal;
	static int C;
	static char[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr = st.nextToken().toCharArray();
			C = Integer.parseInt(st.nextToken());
			maxVal = 0;
			dfs(0, 0);
			
			sb.append("#").append(tc).append(" ").append(maxVal).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int curidx, int cnt) {
		if (cnt == C) {
			maxVal = Math.max(maxVal, Integer.parseInt(new String(arr)));
			return;
		}
		
		for (int i=curidx; i<arr.length; i++) {
			for (int j=i; j<arr.length; j++) {
				if (i==j && C-cnt>=2) {
					dfs(i, cnt+2);
				}
				if (i==j) continue;
				swap(i, j);
				dfs(i, cnt+1);
				swap(i, j);
			}
		}
	}
	
	static void swap(int i, int j) {
		char tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
