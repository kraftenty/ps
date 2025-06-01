import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int length = S.length();
		char[] ch = S.toCharArray();
		boolean[] enable = new boolean[length];
		Arrays.fill(enable, true);
		
		// 1의 개수, 0의 개수 구하기
		int oneCount = 0;
		for (int i=0; i<length; i++) {
			if (ch[i] == '1') {
				oneCount++;
			}
		}
		int zeroCount = length - oneCount;
		
		oneCount /= 2;
		zeroCount /= 2;
		
		// 1은 앞에서부터 제거, 0은 뒤에서부터 제거 -> 사전순으로 가장 빠른 것
		for(int i=0; i<length; i++) {
			if (oneCount > 0 && ch[i] == '1') {
				oneCount--;
				enable[i] = false;
			}
		}
		
		for (int i=length-1; i>=0; i--) {
			if (zeroCount > 0 && ch[i] == '0') {
				zeroCount--;
				enable[i] = false;
			}
		}
		
		// 답 출력
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<length; i++) {
			if (enable[i]) {
				sb.append(ch[i]);
			}
		}
		System.out.println(sb);
	}
}