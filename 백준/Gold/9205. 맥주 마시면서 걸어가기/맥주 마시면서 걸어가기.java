import java.util.*;
import java.io.*;

class Node {
	int y,x,idx,v;
	public Node(int y, int x, int idx, int v) {
		this.y=y;
		this.x=x;
		this.idx=idx;
		this.v=v;
	}
}

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc=1; tc<=T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 편의점 개수
			
			Node[] nodearr = new Node[N+2];
			
			for (int i=0; i<N+2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				nodearr[i] = new Node(y, x, i, 0);
			}
			
			Queue<Node> q = new ArrayDeque<>();
			q.offer(nodearr[0]);
			nodearr[0].v=1000;
			while (!q.isEmpty()) {
				Node cur = q.poll();
				for (int i=1; i<N+2; i++) {
					Node next = nodearr[i];
					if (next.v == 0 && walk(cur.y, cur.x, next.y, next.x, cur.v) != -1) {
						next.v = 1000;
						q.offer(next);
					}
				}
			}
			
			if (nodearr[N+1].v == 0) {
				sb.append("sad").append("\n");
			} else {
				sb.append("happy").append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static int walk(int sy, int sx, int ey, int ex, int beer) {
		int mhtdist = Math.abs(sy-ey) + Math.abs(sx-ex);
		if (mhtdist > beer) return -1;
		return beer - mhtdist;
	}

}
