class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        
        int hp = health;
        int sec = 1;
        int bandSec = 0;
        int attackIdx = 0;
        while (true) {
            // 종료조건
            if (hp <= 0) {
                return -1;
            }
            if (attackIdx == attacks.length) {
                return hp;
            }
            
            
            // 몬스터공격
            if (attacks[attackIdx][0] == sec) {
                hp -= attacks[attackIdx][1];
                attackIdx++;
                bandSec = 0; // 연속성공 초기화
            } else {
                // 몬스터 공격X -> 붕대감기
                hp += bandage[1];
                bandSec++;
                if (bandSec == bandage[0]) {
                    bandSec = 0;
                    hp += bandage[2];
                }
                if (hp > health) hp = health;
            }
            
            sec++;
        }
    }
}