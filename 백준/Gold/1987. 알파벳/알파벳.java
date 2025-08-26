import java.io.*;
import java.util.*;

public class Main {
	
	static int R; // row
	static int C; // column
	static char[][] board;
	static Set<Character> set; // 방문 체크 셋
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
		
		set = new HashSet<>();
		maxLength = 0;

		set.add(board[0][0]);
		dfs(0, 0, 1);
		
		System.out.println(maxLength);
	}
	
	static void dfs(int y, int x, int length) {
		maxLength = Math.max(maxLength, length);
		
		for (int i=0; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			
			if (ny>=0 && ny<R && nx>=0 && nx<C
					&& !set.contains(board[ny][nx])) {
				set.add(board[ny][nx]);
				dfs(ny, nx, length+1);
				set.remove(board[ny][nx]);
			}
		}
		
	}

}
