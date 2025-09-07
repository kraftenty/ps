import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	int y; 
	int x;
	int cost;
	public Node(int y, int x, int cost) {
		this.y = y;
		this.x = x;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}

public class Main {
	
	static int[] dy = new int[] {-1, 1, 0, 0};
	static int[] dx = new int[] {0, 0, -1, 1};
	
	final static int INF = 0x0fffffff;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(); // 정답 출력용
		int count = 1;
		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			int[][] board = new int[N][N];
			for (int y=0; y<N; y++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int x=0; x<N; x++) {
					board[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = bfs(N, board);
			sb.append("Problem " + count + ": " + result).append("\n");
			count++;
		}
		
		System.out.println(sb.toString());
	}
	
	// 핵심로직
	static int bfs(int N, int[][] board) {
		Queue<Node> q = new PriorityQueue<>();
		q.add(new Node(0, 0, board[0][0]));
		int[][] dist = new int[N][N];
		for (int y=0; y<N; y++) {
			Arrays.fill(dist[y], INF);
		}
		dist[0][0] = board[0][0];
		
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			// 종료조건
			if (cur.y == N-1 && cur.x == N-1) {
				return cur.cost;
			}
			
			for (int i=0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (ny>=0 && ny<N && nx>=0 && nx<N &&
						(cur.cost + board[ny][nx]) < dist[ny][nx]
				) {
					dist[ny][nx] = cur.cost + board[ny][nx];
					q.add(new Node(ny, nx, cur.cost + board[ny][nx]));
				}
			}
		} 
		
		return -1;
	}
	
}
