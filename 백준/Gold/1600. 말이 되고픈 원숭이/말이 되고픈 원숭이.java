import java.util.*;
import java.io.*;


class Node {
	int y,x;
	int k;
	int cnt;
	public Node(int y, int x, int k, int cnt) {
		this.y=y;
		this.x=x;
		this.k=k;
		this.cnt = cnt;
	}
}

public class Main {
	
	static int K;
	static int R, C;
	static int[][] board;
	
	final static int[] dy = {-1, 1, 0, 0};
	final static int[] dx = {0, 0, -1, 1};
	final static int[] hdy = {-1, -2, -2, -1, 1, 2, 2, 1};
	final static int[] hdx = {-2, -1, 1, 2, 2, 1, -1, -2};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		for (int y=0; y<R; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<C; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		Queue<Node> q = new ArrayDeque<>();
		boolean[][][] v = new boolean[R][C][K+1];
		q.offer(new Node(0, 0, K, 0));
		v[0][0][K] = true;
		
		int answer = -1;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.y==R-1 && cur.x==C-1) {
				answer = cur.cnt;
				break;
			}
			
			// 일반 무빙
			for (int i=0; i<4; i++) {
				int ny = cur.y+dy[i];
				int nx = cur.x+dx[i];
				if (inArea(ny, nx) && !v[ny][nx][cur.k] && board[ny][nx]==0) {
					v[ny][nx][cur.k] = true;
					q.offer(new Node(ny, nx, cur.k, cur.cnt+1));
				}
			}
			
			// 말 무빙
			if (cur.k > 0) {
				for (int i=0; i<8; i++) {
					int ny = cur.y+hdy[i];
					int nx = cur.x+hdx[i];
					if (inArea(ny, nx) && !v[ny][nx][cur.k-1] && board[ny][nx]==0) {
						v[ny][nx][cur.k-1] = true;
						q.offer(new Node(ny, nx, cur.k-1, cur.cnt+1));
					}
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static boolean inArea(int y, int x) {
		return y>=0 && y<R && x>=0 && x<C;
	}
}
	