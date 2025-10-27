import java.util.*;
import java.io.*;

class Node {
	int floor, cnt;
	public Node(int floor, int cnt) {
		this.floor=floor;
		this.cnt=cnt;
	}
}

public class Main {

	static int F; // 최고층
	static int S; // start
	static int G; // goal
	static int U; // 위로
	static int D; // 아래로
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		
		int answer = bfs();
		if (answer == -1) {
			System.out.println("use the stairs");
		} else {
			System.out.println(answer);
		}
	}
	
	static int bfs() {
		Queue<Node> q = new ArrayDeque<>();
		boolean[] v = new boolean[F+1];
		q.offer(new Node(S, 0));
		v[S] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (cur.floor == G) {
				return cur.cnt;
			}
			
			if ((cur.floor+U)<=F && !v[cur.floor+U]) {
				v[cur.floor+U] = true;
				q.offer(new Node(cur.floor+U, cur.cnt+1));
			}
			
			if ((cur.floor-D)>=1 && !v[cur.floor-D]) {
				v[cur.floor-D] = true;
				q.offer(new Node(cur.floor-D, cur.cnt+1));
			}
		}
		
		return -1;
	}

	
}

// 3 7 5 2 6 1 4
