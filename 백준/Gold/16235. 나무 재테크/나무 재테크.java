import java.util.*;
import java.io.*;


class Tree {
	int y;
	int x;
	int age;
	public Tree(int y, int x, int age) {
		this.y=y;
		this.x=x;
		this.age=age;
	}
}

public class Main {
	
	static int N; // N*N
	
	static int[][] board; // 양분
	static int[][] A; // 양분 추가
	
	static List<Tree> aliveTreeList;
	static Queue<Tree> deadTreeQ;
	
	// 12시부터 시계방향으로
	final static int[] dy = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
	final static int[] dx = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());	// 처음 심는 나무 개수
		int K = Integer.parseInt(st.nextToken());	// K년이 지난 후..
		
		board = new int[N][N];
		for (int y=0; y<N; y++) {
			Arrays.fill(board[y], 5);  // 가장 처음에 양분은 모든 칸에 5만큼 들어있다.
		}
		A = new int[N][N];
		for (int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				A[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		aliveTreeList = new ArrayList<>();
		deadTreeQ = new ArrayDeque<>();
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			aliveTreeList.add(new Tree(x-1, y-1, z)); // 혼동주의
		}
		
		// 메인로직
		for (int year=0; year<K; year++) {
			spring();
			summer();
			autumn();
			winter();
		}
		
		System.out.println(aliveTreeList.size());
		
	}
	
	static void spring() {
		// 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
		// 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
		// 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다. 
		aliveTreeList.sort(Comparator.comparingInt(t -> t.age));
		List<Tree> tempList = new ArrayList<>();
		
		for (int i=0; i<aliveTreeList.size(); i++) {
			Tree t = aliveTreeList.get(i);
			// 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
			if (board[t.y][t.x] < t.age) {
				deadTreeQ.offer(t);
				continue;
			}
			
			board[t.y][t.x] -= t.age; 
			t.age++;
			tempList.add(t);
		}
		
		aliveTreeList = tempList;
	}
	
	static void summer() {
		// 여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
		// 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
		
		while (!deadTreeQ.isEmpty()) {
			Tree t = deadTreeQ.poll();
			board[t.y][t.x] += t.age/2; 
		}
	}
	
	static void autumn() {
		// 가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 
		// 인접한 8개의 칸에 나이가 1인 나무가 생긴다. 
		List<Tree> newTreeList = new ArrayList<>();
		for (Tree t : aliveTreeList) {
			if (t.age % 5 != 0) {
				continue;
			}
			
			for (int i=0; i<8; i++) {
				int ny = t.y + dy[i];
				int nx = t.x + dx[i];
				if (ny>=0 && ny<N && nx>=0 && nx<N) {
					newTreeList.add(new Tree(ny, nx, 1));
				}
			}
			
		}
		
		aliveTreeList.addAll(newTreeList);
	}
	
	static void winter() {
		// 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다. 
		// 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
		for(int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				board[y][x] += A[y][x];
			}
		}
	}
	
} 