import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] board;
	
	final static int[] dy = new int[] {0, -1, 0, 1};
	final static int[] dx = new int[] {1, 0, -1, 0};
	
	
	// 입력으로 주어지는 드래곤 커브는 격자 밖으로 벗어나지 않는다. 드래곤 커브는 서로 겹칠 수 있다.
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		board = new int[101][101]; // 100*100
		
		int N = Integer.parseInt(br.readLine());
		for (int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 0=우, 1=상, 2=좌, 3=하
			int d = Integer.parseInt(st.nextToken()); // 시작방향 (0~3)
			int g = Integer.parseInt(st.nextToken()); // 세대 
			
			// 드래곤커브 계획 세우기
			List<Character> planList = makePlan(g);
			
			// 드래곤커브 그리기
			draw(y, x, d, planList);
		}
		
		
		// 크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 것의 개수를 출력
		System.out.println(count());
	}
	
	static List<Character> makePlan(int g) {
		// g0: s
		// g1: s l s
		// g2: s l s l s r s
		// g3: s l s l s r s l s l s r s r s
		// ...
					
		List<Character> planList = new ArrayList<>();
		planList.add('s');
		while (g > 0) {
			List<Character> temp = new ArrayList<>();
			temp.add('l');
			for (int j=planList.size()-1; j>=0; j--) {
				if (planList.get(j) == 'l') {
					temp.add('r');
				} else if (planList.get(j) == 'r') {
					temp.add('l');
				} else {
					temp.add('s');
				}
			}
			planList.addAll(temp);
			g--;
		}
		return planList;
	}
	
	static void draw(int y, int x, int d, List<Character> planList) {
		board[y][x] = 1;
		y += dy[d];
		x += dx[d];
		board[y][x] = 1;
		for (int j=1; j<planList.size(); j++) {
			char plan = planList.get(j);
			if (plan == 's') {
				board[y][x] = 1;
			} else {
				if (plan == 'l') {
					d = (d+1) % 4;
				} else if (plan == 'r') {
					d = (d-1);
					if (d==-1) {
						d=3;
					}
				}
				y += dy[d];
				x += dx[d];
			}
		}
	}
	
	static int count() {
		int cnt=0;
		for (int y=0; y<100; y++) {
			for (int x=0; x<100; x++) {
				if (board[y][x]==1 && board[y][x+1]==1
						&& board[y+1][x]==1 && board[y+1][x+1]==1) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
} 