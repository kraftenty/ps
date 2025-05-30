import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		public int y;
		public int x;
		public Point (int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	static int N; // board 길이
	static int K; // 문명 발상지의 수
	static int[][] board;
	
	static int[] dy;
	static int[] dx;
	
	static int sy,sx;

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			board[y-1][x-1] = 1;
			if (i==0) {
				sy = y-1;
				sx = x-1;
			}
		}

		dy = new int[]{-1, 1, 0, 0};
		dx = new int[]{0, 0, 1, -1};
		
		

		Queue<Point> q = new ArrayDeque<>();
		
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (board[y][x] != 0) {
					q.offer(new Point(y, x));
				}
			}
		}

		int year = 0;
		while(!isUnion()) {
			int size = q.size();
			for (int i=0; i<size; i++) {
				Point p = q.poll();
				for (int d=0; d<4; d++) {
					int ny = p.y + dy[d];
					int nx = p.x + dx[d];
					if (ny >= 0 && ny < N && nx >= 0 && nx < N && board[ny][nx] == 0) {
						board[ny][nx] = 1;
						q.offer(new Point(ny, nx));
					}
				}
			}
			year++;
		}
		
		System.out.println(year);
		
	}
	
	public static boolean isUnion() {
		boolean[][] visited = new boolean[N][N];
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (board[i][j] == 0) {
					visited[i][j] = true;
				}
			}
		}
		
		dfs(sy, sx, visited);
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (visited[i][j] == false) {
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	public static void dfs(int y, int x, boolean[][] visited) {
		for(int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny >= 0 && ny < N && nx >= 0 && nx < N && !visited[ny][nx] && board[ny][nx]==1) {
				visited[ny][nx] = true;
				dfs(ny, nx, visited);
			}
		}
	}
	
	
	
	public static void print() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("-----------------");
	}
}