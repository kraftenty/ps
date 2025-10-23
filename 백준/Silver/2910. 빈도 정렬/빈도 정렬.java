import java.util.*;
import java.io.*;

class Num implements Comparable<Num> {
	int num;
	int firstIdx;
	int cnt;
	public Num(int num, int firstIdx, int cnt) {
		this.num=num; 
		this.firstIdx=firstIdx;
		this.cnt=cnt;
	}
	
	public int compareTo(Num o) {
		if (this.cnt != o.cnt) {
			return o.cnt - this.cnt;
		}
		return this.firstIdx - o.firstIdx;
	}
}

public class Main {

	static int N;
	static int C;
	
	static Map<Integer, Num> map;
	static List<Num> li;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new HashMap<>();
		li = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			int n = Integer.parseInt(st.nextToken());
			if (map.containsKey(n)) {
				map.get(n).cnt++;
			} else {
				Num num = new Num(n, i, 1);
				map.put(n, num);
				li.add(num);
			}
		}
		
		Collections.sort(li);
		
		StringBuilder sb = new StringBuilder();
		for (Num num : li) {
			for (int i=0; i<num.cnt; i++) {
				sb.append(num.num).append(" ");
			}
		}
		System.out.println(sb.toString());
	}
	
}