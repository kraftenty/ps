import java.util.*;
import java.io.*;

class Shark {
	int y;
	int x;
	int s; // 속력
	int d; // 방향
	int z; // 크기
	public Shark(int y, int x, int s, int d, int z) {
		this.y=y;
		this.x=x;
		this.s=s;
		this.d=d;
		this.z=z;
	}
}

public class Main {

	static int R;
	static int C;
	static int M; // 상어 개수
	
	static int[][] board; // 상어사이즈가 들어간 보드 (메모이제이션)
	static Shark[] arr;
	
	// 상하우좌
	final static int[] dy = {-1, 1, 0, 0};
	final static int[] dx = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		arr = new Shark[10001];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Shark shark = new Shark(r-1, c-1, s, d-1, z);
			arr[z] = shark;
			board[r-1][c-1] = z;
		}
		
		int answer = 0;
		for (int kingX=0; kingX<C; kingX++) { // 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
			// 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 
			// 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
			answer += catchShark(kingX);
			
			// 3. 상어가 이동한다.
			move();
		}
		System.out.println(answer);
	}
	
	static int catchShark(int x) {
		for (int y=0; y<R; y++) {
			if (board[y][x]!=0) {
				int size = arr[board[y][x]].z;
				
				// 상어 없애기
				arr[board[y][x]] = null;
				board[y][x] = 0;
				
				return size;
			}
		}
		return 0;
	}
	
	static void move() {
		int[][] newBoard = new int[R][C];
		
		for (int z=1; z<10001; z++) {
			if (arr[z] == null) continue;
			
			Shark shark = arr[z];
			
			// 왕복횟수 이상이면 왕복 컷하기
			int cnt = shark.s;
			int len = shark.d<2 ? R : C;
			if (cnt >= (len-1)*2) {
				cnt = cnt % ((len-1)*2);
				if (shark.d<2) { // 수직이동상어
					if (shark.y==0 || shark.y==(R-1)) { // 벽에서 시작했으면 방향전환
						changeDir(shark);
					}
				} else { // 수평이동상어
					if (shark.x==0 || shark.x==(C-1)) { // 벽에서 시작했으면 방향전환
						changeDir(shark);
					}
				}
			}
			
			// 나머지 횟수 처리
			while (cnt-- > 0) {
				int ny = shark.y + dy[shark.d];
				int nx = shark.x + dx[shark.d];
				
				// 벽 충돌
				if (ny<0 || ny>=R || nx<0 || nx>=C) {
					changeDir(shark);
					ny = shark.y + dy[shark.d];
					nx = shark.x + dx[shark.d];
				}
				
				shark.y = ny;
				shark.x = nx;
			}
			
			// 이동완료한 칸에 다른 상어가 있으면 배틀
			if (newBoard[shark.y][shark.x]!=0) {
				if (newBoard[shark.y][shark.x] > shark.z) { // 다른 상어가 더크면
					arr[shark.z] = null; 
				} else { // 내 상어가 더 크면
					arr[newBoard[shark.y][shark.x]] = null;
					newBoard[shark.y][shark.x] = shark.z; 
				}
			} else {
				// 없으면 칸 바로 차지
				newBoard[shark.y][shark.x] = shark.z; 
			}
		}
		
		board = newBoard;
	}
	
	static void changeDir(Shark shark) {
		if (shark.d < 2) { // 수직이동상어
			shark.d = shark.d==0 ? 1 : 0;
		} else { // 수평이동상어
			shark.d = shark.d==2 ? 3 : 2;
		}
	}
}
