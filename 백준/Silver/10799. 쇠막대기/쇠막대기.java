import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		
		Stack<Integer> stack = new Stack<>();
		int answer = 0;
		
		for (int i=0; i<S.length(); i++) {
			if (S.charAt(i) == '(') {		// 여는괄호
				stack.push(1);
			} else {						// 닫는괄호
				stack.pop();
				if (S.charAt(i-1) == '(') { // 레이저
					answer += stack.size();
				} else {					// 레이저 아님
					answer++;
				}
			} 
		}
		
		System.out.println(answer);
	}
}