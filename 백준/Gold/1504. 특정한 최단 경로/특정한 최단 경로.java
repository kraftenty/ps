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
	
	//세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동
	// 주어진 두 정점(v1, v2)을 반드시 거치면서 최단 경로로 이동
	// 1. 1 -> v1 -> v2 -> N
	// 2. 1 -> v2 -> v1 -> N
	
	static int N; // 정점의 개수
	static int E; // 간선의 개수
	static List<Edge>[] adj;
	static int v1;
	static int v2;
	
	final static int INF = 0x0FFFFFFF;
	
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		for (int i=1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			adj[a].add(new Edge(b, c));
			adj[b].add(new Edge(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());

		// 1 -> v1 -> v2 -> N
		int result1 = INF;
		int dist_1_to_v1 = dijkstra(1, v1);
		int dist_v1_to_v2 = dijkstra(v1, v2);
		int dist_v2_to_N = dijkstra(v2, N);
		if (dist_1_to_v1 != INF && dist_v1_to_v2 != INF && dist_v2_to_N != INF) {
			result1 = dist_1_to_v1 + dist_v1_to_v2 + dist_v2_to_N;
		}
		
		// 1 -> v2 -> v1 -> N
		int result2 = INF;
		int dist_1_to_v2 = dijkstra(1, v2);
		int dist_v2_to_v1 = dijkstra(v2, v1);
		int dist_v1_to_N = dijkstra(v1, N);
		if (dist_1_to_v2 != INF && dist_v2_to_v1 != INF && dist_v1_to_N != INF) {
			result2 = dist_1_to_v2 + dist_v2_to_v1 + dist_v1_to_N;
		}
		
		int answer = Math.min(result1, result2);
		if (answer == INF) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}

	}
	
	static int dijkstra(int start, int end) {
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		Queue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
		pq.add(new Edge(start, 0));
		
		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if (now.node == end) {
				return now.cost;
			}
			
			if (now.cost > dist[now.node]) {
				continue;
			}
			
			// 인접노드 돌기
			for (Edge next : adj[now.node]) {
				if (dist[next.node] > now.cost + next.cost) {
					dist[next.node] = now.cost + next.cost;
					pq.add(new Edge(next.node, now.cost + next.cost));
				}
			}
		}
		
		// 목적지 도달X
		return INF;
		
	}
}