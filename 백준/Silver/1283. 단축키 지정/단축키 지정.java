import java.io.*;
import java.util.*;


public class Main {

	static int N; // 옵션 개수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		Set<Character> set = new HashSet<>();
		StringBuilder sb = new StringBuilder(); // 정답 저장용
		
		for (int i=0; i<N; i++) {
			boolean completed = false; // 완료 플래그
			String s = br.readLine();
			
			// 1. 먼저 하나의 옵션에 대해 왼쪽에서부터 오른쪽 순서로 단어의 첫 글자가 이미 단축키로 지정되었는지 살펴본다. 
			// 만약 단축키로 아직 지정이 안 되어있다면 그 알파벳을 단축키로 지정한다.
			String[] strArr = s.split(" ");
			for (int j=0; j<strArr.length; j++) {
				char ch = Character.toLowerCase(strArr[j].charAt(0));
				if (!set.contains(ch)) {
					// 단축키 지정
					set.add(ch);
					// 전
					for (int k=0; k<j; k++) {
						sb.append(strArr[k]).append(" ");
					}
					// 중 (단축키포함 단어 존재)
					sb.append("[").append(strArr[j].charAt(0)).append("]");
					for (int k=1; k<strArr[j].length(); k++) {
						sb.append(strArr[j].charAt(k));	
					}
					
					// 후
					if (j+1 < strArr.length) {
						for (int k=j+1; k<strArr.length; k++) {
							sb.append(" ").append(strArr[k]);
						}
					}
					sb.append("\n");
					completed = true;
					break;
				}
			}
			
			// 2. 만약 모든 단어의 첫 글자가 이미 지정이 되어있다면 왼쪽에서부터 차례대로 알파벳을 보면서 
			// 단축키로 지정 안 된 것이 있다면 단축키로 지정한다.
			if (!completed) {
				char[] chArr = s.toCharArray();
				boolean innerCompleted = false;
				StringBuilder innerSb = new StringBuilder();
				for (int j=0; j<chArr.length; j++) {
					char ch = Character.toLowerCase(chArr[j]);
					if (!innerCompleted && ch != ' ' && !set.contains(ch)) {
						set.add(ch);
						
						innerSb.append("[").append(chArr[j]).append("]");
						
						completed = true;
						innerCompleted = true;
					} else {
						innerSb.append(chArr[j]);
					}
				}
				innerSb.append("\n");
				
				if (innerCompleted) {
					sb.append(innerSb);
				}
			}
			
			// 3. 어떠한 것도 단축키로 지정할 수 없다면 그냥 놔두며 대소문자를 구분치 않는다.
			if (!completed) {
				sb.append(s).append("\n");				
			}
		}
		
		
		System.out.println(sb.toString());
	}
	
}
