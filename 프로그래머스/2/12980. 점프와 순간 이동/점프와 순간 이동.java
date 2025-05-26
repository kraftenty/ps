import java.util.*;

class Solution {
	public int solution(int n) { 
        int count = 0;
        while (n > 0) {
        	if (n % 2 == 1) {   // 점프
        		n--;
            	count++;
        	}
            n /= 2; // 순간이동
        }
        return count;
	}
}
