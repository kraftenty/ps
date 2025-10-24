import java.util.*;
import java.io.*;

class Node {
	int y;
	int x;
	public Node(int y, int x) {
		this.y=y;
		this.x=x;
	}
}

public class Main {

	static char[][] board;
	static int answer = 0;
	static boolean[][] v = new boolean[5][5];
	
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new char[5][5];
		for (int y=0; y<5; y++) {
			char[] charr = br.readLine().toCharArray();
			for (int x=0; x<5; x++) {
				board[y][x] = charr[x];
			}
		}
		
		// 백트래킹
		dfs(0, 0);
		
		System.out.println(answer);
	}
	
	static void dfs(int depth, int idx) {
		if (depth == 7) {	
			if (has4S() && bfs()) {
				answer++;
			}
			return;
		}
		
		// 가지치기
		if (7-depth > 25-idx) {
			return;
		}
		
		for (int i=idx; i<25; i++) {
			int y = i/5;
			int x = i%5;
			
			v[y][x] = true;
			dfs(depth+1, i+1);
			v[y][x] = false;
		}
	}
	
	static boolean has4S() {
		int scnt = 0;
		for (int y=0; y<5; y++) {
			for (int x=0; x<5; x++) {
				if (v[y][x] && board[y][x]=='S') {
					scnt++;
				}
			}
		}
		
		if (scnt>=4) {
			return true;
		}
		return false;
	}
	
	static boolean bfs() {
		int cnt = 1;
		
		int sy = -1;
		int sx = -1;
		for (int y=0; y<5; y++) {
			for (int x=0; x<5; x++) {
				if (v[y][x]) {
					sy = y;
					sx = x;
					break;
				}
			}
		}
		
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(sy, sx));
		boolean[][] visited = new boolean[5][5];
		visited[sy][sx] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int i=0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (ny>=0 && ny<5 && nx>=0 && nx<5 && v[ny][nx] && !visited[ny][nx]) {
					visited[ny][nx] = true;
					q.offer(new Node(ny, nx));
					cnt++;
				}
			}
		}
		
		return cnt == 7;
	}
}