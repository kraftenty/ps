import java.util.*;

class Solution {
	
	private List<Integer>[] tree;
	private int answer;
	
    public int solution(int[] info, int[][] edges) {
    	
    	// 트리 제작
    	tree = new ArrayList[info.length];
    	for (int i=0; i<info.length; i++) {
    		tree[i] = new ArrayList<>();
    	}
    	for (int i=0; i<edges.length; i++) {
    		int[] edge = edges[i];
    		tree[edge[0]].add(edge[1]);
    	}
    	
    	// DFS로 해결
    	answer = 0;
    	List<Integer> nextNodes = new ArrayList();
    	nextNodes.add(0);
    	dfs(0, 0, 0, nextNodes, info);
    	
        return answer;
    }
    
    private void dfs(int cur, int sheep, int wolf, List<Integer> nextNodes, int[] info) {
    	if (info[cur] == 0) {
    		sheep++;
    	} else {
    		wolf++;
    	}
    	
    	if (wolf >= sheep) {
    		return;
    	}
    	
    	answer = Math.max(answer, sheep);
    	
    	// cur의 자식만 갈수있는게 아니라, 방문했던 곳으로 건너뛸수도 있음
    	nextNodes.remove(Integer.valueOf(cur));
    	nextNodes.addAll(tree[cur]);
    	for (int next : nextNodes) {
    		dfs(next, sheep, wolf, new ArrayList<>(nextNodes), info);
    	}
    }
}