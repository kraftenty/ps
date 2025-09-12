import java.io.*;
import java.util.*;


public class Main {

	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		 
		StringBuilder sb = new StringBuilder();
		for (int t=0; t<T; t++) {
			String W = br.readLine();
			char[] charArr = W.toCharArray();
			int K = Integer.parseInt(br.readLine());
			
			Map<Character, Integer> map = new HashMap<>(); // 대상 문자, 대상 문자 개수
			for (char c : charArr) {
				map.put(c, map.getOrDefault(c, 0) + 1);
			}
			
			int answer1 = Integer.MAX_VALUE; // 어떤 문자를 정확히 K개를 포함하는 가장 짧은 연속 문자열의 길이
			int answer2 = Integer.MIN_VALUE; // 어떤 문자를 정확히 K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이
			boolean hasResult = false; 
			for (Character c : map.keySet()) {
				// c의 개수가 K개보다 없으면 패스
				if (map.get(c) < K) {
					continue;
				}
				
				hasResult = true;
				

				// c가 있는 위치를 담은 배열 arr 만들기
				int[] arr = new int[map.get(c)];
				int p=0;
				for (int i=0; i<charArr.length; i++) {
					if (charArr[i] == c) {
						arr[p] = i;
						p++;
					}
				}
				
				
				// 답 구하기
				int windowSize = K-1;
				for (int s=0; s<arr.length; s++) {
					int e = s + windowSize;
					answer1 = Math.min(answer1, arr[e]-arr[s]+1);
					answer2 = Math.max(answer2, arr[e]-arr[s]+1);
					
					if (e >= arr.length - 1) {
						break;
					}
				}
				
			}
			
			if (hasResult) {
				sb.append(answer1 + " " + answer2 + "\n");
			} else {
				sb.append("-1\n");
			}
		}
		
		System.out.println(sb.toString());
	}

}