import java.util.*;
import java.io.*;

class Node {
	int y;
	int x;
	public Node (int y, int x) {
		this.y=y;
		this.x=x;
	}
}

class Customer implements Comparable<Customer> {
	int sy;
	int sx;
	int ey;
	int ex;
	boolean completed;
	int len;
	public Customer(int sy, int sx, int ey, int ex) {
		this.sy=sy;
		this.sx=sx;
		this.ey=ey;
		this.ex=ex;
		this.completed=false;
		this.len=-1;
	}
	
	public int compareTo(Customer o) {
		if (this.len != o.len) {
			return this.len - o.len;
		} else if (this.sy != o.sy) {
			return this.sy - o.sy;
		} else {
			return this.sx - o.sx;
		}
	}
}

//class PickupResult {
//	int len;
//	Customer customer;
//	public PickupResult(int len, Customer customer) {
//		this.len=len;
//		this.customer=customer;
//	}
//}

public class Main {
	
	static int N;
	static int F; // 연료
	
	static int ty, tx; // 택시좌표
	
	static int[][] board;
	static List<Customer> customers;
	
	final static int[] dy = {-1, 1, 0, 0};
	final static int[] dx = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		for (int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		ty = Integer.parseInt(st.nextToken())-1;
		tx = Integer.parseInt(st.nextToken())-1;
		
		customers = new ArrayList<>();
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int ey = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			customers.add(new Customer(sy-1, sx-1, ey-1, ex-1));
		}
		
		
		// 메인루프
		for (int i=0; i<M; i++) {
			// [1] 승객 고르기
			Customer c = pickCustomer();
			if (c.len == -1) {
				F = -1;
				break;
			}

			// 연료 소모
			F -= c.len;
			if (F < 0) {
				F = -1;
				break;
			}
			
			// 택시 이동
			ty = c.sy;
			tx = c.sx;
			
			// [2] 승객 목적지로 이동, 연료정산
			gotoGoal(c);
			
			// 연료 소모
			F -= c.len;
			if (F < 0) {
				F = -1;
				break;
			}
			
			// 연료 충전
			F += (c.len*2);
			
			// 택시 이동
			ty = c.ey;
			tx = c.ex;
		}
		
		System.out.println(F);
	}
	
	static Customer pickCustomer() {
		// 택시로부터 퍼지는 bfs
		int[][] minMap = getMinMap();
//		dbg(minMap);
		
		for (Customer c : customers) {
			if (c.completed) {
				c.len = Integer.MAX_VALUE;
				continue;
			}
			c.len = minMap[c.sy][c.sx];			
		}
		
		Collections.sort(customers);
		
		Customer ret = customers.get(0);
		
		// 갈 수 있는지 검증. 갈수없으면 ret.len을 -1로 리턴
		int mhtDist = Math.abs(ty-ret.sy) + Math.abs(tx-ret.sx);
		if (mhtDist>0 && ret.len==0) {
			ret.len=-1;
		}
		
		return ret;
	}
	
	static int[][] getMinMap() {
		int[][] minMap = copyArr(board);
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(ty, tx));
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int i=0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (inRange(ny, nx, N) && minMap[ny][nx]==0 && !(ny==ty && nx==tx)) {
					minMap[ny][nx]=minMap[cur.y][cur.x]+1;
					q.offer(new Node(ny, nx));
				}
			}
		}
		
		return minMap;
	}
	
	static void gotoGoal(Customer c) {
		int[][] minMap = getMinMap();
		
		c.len = minMap[c.ey][c.ex];
		
		c.completed = true;
	}
	
	static int[][] copyArr(int[][] a) {
		int[][] b = new int[N][N];
		for (int y=0; y<N; y++) {
			b[y] = Arrays.copyOf(a[y], N);
		}
		return b;
	}
	
	static boolean inRange(int y, int x, int N) {
		return y>=0 && y<N && x>=0 && x<N;
	}
	
	static void dbg(int[][] a) {
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				System.out.print(a[y][x] + " ");
			}
			System.out.println();
		}
		System.out.println("-----");
	}
	
}
