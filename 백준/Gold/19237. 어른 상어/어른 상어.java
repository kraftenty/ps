import java.util.*;
import java.io.*;

class Shark {
	int id;
	int y;
	int x;
	int d;
	int[][] priority;
	boolean dead;
	

	
	public Shark(int id, int y, int x) {
		this.id=id;
		this.y=y;
		this.x=x;
		this.dead=false;
	}
	
	
}

class Node {
	int y;
	int x;
	int d;
	public Node(int y, int x, int d) {
		this.y=y;
		this.x=x;
		this.d=d;
	}
	
	public Node(int y, int x) {
		this.y=y;
		this.x=x;
	}
}

public class Main {

	static int N;
	static int M; // 상어 마리수
	static int k; // 냄새 시간
	
	static int[][] smell;
	static int[][] smellOwner;
	
	static Shark[] sharkArr;
	
	// 상하좌우
	final static int[] dy = new int[] {-1, 1, 0, 0};
	final static int[] dx = new int[] {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		smell = new int[N][N];
		smellOwner = new int[N][N];
		sharkArr = new Shark[M+1];
		for (int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				smellOwner[y][x] = Integer.parseInt(st.nextToken());
				// 상어 입력
				if (smellOwner[y][x] != 0) {
					sharkArr[smellOwner[y][x]] = new Shark(smellOwner[y][x], y, x);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int id=1; id<=M; id++) {
			sharkArr[id].d = Integer.parseInt(st.nextToken()) - 1;
		}
		
		for(int id=1; id<=M; id++) {
			int[][] priority = new int[4][4];
			for(int i=0; i<4; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<4; j++) {
					priority[i][j] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
			sharkArr[id].priority = priority;
		}
		
		// 맨 처음에는 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다.
		for (int id=1; id<=M; id++) {
			Shark s = sharkArr[id];
			smell[s.y][s.x] = k; 
		}
		
		// 메인루프
		int sec = 0;
		while (sec<=1000) {
			// 모든 상어가 동시에 상하좌우로 인접한 칸 중 하나로 이동하고
			for (int id=1; id<=M; id++) {
				Shark s = sharkArr[id];
				if (s.dead) {
					continue;
				}
				// 이동	
				move(s);

			}
			
			// 모두 이동 후 냄새 감소 로직
			reduceSmell();
			
			// 냄새 뿌리기
			spreadSmell();
			

			sec++;
			
			// 1번 상어만 격자에 남게 되기까지 걸리는 시간을 출력한다.
			if (!sharkArr[1].dead) {
				boolean allDeadWithout1 = true;
				for (int id=2; id<=M; id++) {
					if (!sharkArr[id].dead) {
						allDeadWithout1 = false;
						break;
					}
				}
				
				if (allDeadWithout1) {
					break;
				}
			}
			
		}
		
		if (sec == 1001) {
			System.out.println(-1);
			return;
		}
		 
		System.out.println(sec);
		
	}
	
	static void move(Shark s) {
		List<Node> clearList = new ArrayList<>();
		List<Node> mySmellList = new ArrayList<>();
		
		for (int i=0; i<4; i++) {
			int ny = s.y+dy[i];
			int nx = s.x+dx[i];
			if (ny>=0 && ny<N && nx>=0 && nx<N) {
				if (smell[ny][nx]==0)
					clearList.add(new Node(ny, nx, i));
				else if (smellOwner[ny][nx]==s.id)
					mySmellList.add(new Node(ny, nx, i));
			}
		}
		
		Node n = null;
		if (clearList.size() == 1) {
			n = clearList.get(0);
		} else if (clearList.size() > 1) {
			n = getNextNodeByPriority(s, clearList);
		} else {
			// 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다. 
			if (mySmellList.size() == 1) {
				n = mySmellList.get(0);
			} else if (mySmellList.size() > 1) {
				n = getNextNodeByPriority(s, mySmellList);
			}
		}
		
		s.y = n.y;
		s.x = n.x;
		s.d = n.d;
		return;
	}

	static Node getNextNodeByPriority(Shark s, List<Node> ableList) {
		Node retNode = null;
		for (int nd : s.priority[s.d]) {
			int ny = s.y + dy[nd];
			int nx = s.x + dx[nd];
			
			boolean able = false;
			for (Node ab : ableList) {
				if (ny==ab.y && nx==ab.x) {
					retNode = ab;
					able = true;
					break;
				}
			}
			
			if (able) {
				break;
			}
		}
		return retNode;
	}
	
	
	static void reduceSmell() {
	    for (int y = 0; y < N; y++) {
	        for (int x = 0; x < N; x++) {
	            if (smell[y][x] > 0) {
	                smell[y][x]--;
	                if (smell[y][x] == 0) smellOwner[y][x] = 0;
	            }
	        }
	    }
	}
	
	static void spreadSmell() {
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				List<Integer> findIdList = new ArrayList<>();
				for (int id=1; id<=M; id++) {
					Shark s = sharkArr[id];
					if (s.dead) continue;
					
					if (s.y==y && s.x==x) {
						findIdList.add(id);
					}
				}
				
				if (findIdList.isEmpty()) {
					continue;
				}
				
				// 한칸에 상어가 여러마리면, 한마리 빼놓고 죽여야함
				if (findIdList.size() > 1) {
					Collections.sort(findIdList);
					
					for (int i=1; i<findIdList.size(); i++) {
						sharkArr[findIdList.get(i)].dead = true;
					}
				}

				// 승자 냄새 뿌리기
				Shark win = sharkArr[findIdList.get(0)];
				smellOwner[win.y][win.x] = win.id;
				smell[win.y][win.x] = k;
			}
		}
	}

}