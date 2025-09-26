import java.util.*;
import java.io.*;

class Node {
	int y;
	int x;
	public Node (int y, int x) {
		this.y=y;
		this.x=x;
	}
}

public class Main {

	static int N; // 2^N 격자
	static int NPW;
	static int Q; // 파이어스톰 시전 횟수
	
	static int[][] board;
	static int[] L;
	
	// 상하좌우
	final static int[] dy = {-1, 1, 0, 0};
	final static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		NPW = (int) Math.pow(2, N);
		board = new int[NPW][NPW];
		L = new int[Q+1];
		
		for (int y=0; y<NPW; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<NPW; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int l=1; l<=Q; l++) {
			L[l] = Integer.parseInt(st.nextToken());
		}
		
		// 메인루프
		for (int i=0; i<Q; i++) {
			// 파이어스톰 시전
			splitAndRotate(L[i+1]);
			reduce();
		}
		
		int iceSum = getIceSum();
		int maxIceTileCnt = getMaxIceTileCnt();
		
		System.out.printf("%d\n%d\n", iceSum, maxIceTileCnt);
	}
	
	static void splitAndRotate(int level) {
		int[][] newBoard = new int[NPW][NPW];
		int step = (int) Math.pow(2, level);
		
		// 자르기
		for (int sy=0; sy<NPW; sy+=step) {
			for (int sx=0; sx<NPW; sx+=step) {
				
				// 회전해서 새 보드에 넣기
				for (int y = 0; y < step; y++) {
	                for (int x = 0; x < step; x++) {
	                    // (y, x) -> (x, step-1-y)
	                    int newY = sy + x;
	                    int newX = sx + (step - 1 - y);
	                    newBoard[newY][newX] = board[sy + y][sx + x];
	                }
	            }
			}
		}
		
		board = newBoard;
	}
	
	
	// 중요!!!!!! 한번에 검사 후 한번에 리듀스해야함.
	static void reduce() {
		List<Node> reducedNodeLi = new ArrayList<>();
		for (int y=0; y<NPW; y++) {
			for (int x=0; x<NPW; x++) {
				int cnt = 0;
				for (int i=0; i<4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					if (ny>=0 && ny<NPW && nx>=0 && nx<NPW && board[ny][nx]>0) {
						cnt++;
					}
				}
				if (cnt<3 && board[y][x]>0) {
					reducedNodeLi.add(new Node(y, x));
				}
			}
		}
		for (Node n : reducedNodeLi) {
			board[n.y][n.x]--;
		}
	}
	
	static int getIceSum() {
		int sum = 0;
		for (int y=0; y<NPW; y++) {
			for (int x=0; x<NPW; x++) { 
				if (board[y][x]>0) {
					sum += board[y][x];
				}
			}
		}
		return sum;
	}
	
	static int getMaxIceTileCnt() {
		int maxCnt = 0;
		boolean[][] visited = new boolean[NPW][NPW];
		
		for (int y=0; y<NPW; y++) {
			for (int x=0; x<NPW; x++) {
				if (board[y][x]==0) continue;
				
				int cnt = bfs(y, x, visited);
				if (cnt >= 2) {
					maxCnt = Math.max(maxCnt, cnt);
				}
			}
		}
		return maxCnt;
	}
	
	static int bfs(int sy, int sx, boolean[][] visited) {
		int cnt = 0;
		
		visited[sy][sx] = true;
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(sy, sx));
		cnt++;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for (int i=0; i<4; i++) {
				int ny=cur.y+dy[i];
				int nx=cur.x+dx[i];
				if (ny>=0 && ny<NPW && nx>=0 && nx<NPW && !visited[ny][nx] && board[ny][nx]>0) {
					visited[ny][nx] = true;
					cnt++;
					q.offer(new Node(ny, nx));
				}
			}
		}
		
		return cnt;
	}

}
