import java.util.*;
import java.io.*;

class Node {
	int y, x, d;
	public Node (int y, int x, int d) { // d)0=가로, 1=대각, 2세로
		this.y=y;
		this.x=x;
		this.d=d;
	}
}

public class Main {
	
	static int N;
	static boolean[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new boolean[N][N];
		for (int y=0; y<N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken())==1 ? true : false;
			}
		}
		
		if (board[N-1][N-1]) {
			System.out.println(0);
			return;
		}
		
		Node s = new Node(0, 1, 0);
		Queue<Node> q = new ArrayDeque<>();
		q.offer(s);
		
		int cnt = 0;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.y == N-1 && cur.x == N-1) {
				cnt++;
			}
			
			if (cur.d==0 || cur.d==1) { // 가로
				if (inArea(cur.y, cur.x+1, false)) {
					q.add(new Node(cur.y, cur.x+1, 0));
				}
			}
			if (cur.d==1 || cur.d==2) { // 세로
				if (inArea(cur.y+1, cur.x, false)) {
					q.add(new Node(cur.y+1, cur.x, 2));
				}
			}
			// 대각
			if (inArea(cur.y+1, cur.x+1, true)) {
				q.add(new Node(cur.y+1, cur.x+1, 1));
			}
		}
		
		System.out.println(cnt);
	}
	
	static boolean inArea(int y, int x, boolean isWide) {
		boolean b = y<N && x<N && !board[y][x];
		if (isWide && b) {
			b = b && (y-1>=0 && y-1<N) && (x-1>=0 && x-1<N) && !board[y-1][x] && !board[y][x-1];
		}
		return b;
	}

} 