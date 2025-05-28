import java.util.*;
import java.io.*;

class Conference implements Comparable<Conference>{
	int start;
	int end;
	public Conference(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public int compareTo(Conference o) {
		if (this.end != o.end)
			return this.end - o.end;
		else 
			return this.start - o.start;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		List<Conference> list = new ArrayList<>();
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list.add(new Conference(start, end));
		}
		
		Collections.sort(list);
		
		int t = 0;
		int count = 0;
		for (Conference c : list) {
			if (c.start >= t) {
				count++;
				t = c.end;
			}
		}
		
		System.out.println(count);
		
	}
}