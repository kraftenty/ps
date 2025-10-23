import java.util.*;
import java.io.*;

class Node {
	int z;
	int y;
	int x;
	public Node(int z, int y, int x) {
		this.z=z;
		this.y=y;
		this.x=x;
	}
}


public class Main {

	static int L;
	static int R;
	static int C;
	static char[][][] board;
	
	// 시작점 좌표
	static int sz=0;
	static int sy=0;
	static int sx=0;
	
	// 끝점 좌표
	static int ez=0;
	static int ey=0;
	static int ex=0;
	
	final static int[] dz = new int[] {0, 0, 0, 0, 1, -1};
	final static int[] dy = new int[] {-1, 1, 0, 0, 0, 0};
	final static int[] dx = new int[] {0, 0, -1, 1, 0, 0};
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			if (L==0 && R==0 && C==0) {
				break;
			}
			board = new char[L][R][C];
			
			// 보드 입력
			for (int z=0; z<L; z++) {
				for (int y=0; y<R; y++) {
					String s = br.readLine();
					for (int x=0; x<C; x++) {
						board[z][y][x] = s.charAt(x);
						if (board[z][y][x] == 'S') {
							sz = z;
							sy = y;
							sx = x;
						} else if (board[z][y][x] == 'E') {
							ez = z;
							ey = y;
							ex = x;
						}
					}
				}
				
				br.readLine();
			}
			
			// 메인로직
			int answer = bfs();
			
			if (answer == -1) {
				sb.append("Trapped!\n");
			} else {
				sb.append("Escaped in ").append(answer).append(" minute(s).\n");
			}
			
		}
		
		System.out.print(sb.toString());
	}
	
	static int bfs() {
		int[][][] v = new int[L][R][C];
		v[sz][sy][sx] = 1; // 마지막에 1빼줘야함
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(sz, sy, sx));
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int i=0; i<6; i++) {
				int nz=cur.z+dz[i];
				int ny=cur.y+dy[i];
				int nx=cur.x+dx[i];
				
				if (inArea(nz, ny, nx) && v[nz][ny][nx]==0 && board[nz][ny][nx]!='#') {
					v[nz][ny][nx] = v[cur.z][cur.y][cur.x] + 1;
					q.offer(new Node(nz, ny, nx));
				}
				
			}
		}
		
		if (v[ez][ey][ex]!=0) {
			return v[ez][ey][ex]-1;
		} else {
			return -1;
		}
	}
	
	static boolean inArea(int z, int y, int x) {
		return z>=0 && z<L && y>=0 && y<R && x>=0 && x<C;
	}

}