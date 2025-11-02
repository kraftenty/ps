import java.util.*;
import java.io.*;


public class Main {
	
	static int N;
	static int[][] board;
	static int[][] maxdp;
	static int[][] mindp;
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N= Integer.parseInt(br.readLine());
		board = new int[N][3];
		maxdp = new int[N][3];
		mindp = new int[N][3];
		for (int y=0; y<N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x=0; x<3; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for (int x=0; x<3; x++) {
			maxdp[0][x] = board[0][x];
			mindp[0][x] = board[0][x];
		}
		
		for (int y=1; y<N; y++) {
			Arrays.fill(mindp[y], Integer.MAX_VALUE);
		}
		
		for (int y=1; y<N; y++) {
			for (int x=0; x<3; x++) {
				// 바로아래
				maxdp[y][x] = Math.max(maxdp[y][x], maxdp[y-1][x] + board[y][x]);
				mindp[y][x] = Math.min(mindp[y][x], mindp[y-1][x] + board[y][x]);
				
				// 왼쪽
				if (x>0) {
					maxdp[y][x-1] = Math.max(maxdp[y][x-1], maxdp[y-1][x] + board[y][x-1]);
					mindp[y][x-1] = Math.min(mindp[y][x-1], mindp[y-1][x] + board[y][x-1]);
				}
				
				
				// 오른쪽
				if (x<2) {
					maxdp[y][x+1] = Math.max(maxdp[y][x+1], maxdp[y-1][x] + board[y][x+1]);
					mindp[y][x+1] = Math.min(mindp[y][x+1], mindp[y-1][x] + board[y][x+1]);
				}
			}
		}
		
		int maxVal = Math.max(maxdp[N-1][0], Math.max(maxdp[N-1][1], maxdp[N-1][2]));
		int minVal = Math.min(mindp[N-1][0], Math.min(mindp[N-1][1], mindp[N-1][2]));
		
		System.out.println(maxVal + " " + minVal);
	}
	

	
}
	