import java.io.*;
import java.util.*;


public class Main {
	
	static Map<Integer, Integer> answerMap = new HashMap<>(); // 정답저장용 맵 
	static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int y=0; y<N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		answerMap.put(-1, 0);
		answerMap.put(0, 0);
		answerMap.put(1, 0);
		
		sol(0, 0, N);
		
		System.out.println(answerMap.get(-1));
		System.out.println(answerMap.get(0));
		System.out.println(answerMap.get(1));
		
	}

	static void sol(int ys, int xs, int size) { // Start, End
		if (size == 1 || checkAllSame(ys, xs, size)) {
			answerMap.put(board[ys][xs], answerMap.get(board[ys][xs]) + 1);
			return;
		}
		
		// ys = 0, xs = 0, size = 9
		// 일때
		// ys = 0, xs = 0, size = 3
		// ys = 0, xs = 3, size = 3
		// ys = 0, xs = 6, size = 3
		// ys = 3, xs = 0, size = 3
		// ys = 3, xs = 3, size = 3
		// ys = 3, xs = 6, size = 3
		// ys = 6, xs = 0, size = 3
		// ys = 6, xs = 3, size = 3
		// ys = 6, xs = 6, size = 3
		int step = size / 3;
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				sol(ys + i*step, xs + j*step, step);
			}
		}
	}
	
	static boolean checkAllSame(int ys, int xs, int size) {
		int num = board[ys][xs];
		for (int y=ys; y<ys+size; y++) {
			for (int x=xs; x<xs+size; x++) {
				if (board[y][x] != num) {
					return false;
				}
			}
		}
		return true;
	}
}