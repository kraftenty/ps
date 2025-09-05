import java.io.*;
import java.util.*;

class Edge {
	int node;
	int cost;
	public Edge(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}
}


public class Main {

	static int N; // 노드개수
	static int M; // 엣지개수
	
	final static int INF = 0x0FFFFFFF;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		List<Edge>[] adj = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a].add(new Edge(b, c));
			adj[b].add(new Edge(a, c));
		}
		
		
		// 현서: 헛간 1
		// 찬홍: 헛간 N
		// 1부터 N까지 최단거리
		int answer = -1;
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		dist[1] = 0;
		Queue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
		pq.add(new Edge(1, 0));
		
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			
			// 종료조건
			if (now.node == N) {
				answer = now.cost;
				break;
			}
			
			// 더 먼 노드는 쳐냄
			if (now.cost > dist[now.node]) {
				continue;
			}
			
			// 인접노드 돌기
			for (Edge next : adj[now.node]) {
				if (dist[next.node] > now.cost + next.cost) {
					dist[next.node] = now.cost + next.cost;
					pq.add(new Edge(next.node, now.cost + next.cost)); // cost 누적
				}
			}

		}
		
		System.out.println(answer);
		
	}
	
}