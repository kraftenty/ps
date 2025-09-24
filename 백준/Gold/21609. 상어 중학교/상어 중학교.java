import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
	int y;
	int x;
	public Node(int y, int x) {
		this.y=y;
		this.x=x;
	}
	
	@Override
	public int compareTo(Node o) { // 내림차순
		if (this.y!=o.y) {
			return o.y-this.y;
		}
		return o.x-this.x;
	}
}

class BlockGroup implements Comparable<BlockGroup> {
	List<Node> list;
	int[][] board;
	public BlockGroup(int[][] board) {
		list = new ArrayList<>();
		this.board = board;
	}
	
	public Node getStandardBlock() {
		// 오름차순정렬!!!!
		list.sort((a, b) -> {
			if (a.y!= b.y) {
				return a.y - b.y;
			}
			return a.x - b.x;
		});
		for (Node n : list) {
			if (board[n.y][n.x] != 0) {
				return n;
			}
		}
		return null;
	}
	
	@Override
	public int compareTo(BlockGroup o) {
		// 크기비교
		if (this.list.size() != o.list.size()) {
			return o.list.size() - this.list.size();
		}
		
		// 포함된 무지개블록 개수 비교
		int thisRbCnt = 0;
		for (Node n : this.list) {
			if (board[n.y][n.x]==0) thisRbCnt++;
		}
		int oRbCnt = 0;
		for (Node n : o.list) {
			if (board[n.y][n.x]==0) oRbCnt++;
		}
		if (thisRbCnt != oRbCnt) {
			return oRbCnt - thisRbCnt;
		}
		
		// 기준 블록의 행이 가장 큰 것
		if (this.getStandardBlock().y != o.getStandardBlock().y) {
			return o.getStandardBlock().y - this.getStandardBlock().y;
		}
		
		// 기준 블록의 열이 가장 큰 것
		return o.getStandardBlock().x - this.getStandardBlock().x;
	}
}



public class Main {

	static int N;
	static int M; // 일반 블록 색상의 개수(1부터)
	
	static int[][] board;

	final static int[] dy = new int[] {-1, 1, 0, 0};
	final static int[] dx = new int[] {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for (int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}

		// 메인루프
		int answer = 0;
		while (true) {
			// 1. 크기가 가장 큰 블록 그룹을 찾는다. 
			BlockGroup bg = findBiggestBlockGroup();
			if (bg == null) {
				break;
			}
			
			// 2. 1에서 찾은 블록 그룹의 모든 블록을 제거한다. (빈칸=-2)
			// 블록 그룹에 포함된 블록의 수를 B라고 했을 때, B^2점을 획득한다.
			for (Node n : bg.list) {
				board[n.y][n.x]=-2; 
			}
			answer += Math.pow(bg.list.size(), 2);
			
			// 3. 격자에 중력이 작용한다.
			applyGravity();
			
			
			// 4. 격자가 90도 반시계 방향으로 회전한다.
			rotateBoard();
			
			// 5. 다시 격자에 중력이 작용한다.
			applyGravity();
		}
		
		System.out.println(answer);
	}
	
	static BlockGroup findBiggestBlockGroup() {
		// 후보 블록그룹 모두 찾기
		List<BlockGroup> candidateBGList = new ArrayList<>();
		boolean[][] visited = new boolean[N][N];
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (board[y][x]>0 && !visited[y][x]) {
					BlockGroup bg = bfs(y, x, visited);
					// 그룹에 속한 블록의 개수는 2보다 크거나 같아야 함
					if (bg.list.size() >= 2) {
						candidateBGList.add(bg);
					}
				}
			}
		}
		
		if (candidateBGList.isEmpty()) {
			return null;
		}
		
		// 크기가 가장 큰 블록 그룹을 찾는다.
		// 그러한 블록 그룹이 여러 개라면 포함된 무지개 블록의 수가 가장 많은 블록 그룹, 
		// 그러한 블록도 여러개라면 기준 블록의 행이 가장 큰 것을,
		// 그 것도 여러개이면 열이 가장 큰 것을 찾는다.
		Collections.sort(candidateBGList);
		return candidateBGList.get(0);
	}
	
	static BlockGroup bfs(int sy, int sx, boolean[][] visited) {
		BlockGroup bg = new BlockGroup(board);
		int color = board[sy][sx];
		
		Queue<Node> q = new ArrayDeque<>();
		Node start = new Node(sy, sx);
		q.add(start);
		bg.list.add(start);
		visited[sy][sx] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for (int i=0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (ny>=0 && ny<N && nx>=0 && nx<N &&
						!visited[ny][nx] &&
						(board[ny][nx]==0 || board[ny][nx]==color)
				) {
					visited[ny][nx] = true;
					Node n = new Node(ny, nx);
					q.add(n);
					bg.list.add(n);
				}
			}
		}
		
		// 무지개블록은 visited 에서 빼기
		for (Node n : bg.list) {
			if (board[n.y][n.x]==0) {
				visited[n.y][n.x] = false; 
			}
		}
		
		return bg;
	}
	
	static void applyGravity() {
		for (int x=0; x<N; x++) {
			for(int y=N-2; y>=0; y--) {
				// 중력
				if (board[y][x] >=0) {
					int ny = y;
					while (true) {
						if (ny+1 >= N) break;
						if (board[ny+1][x] != -2) break;
						// swap
						board[ny+1][x] = board[ny][x];
						board[ny][x] = -2;
						ny++;
					}
				}
			}
		}
	}
	
	static void rotateBoard() {
		// 90도 반시계방향 회전
		int[][] temp = new int[N][N];
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				temp[N-1-x][y] = board[y][x]; 
			}
		}
		board = temp;
	}

}