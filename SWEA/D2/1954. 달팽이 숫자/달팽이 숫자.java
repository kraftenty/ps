import java.util.*;
import java.io.*;

class Solution {
	
	static int N;
	static int[][] board;
	static int num;
	
	
	// 우하좌상
	final static int[] dy = new int[] {0, 1, 0, -1};
	final static int[] dx = new int[] {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			num = 1;
			
			int y=0, x=0;
			int d=0;
			while (num <= N*N) {
				board[y][x] = num;
				int ny = y + dy[d];
				int nx = x + dx[d];
				if (ny<0 || ny>=N || nx<0 || nx>=N || board[ny][nx]!=0) {
					d = (d+1) % 4;
					ny = y + dy[d];
					nx = x + dx[d];
				}
				
				y = ny;
				x = nx;

				num++;
			}
			
			// 출력
			System.out.printf("#%d\n", tc);
			for (y=0; y<N; y++) {
				for (x=0; x<N; x++) {
					System.out.printf("%d ", board[y][x]);
				}
				System.out.println();
			}
		}
	}
}