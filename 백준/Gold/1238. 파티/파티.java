import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
	int end;
	int cost;
	public Node(int end, int cost) {
		this.end=end;
		this.cost=cost;
	}
	
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}

public class Main {
	
	final static int INF = 0x0fffffff;
	
	static int N; // 노드 개수
	static int M; // 단방향 엣지 개수
	static int X; // 파티장소
	
	// N->X로 갔다가 다시 X->N으로 옴
	// = revGraph X->N 다익스트라 + graph X->N 다익스트라

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		ArrayList<Node>[] graph = new ArrayList[N+1];
		ArrayList<Node>[] revGraph = new ArrayList[N+1];
		for (int i=0; i<=N; i++) {
			graph[i] = new ArrayList<>();
			revGraph[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[start].add(new Node(end, cost));
			revGraph[end].add(new Node(start, cost));
		}
		
		int[] dist1 = dijkstra(revGraph, X); // N에서 X로 감 => X->N revGraph 다익스트라
		int[] dist2 = dijkstra(graph, X); // X에서 N으로 감 => X->N graph 다익스트라
		int answer = 0;
		for (int i=1; i<=N; i++) {
			answer = Math.max(answer, dist1[i] + dist2[i]);
		}
		System.out.println(answer);
	}
	
	static int[] dijkstra(ArrayList<Node>[] g, int start) {
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for (Node next : g[cur.end]) {
				int cost = cur.cost + next.cost;
				if (cost < dist[next.end]) {
					dist[next.end] = cost;
					pq.offer(new Node(next.end, cost));
				}
			}
		}
		
		return dist;
	}
}
