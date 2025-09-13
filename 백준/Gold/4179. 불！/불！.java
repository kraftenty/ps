import java.io.*;
import java.util.*;

class Node {
	int y;
	int x;
	int val;
	public Node(int y, int x, int val) {
		this.y=y;
		this.x=x;
		this.val=val;
	}
	
	public Node(int y, int x) {
		this.y=y;
		this.x=x;
		this.val=-1;
	}
}


public class Main {

	static int R;
	static int C;
	
	static int[] dy = new int[] {-1, 1, 0, 0};
	static int[] dx = new int[] {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] board = new char[R+2][C+2];
		// 겉을 E로 둘러쌈
		for (int y=0; y<R+2; y++) {
			Arrays.fill(board[y], 'E');
		}
		
		
		Queue<Node> jq = new ArrayDeque<>();
		Queue<Node> fq = new ArrayDeque<>();
		for (int y=1; y<=R; y++) {
			char[] charArr = br.readLine().toCharArray();
			for (int x=1; x<=C; x++) {
				board[y][x] = charArr[x-1];
				if (board[y][x] == 'J') {
					jq.offer(new Node(y, x, 0));
				} else if (board[y][x] == 'F') {
					fq.offer(new Node(y, x));
				}
			}
		}
		
		
		
		String answer = "IMPOSSIBLE";
		
		
		a: while (!jq.isEmpty()) {
			// 불 퍼지기
			int fqSize = fq.size();
			for (int k=0; k<fqSize; k++) {
				Node f_cur = fq.poll();
				for (int i=0; i<4; i++) {
					int ny = f_cur.y + dy[i];
					int nx = f_cur.x + dx[i];
					if (ny>0 && ny<=R && nx>0 && nx<=C 
							&& board[ny][nx]!='#' 
							&& board[ny][nx]!='E'
							&& board[ny][nx]!='F'
					) {
						board[ny][nx] = 'F';
						fq.offer(new Node(ny, nx));
					}
				}
			}

			// 지훈이 퍼지기
			int jqSize =jq.size();
			for (int k=0; k<jqSize; k++) {
				Node j_cur = jq.poll();
				for (int i=0; i<4; i++) {
					int ny = j_cur.y + dy[i];
					int nx = j_cur.x + dx[i];
					if (ny>=0 && ny<=R+1 && nx>=0 && nx<=C+1) {
						if (board[ny][nx] == 'E') {
							answer = Integer.toString(j_cur.val + 1);
							break a;
						} else if (board[ny][nx] == '.') {
							board[ny][nx] = 'J';
							jq.offer(new Node(ny, nx, j_cur.val + 1));
						}
					}
				}
			}
			
		}
		
		System.out.println(answer);

	}
	
}