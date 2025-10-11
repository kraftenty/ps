import java.util.*;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet<>();
        int[] arr = new int[elements.length*2];
        
        for (int i=0; i<elements.length; i++) {
            arr[i] = elements[i];
            arr[elements.length+i] = elements[i];
        }
        
        for (int len=1; len<=elements.length; len++) {
            for (int st=0; st<elements.length; st++) {
                int sum = 0; 
                for (int i=st; i<st+len; i++) {
                    sum += arr[i];
                }
                set.add(sum);
            }
        }
        
        int answer = set.size();
        return answer;
    }
}

// 7 9 1 1 4 7 9 1 1 4