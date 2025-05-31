import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int t=0; t<T; t++) {
			
			// 입력부분 ---------------------------------------------
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 건물 개수 (1~N번)
			int K = Integer.parseInt(st.nextToken()); // 건설순서 규칙 개수
			
			st = new StringTokenizer(br.readLine());
			int[] D = new int[N+1];					  // 각 건물당 건설 소요시간
			for(int i=1; i<=N; i++) {
				D[i] = Integer.parseInt(st.nextToken());
			}
			
			ArrayList<Integer>[] graph = new ArrayList[N+1];
			for (int i=1; i<=N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			int[] inDegree = new int[N+1];
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				graph[first].add(second);
				inDegree[second] ++;
				
			}
			
			
			// 로직 ---------------------------------------------
			
			int[] dp = new int[N+1];  // i번 건물을 짓는데 최소 시간
			for (int i=1; i<=N; i++) {
				dp[i] = D[i];
			}
			
			// 위상정렬 세팅
			Queue<Integer> q = new ArrayDeque<>();
			for (int i=1; i<=N; i++) {
				if (inDegree[i] == 0) {
					q.offer(i);
				}
			}
			
			// 위상정렬 돌리기
			while(!q.isEmpty()) {
				int cur = q.poll();
				
				for (int next : graph[cur]) {
					inDegree[next] --;
					dp[next] = Math.max(dp[next], dp[cur] + D[next]);
					if (inDegree[next] == 0) {
						q.offer(next);
					}
				}
			}
			
//			System.out.println("Test" + T + " : " + Arrays.toString(dp));
			
			int W = Integer.parseInt(br.readLine()); // 목표 건물		
			System.out.println(dp[W]);
		}
	}
}