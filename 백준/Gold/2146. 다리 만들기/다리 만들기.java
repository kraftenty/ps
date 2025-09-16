import java.util.*;
import java.io.*;

class Node {
	int y;
	int x;
	int l;
	public Node(int y, int x, int l) {
		this.y=y;
		this.x=x;
		this.l=l;
	}
	public Node(int y, int x) {
		this.y=y;
		this.x=x;
		this.l=-1;
	}
}

public class Main {
	
	static int N;
	static int[][] board;
	
	static int[] dy = new int[] {-1, 1, 0, 0};
	static int[] dx = new int[] {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int y=0; y<N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 구분짓기
		int num = 1;
		boolean[][] visited = new boolean[N][N];
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (!visited[y][x] && board[y][x] != 0) {
					bfs1(y, x, num, visited);
					num++;
				}
			}
		}
		
		
		// 바다와 붙어있는 땅에서 시작
		int minLen = Integer.MAX_VALUE;
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (board[y][x] != 0) {
					// 육지와 붙어있는가
					for (int i=0; i<4; i++) {
						int ny = y + dy[i];
						int nx = x + dx[i];
						if (ny>=0 && ny<N && nx>=0 && nx<N && board[ny][nx]==0) {
							int val = bfs2(y, x, board[y][x]);
//							System.out.println("val="+val+",y,x=" + y + "," + x);
							minLen = Math.min(minLen, val);
							break;
						}
					}
						
				}
			}
		}
		
		
		System.out.println(minLen);
		
	}
	
	static void bfs1(int sy, int sx, int num, boolean[][] visited) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(sy, sx));
		board[sy][sx] = num;
		visited[sy][sx] = true;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for(int i=0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (ny>=0 && ny<N && nx>=0 && nx<N &&
						board[ny][nx]==1 
						&& !visited[ny][nx]
				) {
					board[ny][nx] = num;
					visited[ny][nx] = true;
					q.offer(new Node(ny, nx));
				}
			}
		}
	}
	
	static int bfs2(int sy, int sx, int startNum) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(sy, sx, 0));
		boolean[][] visited = new boolean[N][N];
		visited[sy][sx] = true;
		
		int minLen = Integer.MAX_VALUE;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for(int i=0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if (ny>=0 && ny<N && nx>=0 && nx<N && 
						!visited[ny][nx]) {
					visited[ny][nx]=true;
					
					if (board[ny][nx] != 0 && board[ny][nx] != startNum && (minLen > cur.l)) {
						minLen = cur.l;
					} else if (board[ny][nx] == 0) {
						q.offer(new Node(ny, nx, cur.l+1));	
					}
				}
			}
		}
	
		return minLen;
	}
} 