import java.util.*;
import java.io.*;

class Point {
	int y,x;
	public Point(int y, int x ) {
		this.y = y;
		this.x = x;
	}
}

public class Main {
	
	static int N, M; // N = 세로, M = 가로
	static int T; // 제한시간
	static int[][] board;
	
	final static int EMPTY = 0;
	final static int WALL = 1;
	final static int SWORD = 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		board = new int[N+1][M+1];
		
		int swordY = 0;
		int swordX = 0;
		for (int y=1; y<=N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=1; x<=M; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
				if (board[y][x] == SWORD) {
					swordY = y;
					swordX = x;
				}
			}
		}
		
		int withoutSwordHour = bfs(N, M);
		int withSwordHour = bfs(swordY, swordX);
		if (withSwordHour != Integer.MAX_VALUE) {
			withSwordHour += ((N - swordY) + (M - swordX));
		}
		
		
		int minHour = Math.min(withoutSwordHour, withSwordHour);
		if (minHour > T) {
			System.out.println("Fail");
		} else {
			System.out.println(minHour);
		}
		
		
	}
	
	static int bfs(int endY, int endX) {
		int[][] count = new int[N+1][M+1];
		int[] dy = new int[] {1, -1, 0, 0};
		int[] dx = new int[] {0, 0, -1, 1};
		
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(1, 1));
		count[1][1] = 1;
		while (!q.isEmpty()) {
			Point cp = q.poll();
			if (cp.y == endY && cp.x == endX) {
				// 목표점까지 찾아옴
				return count[cp.y][cp.x] - 1;
			}
			for (int i=0; i<4; i++) {
				int ny = cp.y + dy[i];
				int nx = cp.x + dx[i];
				if (ny >= 1 && ny <= N && nx >= 1 && nx <= M && board[ny][nx] != 1 && count[ny][nx] == 0) {
					count[ny][nx] = count[cp.y][cp.x] + 1;
					q.offer(new Point(ny, nx));
				}
			}
		}
		
		
		// 길막힘
		return Integer.MAX_VALUE;
		
	}

}