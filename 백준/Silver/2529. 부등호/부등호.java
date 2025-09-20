import java.io.*;
import java.util.*;

public class Main {
	
	static int k;
	static char[] a;
	
	static long maxVal = Long.MIN_VALUE;
	static long minVal = Long.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		a = new char[k];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<k; i++) {
			a[i] = st.nextToken().charAt(0);
		}
		
		dfs(0, "");
		
		String format = "%0" + (k+1) + "d";
		String max = String.format(format, maxVal);
		String min = String.format(format, minVal);
		
		System.out.println(max + "\n" + min);
		
	}
	
	// 0, ""
	// 1, "8"
	// 2, "89"
	// 3, "897"
	
	static void dfs(int depth, String s) { // 0뎁스부터 시작
		if (depth == k+1) {
			maxVal = Math.max(maxVal, Long.parseLong(s));
			minVal = Math.min(minVal, Long.parseLong(s));
			return;
		}
		
		for (int i=0; i<=9; i++) {
			// 중복금지
			boolean duplicated = false;
			for (int j=0; j<s.length(); j++) {
				if (s.charAt(j)=='0'+i) {
					duplicated = true;
				}
			}
			if (duplicated) {
				continue;
			}
			
			// 부등호 준수
			if (depth>0) {
				if (a[depth-1] == '>'&& s.charAt(depth-1)-'0' < i) {
					continue;
				} else if (a[depth-1] == '<' && s.charAt(depth-1)-'0' > i) {
					continue;
				}
			}
			
			dfs(depth+1, s + Integer.toString(i));
		}
	}
}
