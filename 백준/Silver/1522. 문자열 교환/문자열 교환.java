import java.io.*;
import java.util.*;

public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		
		// a 개수 세기
		int aCount = 0;
		for (int i=0; i<s.length(); i++) {
			if (s.charAt(i) == 'a') {
				aCount++;
			}
		}
		
		// 슬라이딩 윈도우로 해결 
		// 원형 문자열이므로 뒤에 s를 더붙임
		s += s;
		
		int minChangeCount = Integer.MAX_VALUE;
		for (int i=0; i<=s.length() - aCount; i++) {
			int bCount = 0;
			for (int j=i; j<(i+aCount); j++) {
				if (s.charAt(j) == 'b') {
					bCount++;
				}
			}
			
			minChangeCount = Math.min(minChangeCount, bCount);
		}
		
		System.out.println(minChangeCount);
		 
	}
}

/**
	abababababababa 에서 a의 개수는 8개 -> 윈도우 크기=8
	
	따라서
	[abababab]abababa
	a[babababa]bababa
	...
	abababa[babababa] 
	
	이렇게 쭉 윈도우 슬라이딩하면서 각 윈도우의 b개수 세면됨
*/