import java.util.*;
import java.io.*;

public class Main {

	static int N; // 메모장에 적은 키워드 개수
	static int M; // 블로그에 쓴 글의 개수
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		
		Set<String> memo = new HashSet<>();
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			memo.add(s);
		}
		
		StringBuilder sb = new StringBuilder();
		for (int j=0; j<M; j++) {
			String[] keywords = br.readLine().split(",");
			for (String k : keywords) {
				memo.remove(k);
			}
			sb.append(memo.size() + "\n");
		}
		System.out.println(sb);
	}

}