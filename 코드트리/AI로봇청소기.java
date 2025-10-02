// https://www.codetree.ai/ko/frequent-problems/samsung-sw/problems/ai-robot/description
package ps;

import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
	int y;
	int x;
	int l;
	public Node (int y, int x) {
		this.y=y;
		this.x=x;
		this.l=Integer.MAX_VALUE;
	}
	public Node (int y, int x, int l) {
		this.y=y;
		this.x=x;
		this.l=l;
	}
	
	@Override
	public int compareTo(Node o) {
		if (this.l != o.l) {
			return this.l - o.l;
		} else if (this.y != o.y) {
			return this.y - o.y;
		} else {
			return this.x - o.x;
		}
	}
}


public class Main {
	
	static int N;
	static int K;
	static int[][] board;
	
	static Node[] agentArr;
	static int[][] agentId;
	
	final static int[] dy = {0, 1, 0, -1};
	final static int[] dx = {1, 0, -1, 0};
	
	final static int[][][] clean = {
			{
				{0, 1, 0},
				{0, 1, 1},
				{0, 1, 0}
			},
			{
				{0, 0, 0},
				{1, 1, 1},
				{0, 1, 0}
			},
			{
				{0, 1, 0},
				{1, 1, 0},
				{0, 1, 0}
			},
			{
				{0, 1, 0},
				{1, 1, 1},
				{0, 0, 0}
			}
	};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		board = new int[N][N];
		for (int y=0; y<N; y++) {
			st = new StringTokenizer(br.readLine());
			for (int x=0; x<N; x++) {
				board[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		agentArr = new Node[K+1];
		agentId = new int[N][N];
		for (int id=1; id<=K; id++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			agentArr[id] = new Node(r-1, c-1);
			agentId[r-1][c-1] = id;
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<L; i++) {
			n1_moving();

			n2_clean();
			
			n3_add();
			
			n3_spread();
			
			int res = n4_calc();
			sb.append(res + "\n");
			if (res==0) break;
		}
		System.out.println(sb.toString());
	}
	
	static void n1_moving() {
		for (int id=1; id<=K; id++) {
			Node agent = agentArr[id];
			if (board[agent.y][agent.x] != 0) continue;
			
			Node goal = bfs(agent.y, agent.x);
			if (goal.y==-1 && goal.x==-1) {
				continue;
			}
			
			agentId[agent.y][agent.x]= 0;
			agent.y = goal.y;
			agent.x = goal.x;
			agentId[agent.y][agent.x]= id; 
		}
	}
	
	static Node bfs(int sy, int sx) {
		List<Node> candidates = new ArrayList<>();
		Queue<Node> q = new ArrayDeque<>();
		boolean[][] v = new boolean[N][N];
		q.add(new Node(sy, sx, 0));
		v[sy][sx] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for (int i=0; i<4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (inRange(ny, nx) && !v[ny][nx] 
						&& agentId[ny][nx]==0 && board[ny][nx]>=0) {
					v[ny][nx] = true;
					Node newNode = new Node(ny, nx, cur.l + 1);
					if (board[ny][nx] > 0) {
						candidates.add(newNode);
					}
					q.add(newNode);
				}
			}
		}
		
		
		if (candidates.isEmpty()) {
			return new Node(-1, -1);
		}
		Collections.sort(candidates);
		return candidates.get(0);
	}
	
	static void n2_clean() {
		for (int id=1; id<=K; id++) {
			Node agent = agentArr[id];
			int d = testClean(agent.y, agent.x);
			
			realClean(agent.y, agent.x, d);
		}
	}
	
	static int testClean(int cy, int cx) {
		int maxSum = -1;
		int resD = -1;
		for (int d=0; d<4; d++) {
			int tempSum = 0;
			for (int y=0; y<=2; y++) {
				for (int x=0; x<=2; x++) {
					int ry = cy-1+y;
					int rx = cx-1+x;
					if (clean[d][y][x]==1 && inRange(ry, rx) && board[ry][rx]>0) {
						if (board[ry][rx] > 20) {
							tempSum += 20;
						} else {
							tempSum += board[ry][rx];
						}
					}
				}
			}
			if (tempSum > maxSum) {
				maxSum = tempSum;
				resD = d;
			}
		}
		
		return resD;
	}
	
	static void realClean(int cy, int cx, int d) {
		for (int y=0; y<=2; y++) {
			for (int x=0; x<=2; x++) {
				int ry = cy-1+y;
				int rx = cx-1+x;
				if (clean[d][y][x]==1 && inRange(ry, rx) && board[ry][rx]>0) {
					board[ry][rx] -= 20;
					if (board[ry][rx] < 0) {
						board[ry][rx] = 0;
					}
				}
			}
		}
	}
	
	static void n3_add() {
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (board[y][x] > 0) {
					board[y][x]+=5;
//					if (board[y][x] > 100) {
//						board[y][x] = 100;
//					}
				}
			}
		}
	}
	
	static void n3_spread() {
		int[][] b = copyArr(board);
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (b[y][x] == 0) {
					int sum = 0;
					for (int i=0; i<4; i++) {
						int ny = y + dy[i];
						int nx = x + dx[i];
						if (inRange(ny, nx) && b[ny][nx] > 0) {
							sum += b[ny][nx];
						}
					}
					
					board[y][x] = sum / 10;
				}
			}
		}
	}
		
	static int n4_calc() {
		int cnt = 0;
		for (int y=0; y<N; y++) {
			for (int x=0; x<N; x++) {
				if (board[y][x] > 0) {
					cnt += board[y][x];
				}
			}
		}
		
		return cnt;
	}
	
	static int[][] copyArr(int[][] a) {
		int[][] b = new int[N][N];
		for (int y=0; y<N; y++) {
			b[y] = Arrays.copyOf(a[y], N);
		}
		return b;
	}
	
	static boolean inRange(int y, int x) {
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
