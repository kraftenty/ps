import java.io.*;
import java.util.*;


class Point{
	int y;
	int x;
	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class Main {

	static int N; // N*N 땅 크기
	static int L; // 인구차이 L명 이상
	static int R; // 인구차이 R명 이하
	static int[][] board; // 각 나라의 인구수
	static int[][] visited;
	
	static int[] dy = new int[] {-1, 1, 0, 0};
	static int[] dx = new int[] {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for (int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;
		while (true) {
			visited = new int[N][N];
			boolean openedAtLeastOnce = false;
			int nationNum = 1;
			List<int[]> changeList = new ArrayList<>();
			for (int y=0; y<N; y++) {
				for (int x=0; x<N; x++) {
					if (visited[y][x] != 0) {
						continue;
					}
					boolean opened = bfs(visited, y, x, nationNum, changeList);
					if (opened) {
						openedAtLeastOnce = true;
					}
					nationNum++;
				}
			}
			
			// 한번이라도 국경 오픈 안했으면 break
			if (!openedAtLeastOnce) {
				break;
			}
			
			// 국경 오픈 했으면 board 업데이트
			for (int[] arr : changeList) {
				board[arr[0]][arr[1]] = arr[2];
			}
			
			count++;
		}
		
		System.out.println(count);
	}
	
	static boolean bfs(int[][] visited, int sy, int sx, int nationNum, List<int[]> changeList) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(sy, sx));
		visited[sy][sx] = nationNum;
		int count = 1;
		int sum = board[sy][sx];
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			int curPop = board[cur.y][cur.x]; 
			for (int i=0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				
				if (ny>=0 && ny<N && nx>=0 && nx<N && visited[ny][nx] == 0) {
					int nextPop = board[ny][nx];
					int popGap = Math.abs(curPop - nextPop);
					if (popGap>=L && popGap<=R) { // 두 나라의 인구 차이가 L명 이상, R명 이하
						count++;
						sum += board[ny][nx];
						visited[ny][nx] = nationNum;
						q.add(new Point(ny, nx));
					}
				}
			}
		}
		
		if (count == 1) { // 국경선을 하나도 열지 못했다면
			return false;
		}
		
		// 국경선 오픈하기
		int avg = sum / count;
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (visited[y][x] == nationNum) {
					changeList.add(new int[] {y, x, avg});
				}
			}
		}
		return true;
	}
	
}