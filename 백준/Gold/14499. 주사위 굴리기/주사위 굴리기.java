import java.util.*;
import java.io.*;

class Dice {
	int y;
	int x;
	int[] arr;
	
	// 동, 서, 북, 남
	static int[] dy = new int[] {0, 0, 0, -1, 1};
	static int[] dx = new int[] {0, 1, -1, 0, 0};
	
	public Dice(int sy, int sx) {
		arr = new int[7];
		this.y = sy;
		this.x = sx;
	}
	
	public boolean canRoll(int N, int M, int d) {
		// 주사위는 지도의 바깥으로 이동시킬 수 없다. 
		// 만약 바깥으로 이동시키려고 하는 경우에는 해당 명령을 무시해야 하며, 출력도 하면 안 된다.
		int ny = y + dy[d];
		int nx = x + dx[d];
		if (ny<0 || ny>=N || nx<0 || nx>=M) {
			return false;
		}
		return true;
	}
	
	public void roll(int d) {
		switch(d) {
		case 1: // 동
			int tmp1 = arr[3];
			arr[3] = arr[1];
			arr[1] = arr[4];
			arr[4] = arr[6];
			arr[6] = tmp1;
			break;
		case 2: // 서
			int tmp2 = arr[3];
			arr[3] = arr[6];
			arr[6] = arr[4];
			arr[4] = arr[1];
			arr[1] = tmp2;
			break;
		case 3: // 북
			int tmp3 = arr[2];
			arr[2] = arr[1];
			arr[1] = arr[5];
			arr[5] = arr[6];
			arr[6] = tmp3;
			break;
		case 4: // 남
			int tmp4 = arr[6];
			arr[6] = arr[5];
			arr[5] = arr[1];
			arr[1] = arr[2];
			arr[2] = tmp4;
			break;
		}
		
		// 좌표 이동
		y = y + dy[d];
		x = x + dx[d];
	}
	
	public void afterRollProcess(int[][] board) {
		// 주사위를 굴렸을 때, 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다. 
		// 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
		if (board[y][x] == 0) {
			board[y][x] = arr[6];
		} else {
			arr[6] = board[y][x];
			board[y][x] = 0;
		}
	}
	
	public int getTopNum() {
		return arr[1];
	}
}

public class Main {
	
	static int N; // 세로크기
	static int M; // 가로크기
	
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Dice dice = new Dice(X, Y); // 혼동주의
		
		board = new int[N][M];
		for (int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<M; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<K; i++) {
			// 이동 명령 (동=1, 서=2, 북=3, 남=4)
			int d = Integer.parseInt(st.nextToken());
			if (!dice.canRoll(N, M, d)) {
				continue;
			}
			
			dice.roll(d);
			
			dice.afterRollProcess(board);
			
			sb.append(dice.getTopNum() + "\n");
		}
		
		System.out.println(sb.toString());
	}

} 