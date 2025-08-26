import java.io.*;
import java.util.*;

class Point {
	int y, x, dist;
	public Point(int y, int x, int dist) {
		this.y = y;
		this.x = x;
		this.dist = dist;
	}
}

public class Main {
	
	static int N;
	static int board[][];
	
	// 아기상어 (초기사이즈=2)
	static int sharkY;
	static int sharkX;
	static int sharkSize = 2;
	static int tempSharkEatCount = 0;
	
	static int[] dy = new int[] {-1,1,0,0};
	static int[] dx = new int[] {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 9) {
					sharkY = i;
					sharkX = j;
					board[i][j] = 0;
				}
			}
		}
		
		
		// 시뮬레이션 루프
		int time = 0;
		while (true) {
			
			// BFS로 먹을 수 있는 물고기 찾기
			List<Point> eatableFishList = findEatableFish();
			
			// 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄마 상어에게 도움을 요청한다.
			if (eatableFishList.isEmpty()) {
				break;
			} else {
				// 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
				// 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
				
				if (eatableFishList.size() > 1) { 
					Collections.sort(eatableFishList, (a, b) -> {
						if (a.dist != b.dist) return a.dist - b.dist;
						else if (a.y != b.y) return a.y - b.y;
						else return a.x - b.x;
					});
				}
				
				// 먹기
				Point fishPoint = eatableFishList.get(0);
				time += fishPoint.dist;
				sharkY = fishPoint.y;
				sharkX = fishPoint.x;

				board[fishPoint.y][fishPoint.x] = 0;
				
				tempSharkEatCount++;
				if (tempSharkEatCount == sharkSize) {
					sharkSize++;
					tempSharkEatCount = 0;
				}
			}
		}
		
		System.out.println(time);
		
	}
	
	static List<Point> findEatableFish() {
		List<Point> list = new ArrayList<>();
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];
		
		q.offer(new Point(sharkY, sharkX, 0));
		visited[sharkY][sharkX] = true; 
		
		int minDist = Integer.MAX_VALUE;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			// 최단거리보다 멀어지면 탐색중단(최적화)
			if (p.dist > minDist) {
				break;
			}
			
			// 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
			if (board[p.y][p.x]!=0 && sharkSize > board[p.y][p.x]) {
				minDist = p.dist;
				list.add(p);
			}
			
			for (int i=0; i<4; i++) {
				int ny = p.y + dy[i];
				int nx = p.x + dx[i];
				
				if (ny>=0 && ny<N && nx>=0 && nx<N
						&& !visited[ny][nx]
						&& sharkSize >= board[ny][nx]
				) {
					visited[ny][nx] = true;
					q.offer(new Point(ny, nx, p.dist + 1));
				}
			}
		}
		
		return list;
	}

}