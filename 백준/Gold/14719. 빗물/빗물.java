import java.io.*;
import java.util.*;


public class Main {
	
	static int H; // 세로 길이
	static int W; // 가로 길이
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		
		int board[][] = new int[H][W]; // 0: 빈공간, 1:벽, 2:물 안고이는곳
		for (int x=0; x<W; x++) {
			int h = Integer.parseInt(st.nextToken());
			for (int y=H-1; y>=(H-h); y--) {
				board[y][x] = 1;
			}
		}
		
		// 왼쪽 사이드
		for (int y=0; y<H; y++) {
			if (board[y][0] != 1) { // 벽이 아니면 (뚫려있으면) 물 새어나감
				for (int x=0; x<W; x++) {
					if (board[y][x] == 1) {
						break;
					}
					board[y][x] = 2;
				}
			} else {
				break;
			}
		}
		
		// 오른쪽 사이드
		for (int y=0; y<H; y++) {
			if (board[y][W-1] != 1) { // 벽이 아니면 (뚫려있으면) 물 새어나감
				for (int x=W-1; x>=0; x--) {
					if (board[y][x] == 1) {
						break;
					}
					board[y][x] = 2;
				}
			} else {
				break;
			}
		}

		
		int cnt = 0;
		for (int y=0; y<H; y++) {
			for (int x=0; x<W; x++) {
				if (board[y][x] == 0) {
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
		
	}

	
}
