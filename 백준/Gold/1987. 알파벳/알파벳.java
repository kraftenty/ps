import java.io.*;
import java.util.*;

public class Main {
	
	static int R; // row
	static int C; // column
	static char[][] board;
	static boolean[] visited = new boolean[26];
	static int maxLength;
	
	static int[] dy = new int[] {-1,1,0,0};
	static int[] dx = new int[] {0,0,1,-1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		
		for (int i=0; i<R; i++) {
			String s = br.readLine();
			for (int j=0; j<C; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		
		visited[board[0][0] - 'A'] = true;
		maxLength = 0;
		dfs(0, 0, 1);
		
		System.out.println(maxLength);
	}
	
	static void dfs(int y, int x, int length) {
		maxLength = Math.max(maxLength, length);
		
		for (int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny>=0 && ny<R && nx>=0 && nx<C
					&& !visited[board[ny][nx] - 'A']) {
				visited[board[ny][nx] - 'A'] = true;
				dfs(ny, nx, length+1);
				visited[board[ny][nx] - 'A'] = false;
			}
		}
		
	}

}