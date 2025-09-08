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
	
	static int N; // 보드의 크기
	static int K; // 사과의 크기
	
	static int[][] board; // 0: 빈칸, 1: 뱀, 2: 사과
	final static int EMPTY = 0;
	final static int SNAKE = 1;
	final static int APPLE = 2;
	
	static int L; // 뱀의 방향 전환 횟수
	static Map<Integer, Integer> changeMap = new HashMap<>();
	
	static int[] dy = new int[] {-1, 0, 1, 0};
	static int[] dx = new int[] {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i=0; i<K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int appleX = Integer.parseInt(st.nextToken());
			int appleY = Integer.parseInt(st.nextToken());
			board[appleX-1][appleY-1] = APPLE;
		}
		L = Integer.parseInt(br.readLine());
		for (int i=0; i<L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			int cVal;
			if (C == 'L') {
				cVal = -1; // 왼쪽으로회전
			} else {
				cVal = 1; // 오른쪽으로회전
			}
			changeMap.put(X, cVal);
		}
		
		int sec = 0;
		
		Deque<Node> dq = new ArrayDeque<>();
		dq.addFirst(new Node(0, 0));
		board[0][0] = SNAKE;
		int dir = 1; // 0: 12시, 1:3시, 2:6시, 3:9시
		
		while (true) {
			sec++;
			// 먼저 뱀 몸길이를 늘려 머리를 다음칸에 위치시킨다.
			Node curHead = dq.peekFirst();
			int ny = curHead.y + dy[dir];
			int nx = curHead.x + dx[dir];
			
			// 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
			if (ny<0 || ny>=N || nx<0 || nx>=N || board[ny][nx] == SNAKE) {
				break;
			}
			
			// 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
			// 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
			dq.addFirst(new Node(ny, nx));
			if (board[ny][nx] == APPLE) {
				board[ny][nx] = SNAKE;
			} else {
				board[ny][nx] = SNAKE;
				Node tail = dq.removeLast();
				board[tail.y][tail.x] = EMPTY; 
			}
			
			// 방향전환
			if (changeMap.containsKey(sec)) {
				dir += changeMap.get(sec);
				if (dir == 4) {
					dir = 0;
				} else if (dir == -1) {
					dir = 3;
				}
			}
		}
		
		System.out.println(sec);
		
	}

}