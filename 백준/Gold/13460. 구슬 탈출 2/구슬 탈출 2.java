import java.util.*;
import java.io.*;


public class Main {

	static int N;
	static int M;
	static char[][] board;
	static boolean[][][][] v;
	
	static int minCount = Integer.MAX_VALUE;
	
	final static int[] dy = new int[] {-1, 1, 0, 0};
	final static int[] dx = new int[] {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new char[N][M];
		v = new boolean[N][M][N][M];
		
		
		int ry=0, rx=0;
		int by=0, bx=0;
		for (int y=0; y<N; y++) {
			char[] chArr = br.readLine().toCharArray();
			for (int x=0; x<M; x++) {
				board[y][x] = chArr[x];
				if (chArr[x] == 'R') {
					ry = y;
					rx = x;
					board[y][x] = '.';
				} else if (chArr[x] == 'B') {
					by = y;
					bx = x;
					board[y][x] = '.';
				}
			}
		}
		v[ry][rx][by][bx] = true;
		dfs(0, ry, rx, by, bx);
		
		if (minCount==Integer.MAX_VALUE) minCount = -1;
		System.out.println(minCount);
	}
	
	static void dfs(int depth, int ry, int rx, int by, int bx) {
		// 10번 이상이면 컷
		if (depth > 10) {
			return;
		}
		
		// 파란구슬이 구멍에 들어갔으면 바로 컷
		if (board[by][bx] == 'O') {
			return;
		}
		
		// 빨간구슬만 들어갔으면 성공
		if (board[ry][rx] == 'O') {
			minCount = Math.min(minCount, depth);
		}
		
		for (int d=0; d<4; d++) {
			int nry = ry;
			int nrx = rx;
			int nby = by;
			int nbx = bx;
			
			while (board[nry + dy[d]][nrx + dx[d]] != '#') {
				nry += dy[d];
				nrx += dx[d];
				if (board[nry][nrx]=='O') break;
			}
			
			while (board[nby + dy[d]][nbx + dx[d]] != '#') {
				nby += dy[d];
				nbx += dx[d];
				if (board[nby][nbx]=='O') break;
			}
			
			if (nry==nby && nrx==nbx && board[nry][nrx]=='.') {
				int rStep = Math.abs(nry-ry)+Math.abs(nrx-rx);
				int bStep = Math.abs(nby-by)+Math.abs(nbx-bx);
				
				if (rStep>bStep) {
					nry -= dy[d];
					nrx -= dx[d];
				} else {
					nby -= dy[d];
					nbx -= dx[d];
				}
			}
			
			if (!v[nry][nrx][nby][nbx]) {
				v[nry][nrx][nby][nbx] = true;
				dfs(depth+1, nry, nrx, nby, nbx);
				v[nry][nrx][nby][nbx] = false;
			}
			
		}
	}
	

}