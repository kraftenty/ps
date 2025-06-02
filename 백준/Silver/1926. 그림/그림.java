import java.util.*;
import java.io.*;

public class Main {
	
	static class Point {
		public int y;
		public int x;
		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static int N; // 세로 크기
	static int M; // 가로 크기
	static int[][] board;
	
	static int[] dy;
	static int[] dx;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		
		dy = new int[] {-1, 1, 0, 0};
		dx = new int[] {0, 0, 1, -1};
		
		for (int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<M; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;
		int maxArea = 0;
		for (int y=0; y<N; y++) {
			for (int x=0; x<M; x++) {
				if (board[y][x] == 1) {
					int area = bfs(y, x);
					maxArea = Math.max(maxArea, area);
					count++;
				}
			}
		}
		
		System.out.println(count + "\n" + maxArea);
 	}
	
	public static int bfs(int sy, int sx) {
		int area = 0;
		
		Queue<Point> q = new ArrayDeque<>();
		board[sy][sx] = 2;
		area++;
		q.offer(new Point(sy, sx));
		
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i=0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (ny >= 0 && ny < N && nx >= 0 && nx < M && board[ny][nx] == 1) {
					board[ny][nx] = 2;
					area++;
					q.offer(new Point(ny, nx));
				}
			}
		}
		 
		return area;
	}
}