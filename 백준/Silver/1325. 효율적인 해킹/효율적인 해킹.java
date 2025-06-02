import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 컴퓨터 대수
	static ArrayList<Integer>[] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N+1];
		for (int i=1; i<=N; i++) { 
			graph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph[B].add(A);
		}
		
		// 컴퓨터마다 탐색
		int[] trustCnt = new int[N+1];
		int maxTrustCnt = -1;
		for (int i=1; i<=N; i++) {
			int cnt = bfs(i);
			trustCnt[i] = cnt;
			maxTrustCnt = Math.max(maxTrustCnt, cnt);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=N; i++) {
			if (trustCnt[i] == maxTrustCnt) {
				sb.append(i + " ");
			}
		}
		System.out.println(sb);
 	}
	
	public static int bfs(int start) {
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			for (int next : graph[cur]) {
				if (!visited[next]) {
					visited[next] = true;
					q.offer(next);
				}
			}
		}
		
		int cnt = 0;
		for(int i=1; i<=N; i++) {
			if (visited[i])
				cnt++;
		}
		return cnt;
	}
}