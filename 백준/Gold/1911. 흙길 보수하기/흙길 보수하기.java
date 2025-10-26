import java.util.*;
import java.io.*;

public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		List<int[]> puddles = new ArrayList<>();
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			puddles.add(new int[] {s, e});
		}
		
		puddles.sort((a,b) -> {
			return a[0] - b[0];
		});
		
		int nulEnd = 0;
		int nulCnt = 0;
		for (int[] puddle : puddles) {
			int s = puddle[0];
			int e = puddle[1];
			
			if (s < nulEnd) {
				s = nulEnd;
			}
			
			if (s >= e) continue;
			
			int tmpCnt = 0;
			tmpCnt += ((e-s) / L);
			if ((e-s)%L != 0) {
				tmpCnt++;
			}
			
			nulCnt += tmpCnt;
			nulEnd = s + L * tmpCnt;
			
		}
		
		System.out.println(nulCnt);
	}
	
}