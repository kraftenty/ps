import java.util.*;
import java.io.*;


public class Main {

	static ArrayList<Integer>[] graph;
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int c = 1;
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if (N==0 && M==0) break;
			
			
			boolean[] visited = new boolean[N+1];
			graph = new ArrayList[N+1];
			for (int i=1; i<=N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				graph[s].add(e);
				graph[e].add(s);
			}
			
			int T = 0;
			for (int i=1; i<=N; i++) {
				if (!visited[i]) {
					boolean res = bfs(i, visited);
					if (res) T++;
				}
			}
			
			sb.append("Case ").append(c).append(": ");
			if (T>1) {
				sb.append("A forest of ").append(T).append(" trees.\n");
			} else if (T==1) {
				sb.append("There is one tree.\n");
			} else {
				sb.append("No trees.\n");
			}
			c++;
		}
		
		System.out.println(sb.toString());

	}
	
	static boolean bfs(int s, boolean[] visited) {
		int nodeCnt = 0;
		int edgeCnt = 0;
		Queue<Integer> q = new ArrayDeque<>();
		q.add(s);
		visited[s] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();
			nodeCnt++;
			for (int next : graph[cur]) {
				edgeCnt++;
				if (!visited[next]) {
					visited[next] = true;
					q.add(next);
				}
			}
		}
		
		if (nodeCnt-1 == edgeCnt/2) {
			return true;
		} else {
			return false;
		}
	}
}