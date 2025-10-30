import java.util.*;
import java.io.*;


public class Main {
	
	static int N;
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int y=0; y<N; y++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 메인루프
		int maxSum = Integer.MIN_VALUE;
		for (int sy=0; sy<N; sy++) {
			for (int sx=0; sx<N; sx++) {
				int sum=0;
				for (int i=0; i<N; i++) {
					if (sy+i>=N || sx+i>=N) break;
					
					// 세로
					for (int y=sy; y<sy+i; y++) {
						sum += board[y][sx+i];
					}
					
					// 가로
					for (int x=sx; x<sx+i; x++) {
						sum += board[sy+i][x];
					}
					
					// 끝점
					sum += board[sy+i][sx+i];
					
					maxSum = Math.max(maxSum, sum);
				}
			}
		}
		
		System.out.println(maxSum);
	}
}
