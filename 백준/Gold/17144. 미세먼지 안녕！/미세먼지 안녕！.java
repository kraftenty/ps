import java.io.*;
import java.util.*;

public class Main {
	
	static int R,C;
	static int T; // T초가 지난 후 답 출력
	
	static int[][] board;
	
	// 공기청정기
	static int purTopY; // 윗쪽
	static int purBottomY; // 아랫쪽
	
	final static int[] dy = new int[] {-1, 1, 0, 0};
	final static int[] dx = new int[] {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		board = new int[R][C];
		boolean purFlag = false;
		for (int y=0; y<R; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<C; x++) { 
				board[y][x] = Integer.parseInt(st.nextToken());
				if (board[y][x]==-1) {
					if (!purFlag) {
						purFlag = true;
						purTopY = y;
					} else {
						purBottomY = y;
					}
				}
			}
		}
		
		
		// T초동안 시뮬레이션
		for (int t=0; t<T; t++) {
			// 1. 미세먼지가 확산된다
			spread();
			
			// 2. 공기청정기가 작동한다
			topPurify();
			bottomPurify();
		}
		
		// T초가 지난 후 미세먼지 양 출력
		System.out.println(countDust());
		
	}

	static void spread() {
		// 배열 복사
		int[][] temp = new int[R][];
		for(int y=0; y<R; y++) {
			temp[y] = Arrays.copyOf(board[y], board[y].length);
		}
		
		for (int y=0; y<R; y++) {
			for (int x=0; x<C; x++) {
				if (board[y][x]<=0) {
					continue;
				}
				
				int spreadAmount = board[y][x]/5;
				int spreadCnt = 0;
				for(int i=0; i<4; i++) {
					int ny=y+dy[i];
					int nx=x+dx[i];
					
					if (ny>=0 && ny<R && nx>=0 && nx<C && board[ny][nx]!=-1) {
						spreadCnt++;
						temp[ny][nx] += spreadAmount;
					}
				}
				
				temp[y][x] -= spreadAmount*spreadCnt;
			}
		}
		
		board = temp;
	}
	
	static void topPurify() {
		// 하방이동
		for (int y=purTopY-1; y>=1; y--) {
			board[y][0] = board[y-1][0];
		}
		
		// 좌측이동
		for (int x=0; x<C-1; x++) {
			board[0][x] = board[0][x+1];
		}

		// 상방이동
		for (int y=0; y<purTopY; y++) {
			board[y][C-1] = board[y+1][C-1];
		}

		// 우측이동
		for (int x=C-1; x>1; x--) {
			board[purTopY][x] = board[purTopY][x-1];
		}
		board[purTopY][1] = 0;
	}
	
	static void bottomPurify() {
		// 상방이동
		for (int y=purBottomY+1; y<R-1; y++) {
			board[y][0] = board[y+1][0];
		}
		
		// 좌측이동
		for (int x=0; x<C-1; x++) {
			board[R-1][x] = board[R-1][x+1];
		}
		
		// 하방이동
		for (int y=R-1; y>purBottomY; y--) {
			board[y][C-1] = board[y-1][C-1];
		}
		
		// 우측이동
		for (int x=C-1; x>1; x--) {
			board[purBottomY][x] = board[purBottomY][x-1];
		}
		board[purBottomY][1] = 0;
	}
	
	static int countDust() {
		int cnt = 0;
		for (int y=0; y<R; y++) {
			for (int x=0; x<C; x++) {
				if (x==0 && (y==purTopY || y==purBottomY)) {
					continue;
				}
				cnt += board[y][x];
			}
		}
		return cnt;
	}

}
