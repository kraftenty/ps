import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		while (true) {
			String s = br.readLine();
			if (s.equals("end")) {
				break;
			}
			char[] chArr = s.toCharArray();
			int ocnt = 0;
			int xcnt = 0;
			for (int i=0; i<chArr.length; i++) {
				if (chArr[i] == 'O') {
					ocnt++;
				} else if (chArr[i] == 'X') {
					xcnt++;
				}
			}
			
			boolean answer = true;
			
			// O가 X보다 많거나, X가 1개 이상 많으면 컷
			if (ocnt > xcnt || xcnt > ocnt+1) {
				answer = false;
			}
			
			Map<Character, Integer> bingoMap = getBingoCnt(chArr);
			int dotCnt = getDotCnt(chArr);
			
			// .이 있는데 빙고가 없으면 컷
			if (answer && dotCnt>0 && bingoMap.get('X')==0 && bingoMap.get('O')==0) {
				answer = false;
			}
			
			// O가 이겼는데 X가 더 많으면 컷
			if (answer && bingoMap.get('O')>0 && (xcnt > ocnt)) {
				answer = false;
			}
			
			/**
			 * XOX
			 * OXO
			 * XO.
			 */
			// X가 이겼는데 O개수랑 X개수가 똑같으면 컷
			if (answer && bingoMap.get('X')>0 && (xcnt==ocnt)) {
				answer = false;
			}
			
			
			
			if (answer) {
				sb.append("valid\n");
			} else {
				sb.append("invalid\n");
			}
		}
		
		System.out.println(sb.toString());

	}
	
	// 리턴값: X빙고개수, O빙고개수
	static Map<Character, Integer> getBingoCnt(char[] a) {
		Map<Character, Integer> map = new HashMap<>();
		map.put('X', 0);
		map.put('O', 0);
		// 가로빙고    0,1,2,   3,4,5,   6,7,8
		for(int i=0; i<7; i+=3) {
			if (a[i]!='.' && a[i]==a[i+1] && a[i+1]==a[i+2]) {
				map.put(a[i], map.get(a[i]) + 1);
			}
		}
		
		// 세로빙고    0,3,6,   1,4,7,   2,5,8
		for(int i=0; i<3; i++) {
			if (a[i]!='.' && a[i]==a[i+3] && a[i+3]==a[i+6]) {
				map.put(a[i], map.get(a[i]) + 1);
			}
		}
		
		// 대각선빙고
		if (a[0]!='.' && a[0]==a[4] && a[4]==a[8]) {
			map.put(a[0], map.get(a[0]) + 1);
		}
		if (a[2]!='.' && a[2]==a[4] && a[4]==a[6]) {
			map.put(a[2],map.get(a[2]) + 1);
		}
		
		return map;
	}
	
	static int getDotCnt(char[] a) {
		int cnt = 0;
		for(int i=0; i<a.length; i++) {
			if (a[i]=='.') {
				cnt++;
			}
		}
		return cnt;
	}
	
}
