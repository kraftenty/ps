import java.util.*;
import java.io.*;

class Fish {
	int y;
	int x;
	int d;
	public Fish(int y, int x, int d) {
		this.y=y;
		this.x=x;
		this.d=d;
	}
}


public class Main {
	
	final static int[] dy = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
	final static int[] dx = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
	
	static int maxSum = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Map<Integer, Fish> map = new HashMap<>();
		int[][] board = new int[4][4]; // 물고기 번호 저장
		
		for (int y=0; y<4; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x=0; x<4; x++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				board[y][x] = num;
				map.put(num, new Fish(y, x, dir-1));
			}
		}
		
		// 상어 투입 (상어번호=0번)
		int fishNum = board[0][0];
		Fish removed = map.remove(board[0][0]);
		map.put(0, new Fish(0, 0, removed.d));
		board[0][0] = 0;
		
		dfs(map, board, fishNum);
		
		System.out.println(maxSum);
	}
	
	// sy, sx= 상어좌표
	static void dfs(Map<Integer, Fish> map, int[][] board, int sum) { 
		
		maxSum = Math.max(maxSum, sum);
		
		// 물고기 이동
		for (int i=1; i<=16; i++) {
			if (map.containsKey(i)) {
				Fish f = map.get(i);
				for (int a=0; a<8; a++) {
					int ny = f.y + dy[(f.d + a) % 8];
					int nx = f.x + dx[(f.d + a) % 8];
					if (ny>=0 && ny<4 && nx>=0 && nx<4 && board[ny][nx]!=0) {
						f.d = (f.d + a)%8;
						
						if (board[ny][nx]==-1) { // 빈칸일 경우
							board[f.y][f.x] = -1; 
							board[ny][nx] = i;
							f.y = ny;
							f.x = nx;
						} else { // 다른 물고기가 있을 경우
							// 위치 스왑
							Fish o = map.get(board[ny][nx]);
							swap(f, o, board);
						}
						break;
					}
				}
			}
		}
		
		// 상어 이동
		Fish shark = map.get(0);
		for (int i=1; i<4; i++) {
			int ny = shark.y + dy[shark.d]*i;
			int nx = shark.x + dx[shark.d]*i;
			if (ny<0 || ny>=4 || nx<0 || nx>=4 || board[ny][nx] == -1) {
				continue;
			}
			
			// 깊은복사
			int[][] copyBoard = new int[4][4];
			for (int y=0; y<4; y++) {
				System.arraycopy(board[y], 0, copyBoard[y], 0, 4);
			}
			
			Map<Integer, Fish> copyMap = new HashMap<>();
			for (Map.Entry<Integer, Fish> e : map.entrySet()) {
				int key = e.getKey();
				Fish value = e.getValue();
				copyMap.put(key, new Fish(value.y, value.x, value.d));
			}
			
			// 복사된 맵과 보드에 변화 적용 (상어 이동 & 물고기 제거)
			copyBoard[shark.y][shark.x]= -1; 
			int victimNum = copyBoard[ny][nx];
			copyBoard[ny][nx] = 0;
			Fish victim = copyMap.remove(victimNum);
			
			Fish copyShark = copyMap.get(0);
			copyShark.y = victim.y;
			copyShark.x = victim.x;
			copyShark.d = victim.d;
			
			dfs(copyMap, copyBoard, sum + victimNum);
		}		
	}
	
	static void swap(Fish f1, Fish f2, int[][] board) {
		// board에서 위치 스왑
		int tmp = board[f1.y][f1.x];
		board[f1.y][f1.x] = board[f2.y][f2.x];
		board[f2.y][f2.x] = tmp;
		
		// fish 스왑
		int tmpy = f1.y;
		int tmpx = f1.x;
		f1.y = f2.y;
		f1.x = f2.x;
		f2.y = tmpy;
		f2.x = tmpx;
	}

}