import java.util.*;
import java.io.*;

class Edge {
	int to, cost;
	public Edge(int to, int cost) {
		this.to=to;
		this.cost=cost;
	}
}

public class Main {
	
	static int N; // 도시의 개수
	static int M; // 버스의 개수
	
	static List<Edge>[] graph;
	static int startNum;
	static int endNum;
	
	static int[] dist;
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new ArrayList[N+1];
		dist = new int[N+1];
		parent = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for (int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
			parent[i] = -1;
		}
		for (int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph[s].add(new Edge(e, cost));
			
		}
		
		// 출발도시, 도착도시
		StringTokenizer st = new StringTokenizer(br.readLine());
		startNum = Integer.parseInt(st.nextToken());
		endNum = Integer.parseInt(st.nextToken());
	
		dijkstra();
		
		System.out.println(dist[endNum]);
		List<Integer> path = new ArrayList<>();
		int cur = endNum;
		while (cur != -1) {
			path.add(cur);
			cur = parent[cur];
		}
		
		Collections.reverse(path);
		System.out.println(path.size());
		for (int i : path) {
			System.out.println(i + " ");
		}
		
	}
	
	static void dijkstra() {
		dist[startNum] = 0;
		Queue<Edge> pq = new PriorityQueue<>((a,b) -> a.cost - b.cost);
		pq.offer(new Edge(startNum, 0));
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (cur.cost > dist[cur.to]) continue;
			
			for (Edge next : graph[cur.to]) {
				int newCost = cur.cost + next.cost;
				if (newCost < dist[next.to]) {
					dist[next.to] = newCost;
					parent[next.to] = cur.to; 
					pq.offer(new Edge(next.to, newCost));
				}
			}
		}
	}

}
	