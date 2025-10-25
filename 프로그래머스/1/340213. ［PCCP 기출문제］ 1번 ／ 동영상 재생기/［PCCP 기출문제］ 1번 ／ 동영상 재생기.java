class Solution {
    int opStartSec;
    int opEndSec;
    int videoLenSec;
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int posSec = parseToSec(pos);
        opStartSec = parseToSec(op_start);
        opEndSec = parseToSec(op_end);
        videoLenSec = parseToSec(video_len);
        
        posSec = skipOpening(posSec);
        
        for (String cmd : commands) {
            if (cmd.equals("next")) {
                posSec = cover(posSec + 10);
            } else if (cmd.equals("prev")) {
                posSec = cover(posSec - 10);
            }
            
            posSec = skipOpening(posSec);
        }
        
        return parseToStr(posSec);
    }
    
    int parseToSec(String str) {
        String[] sp = str.split(":");
        return Integer.parseInt(sp[0])*60 + Integer.parseInt(sp[1]);
    }
    
    String parseToStr(int sec) {
        int m = sec / 60;
        int s = sec % 60;
        return String.format("%02d:%02d", m, s);
    }
    
    int skipOpening(int posSec) {
        if (opStartSec <= posSec && posSec <= opEndSec) {
            return opEndSec;
        }
        return posSec;
    }
    
    int cover(int posSec) {
        if (posSec < 0) {
            return 0;
        } else if (posSec > videoLenSec) {
            return videoLenSec;
        } 
        return posSec;
    }

}