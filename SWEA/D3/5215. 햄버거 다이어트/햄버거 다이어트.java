import java.util.*;
import java.io.*;

class Food {
	int point;
	int cal;
	public Food(int p, int c) {
		this.point=p;
		this.cal=c;
	}
}

class Solution {

	static int N; // 재료의 수
	static int L; // 제한 칼로리
	
	static Map<Integer, Food> map;
	
	static int maxPoint;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new HashMap<>();
			maxPoint = -1;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int point = Integer.parseInt(st.nextToken());
				int cal = Integer.parseInt(st.nextToken());
				map.put(i, new Food(point, cal));
			}
			
			dfs(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(maxPoint).append("\n");
		}
		
		System.out.print(sb.toString());

	}
	
	static void dfs(int idx, int curPoint, int curCal) {
		if (curCal > L) {
			return;
		}
		if (idx == N) {
			maxPoint = Math.max(maxPoint, curPoint);
			return;
		}
		
		
		dfs(idx+1, curPoint + map.get(idx).point, curCal + map.get(idx).cal);
		dfs(idx+1, curPoint, curCal);
	}
}