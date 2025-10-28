import java.util.*;
import java.io.*;


public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		int[] arr1 = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		int[] arr2 = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}

		
		// 로직
		List<Long> li1 = getList(N, arr1);
		List<Long> li2 = getList(M, arr2);
		
		Collections.sort(li1);
		Collections.sort(li2);
		
//		System.out.println(li1);
//		System.out.println(li2);
		
		// 정답산출
		long cnt = 0;
		int lt = 0;
		int rt = li2.size()-1;
		while (lt<li1.size() && rt>=0) {
			long sum = li1.get(lt) + li2.get(rt);
			if (sum==T) {
				long val1 = li1.get(lt);
				long val2 = li2.get(rt);
				long continuousCnt1=0;
				while (lt<li1.size() && li1.get(lt)==val1) {
					continuousCnt1++;
					lt++;
				}
				long continuousCnt2=0;
				while (rt>=0 && li2.get(rt)==val2) {
					continuousCnt2++;
					rt--;
				}
				
				cnt += (continuousCnt1*continuousCnt2);
			} else if (sum<T) {
				lt++;
			} else {
				rt--;
			}
		}
		
		System.out.println(cnt);
	}
	
	static List<Long> getList(int len, int[] arr) {
	    List<Long> li = new ArrayList<>();
	    for (int i=0; i<len; i++) {
	        long sum = 0;
	        for (int j=i; j<len; j++) {
	            sum += arr[j];
	            li.add(sum);
	        }
	    }
	    return li;
	}

}
