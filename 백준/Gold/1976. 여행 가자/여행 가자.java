import java.util.*;
import java.io.*;


public class Main {
	
	static int[] parent;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		parent = new int[N+1];
		for (int i=1; i<=N; i++) {
			parent[i] = i;
		}
		
		for (int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=1; j<=N; j++) {
				int able = Integer.parseInt(st.nextToken());
				if(able == 1) {
					union(i, j);
				}
			}
		}
		
		int[] travel = new int[M];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			travel[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<M-1; i++) {
			int a = find(travel[i]);
			int b = find(travel[i+1]);
			
			if (a!=b) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
	}
	
	static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a==b) return;
		
		if (a<b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}
}
