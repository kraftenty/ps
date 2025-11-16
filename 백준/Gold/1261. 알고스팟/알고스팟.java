import java.util.*;
import java.io.*;

class Edge {
	int y, x, cost;
	public Edge(int y, int x, int cost) {
		this.y=y;
		this.x=x;
		this.cost=cost;
	}
}

public class Main {
	
	static int N, M;
	
	static int[][] board;
	static int[][] dist;
	
	final static int[] dy = {-1, 1, 0, 0};
	final static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		dist = new int[N][M];
		
		for (int y=0; y<N; y++) {
			String line = br.readLine();
			for (int x=0; x<M; x++) {
				board[y][x] = line.charAt(x) - '0';
				dist[y][x] = Integer.MAX_VALUE;
			}
		}
		
		dijkstra();
		
		System.out.println(dist[N-1][M-1]);
	}

	static void dijkstra() {
		Queue<Edge> pq = new PriorityQueue<>((a,b) -> a.cost - b.cost);
		dist[0][0] = 0;
		pq.offer(new Edge(0, 0, 0));
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if (cur.cost > dist[cur.y][cur.x]) continue;
			
			for (int i=0; i<4; i++) {
				int ny = cur.y+dy[i];
				int nx = cur.x+dx[i];
				
				if (ny<0 || ny>=N || nx<0 || nx>=M) continue;
				
				int nextCost = cur.cost + board[ny][nx];
				if (nextCost < dist[ny][nx]) {
					dist[ny][nx] = nextCost;
					pq.offer(new Edge(ny, nx, nextCost));
				}
			}
		}
	}
}
	