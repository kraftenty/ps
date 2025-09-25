import java.util.*;
import java.io.*;

class Fireball {
	int id;
	int y;
	int x;
	int m; // 질량
	int s; // 속력
	int d; // 방향
	
	public Fireball(int y, int x, int m, int s, int d, int id) {
		this.y=y;
		this.x=x;
		this.m=m;
		this.s=s;
		this.d=d;
		this.id=id;
	}
}
 
public class Main {
	
	static int N; // N*N
	
	static List<Fireball> li;
	static int idAutoInc;
	
	static List<Fireball>[][] arr;
	
	// 12시부터 시계방향
	final static int[] dy = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
	final static int[] dx = new int[] {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken()); // 초기 파이어볼 개수
		int K = Integer.parseInt(st.nextToken()); // 이동 횟수
		
		li = new ArrayList<Fireball>();
		arr = new ArrayList[N][N];
		idAutoInc = 0;
		
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				arr[y][x] = new ArrayList<>();
			}
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int y=Integer.parseInt(st.nextToken());
			int x=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			int d=Integer.parseInt(st.nextToken());
			Fireball fb = new Fireball(y-1, x-1, m, s, d, idAutoInc++);
			li.add(fb);
			arr[y-1][x-1].add(fb);
		}
		
		
		// 메인루프 
		for (int k=0; k<K; k++) {
			
			moveAll();
			
			// 이동 후, 2개 이상의 파이어볼 칸에 대해
			sumAndSplit();
			
			
		}
		
		// 남아있는 파이어볼 질량 합
		int mSum = 0;
		for (Fireball fb : li) {
			mSum += fb.m;
		}
		System.out.println(mSum);
	}
	
	static void moveAll() {
		// 모든 파이어볼 이동
		List<Fireball>[][] newArr = new ArrayList[N][N];
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				newArr[y][x] = new ArrayList<>();
			}
		}
		for (Fireball fb : li) {
			
			// 새로운 위치에 파볼 배치: 좌표 순환
			fb.y = (fb.y + fb.s*dy[fb.d]) % N;
			if (fb.y < 0) fb.y += N;
			
			fb.x = (fb.x + fb.s*dx[fb.d]) % N;
			if (fb.x < 0) fb.x += N;

			newArr[fb.y][fb.x].add(fb);
		}
		
		arr = newArr;
	}
	
	static void sumAndSplit() {
		List<Fireball> newLi = new ArrayList<>();
		
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				List<Fireball> cellLi = arr[y][x];
				if (cellLi.isEmpty()) {
					continue;
				}
				if (cellLi.size() == 1) {
					newLi.add(cellLi.get(0));
					continue;
				}
				
				// 합치기
				int m_sum=0;
				int s_sum=0;
				boolean is0246 = true;
				int prev_d = -1;
				int originalFbCnt = cellLi.size();
				for (Fireball victim : cellLi) {
					m_sum += victim.m;
					s_sum += victim.s;
					if (is0246) {
						if (prev_d == -1) {
							prev_d = victim.d;
						} else {
							if ((prev_d%2) != (victim.d%2)) {
								is0246 = false;
							}
						}
					}
				}
				
				
				// 나누기
				int m = m_sum / 5;
				int s = s_sum / originalFbCnt;
				if (m > 0) {
					if (is0246) {
						newLi.add(new Fireball(y, x, m, s, 0, idAutoInc++));
						newLi.add(new Fireball(y, x, m, s, 2, idAutoInc++));
						newLi.add(new Fireball(y, x, m, s, 4, idAutoInc++));
						newLi.add(new Fireball(y, x, m, s, 6, idAutoInc++));
						 
					} else {
						newLi.add(new Fireball(y, x, m, s, 1, idAutoInc++));
						newLi.add(new Fireball(y, x, m, s, 3, idAutoInc++));
						newLi.add(new Fireball(y, x, m, s, 5, idAutoInc++));
						newLi.add(new Fireball(y, x, m, s, 7, idAutoInc++));
					}
					
				}
				
				
			}
		}
		li = newLi;
	}
}