import java.util.*;
import java.io.*;


public class Main {
	
	static int[] parent;
	static int[] size;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		for (int tc=0; tc<T; tc++) {
			int F = Integer.parseInt(br.readLine());
			parent = new int[F*2];
			size = new int[F*2];
			for (int i=0; i<F*2; i++) {
				parent[i] = i;
				size[i] = 1;
			}
			
			int order = 0;
			Map<String, Integer> map = new HashMap<>();
			
			for (int f=0; f<F; f++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String a = st.nextToken();
				String b = st.nextToken();
				
				if (!map.containsKey(a)) {
					map.put(a, order++);
				}
				if (!map.containsKey(b)) {
					map.put(b, order++);
				}
				
				int root = union(map.get(a), map.get(b));
				
				sb.append(size[root]).append("\n");
			}
		}
		
		System.out.println(sb.toString());
	}
	
	static int find(int num) {
		if (num == parent[num]) {
			return num;
		}
		
		return parent[num] = find(parent[num]);
	}
	
	static int union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a != b) {
			parent[b] = a;
			size[a] += size[b];
		}
		
		return a;
	}
}
