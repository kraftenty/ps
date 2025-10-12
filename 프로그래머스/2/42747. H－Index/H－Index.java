import java.util.*;

class Solution {
    public int solution(int[] citations) {
        // 정렬을 위한 박싱
        Integer[] arr = new Integer[citations.length];
        for (int i=0; i<arr.length; i++) {
            arr[i] = citations[i];
        }
        Arrays.sort(arr, Collections.reverseOrder());
        
        int h=0;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] >= (i+1)) {
                h = i+1;
            } else {
                break;
            }
        }
        
        return h;
    }
}

// 6 5 3 1 0