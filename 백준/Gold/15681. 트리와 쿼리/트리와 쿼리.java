import java.util.*;
import java.io.*;

public class Main {
	
	static int N; // 정점의 수
	static int R; // 루트의 번호
	static int Q; // 쿼리의 수
	static List<List<Integer>> tree;
	static int[] count;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		tree = new ArrayList<>();
		for (int i=0; i<=N; i++) {
			tree.add(new ArrayList<>());
		}
		count = new int[N+1];
		
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			tree.get(U).add(V);
			tree.get(V).add(U);
		}
		
		dfs(R, -1);
		
		for (int i=0; i<Q; i++) {
			int q = Integer.parseInt(br.readLine());
			System.out.println(count[q]);
		}
		
	}
	
	public static void dfs(int current, int parent){
		count[current] = 1;
		
		for (int child : tree.get(current)) {
			if (child != parent) {
				dfs(child, current);
				count[current] += count[child];
			}
		}
	}
}