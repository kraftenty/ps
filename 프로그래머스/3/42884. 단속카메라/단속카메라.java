import java.util.*;

class Car {
    int s;
    int e;
    public Car(int s, int e) {
        this.s=s;
        this.e=e;
    }
    
    
}

class Solution {
    
    static int N;
    
    public int solution(int[][] routes) {
        N = routes.length;
        List<Car> li = new ArrayList<>();
        for (int i=0; i<N; i++) {
            Car car = new Car(routes[i][0], routes[i][1]);
            li.add(car);
        }
        Collections.sort(li, (a, b) -> {
           return a.e - b.e; 
        });
        
        // 메인로직
        int answer = 0;
        int cam = -30001;
        for (Car c : li) {
            if (c.s > cam) {
                answer++;
                cam = c.e;
            }
        }        
        return answer;
    }
}