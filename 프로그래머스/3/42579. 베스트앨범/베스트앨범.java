import java.util.*;
import java.util.stream.*;

class Music implements Comparable<Music> {
	public int number;
	public int play;
	
	public Music (int number, int play) {
		this.number = number;
		this.play = play;
	}
	
	public int compareTo(Music o) {
		if (this.play != o.play) {
			return o.play - this.play;
		} else {
			return this.number - o.number;
		}
	}
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
    	
    	// 장르, 음악들
    	Map<String, List<Music>> genreMap = new HashMap<>();
    	// 장르, 플레이 합
    	Map<String, Integer> playMap = new HashMap<>();
    	
    	for (int i=0; i<genres.length; i++) {
    		if (!genreMap.containsKey(genres[i])) {
    			genreMap.put(genres[i], new ArrayList<>());
    			playMap.put(genres[i], 0);
    		}
    		genreMap.get(genres[i]).add(new Music(i, plays[i]));
    		playMap.put(genres[i], playMap.get(genres[i]) + plays[i]);
    	}
    	
    	List<Map.Entry<String, Integer>> sortedGenreList = playMap.entrySet().stream()
    		.sorted((o1, o2) -> o2.getValue() - o1.getValue())
    		.collect(Collectors.toList());

    	List<Integer> answer = new ArrayList<>();
    	for(Map.Entry<String, Integer> entry : sortedGenreList) {
    		String genre = entry.getKey();
    		List<Music> musicList = genreMap.get(genre);
    		Collections.sort(musicList);
    		for (int i=0; i<musicList.size(); i++) {
    			if (i >= 2) break;
    			answer.add(musicList.get(i).number);
    		}
    	}
    	
    	return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}