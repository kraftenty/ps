import java.io.*;
import java.util.*;


class Node {
	int y;
	int x;
	public Node(int y, int x) {
		this.y=y;
		this.x=x;
	}
}

public class Main {
	
	static int N; // 행의 개수
	static int M; // 열의 개수
	
	static int[] dy = new int[] {-1, 1, 0, 0};
	static int[] dx = new int[] {0, 0, -1, 1};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][M];
		for (int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<M; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int year = 0;
		while (true) {
			String status = getGlacierStatus(board); // 한 덩어리인지 검사
			if (status.equals("more")) {
				break;
			} else if (status.equals("AllMeltedByOneChunk")) {
				year = 0;
				break;
			}
			
			
			board = melt(board);
			year++;
		}
		
		System.out.println(year);
	}
	
	static String getGlacierStatus(int[][] board) {
		// 첫 한 덩어리의 좌표 가져오기
		int[] firstGlacier = getFirstGlacier(board);
		
		// 빙하가 아예 없으면(한 덩어리째로 다 녹아버림) true 반환
		if (firstGlacier == null) {
			return "AllMeltedByOneChunk";
		}

		
		// bfs로 첫 한 덩어리 찾기
		int sy = firstGlacier[0];
		int sx = firstGlacier[1];
		
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(sy, sx));
		boolean[][] visited = new boolean[N][M];
		visited[sy][sx] = true;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int i=0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (ny>=0 && ny<N && nx>=0 && nx<M && 
						board[ny][nx] != 0 && !visited[ny][nx]
				) {
					visited[ny][nx] = true;
					q.add(new Node(ny, nx));
				}
			}
		}
		
		// 첫 한덩어리 이외에 다른 덩어리가 또 있는지 찾기
		for (int y=0; y<N; y++) {
			for (int x=0; x<M; x++) {
				if (board[y][x] != 0 && !visited[y][x]) {
					return "more";
				}
			}
		}
		
		return "one";
	}
	
	static int[] getFirstGlacier(int[][] board) {
		for (int y=0; y<N; y++) {
			for (int x=0; x<M; x++) {
				if (board[y][x] != 0) {
					return new int[] {y, x};
				}
			}
		}
		return null;
	}
	
	static int[][] melt(int[][] board) {
		int[][] result = new int[N][M];
		for (int y=0; y<N; y++) {
			for (int x=0; x<M; x++) {
				if (board[y][x] == 0) {
					continue;
				}
				
				// 0이 아닌 곳(빙산)만
				int meltCount = 0;
				for (int i=0; i<4; i++) {
					int ny = y + dy[i];
					int nx = x + dx[i];
					if (ny>=0 && ny<N && nx>=0 && nx<M && board[ny][nx] == 0) {
						meltCount++;
					}
				}
				result[y][x] = board[y][x] - meltCount;
				if (result[y][x] < 0) {
					result[y][x] = 0;
				}
			}
		}
		
		
		return result;
	}
}
