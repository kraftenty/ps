import java.util.*;

class Solution {
	
	class Node {
		int num;
		int x;
		int y;
		
		Node left;
		Node right;
		
		public Node(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}
	}
	
	public Node[] nodeArr;
			
    public int[][] solution(int[][] nodeinfo) {
    	nodeArr = new Node[nodeinfo.length];
    	for (int i=0; i<nodeinfo.length; i++) {
    		nodeArr[i] = new Node(i+1, nodeinfo[i][0], nodeinfo[i][1]);
    	}
    	
    	// y기준 내림차순 정렬, y가 같으면 x기준 오름차순 정렬
    	Arrays.sort(nodeArr, (o1, o2) -> {
    		if (o1.y != o2.y) {
    			return o2.y - o1.y;
    		} else {
    			return o1.x - o2.x;
    		}
    	});
    	
    	// 트리 구성
    	Node root = nodeArr[0];
    	for (int i=1; i<nodeArr.length; i++) {
    		Node parent = root;
    		
    		while (true) {
    			if (nodeArr[i].x < parent.x) {
        			if (parent.left == null) {
        				parent.left = nodeArr[i];
        				break;
        			} else {
        				parent = parent.left;
        			}
        		} else {
        			if (parent.right == null) {
        				parent.right = nodeArr[i];
        				break;
        			} else {
        				parent = parent.right;
        			}
        		}
    		}
    		
    	}
    	
    	
    	List<Integer> preOrderList = new ArrayList<>();
    	List<Integer> postOrderList = new ArrayList<>();
    	preOrder(root, preOrderList);
    	postOrder(root, postOrderList);
    	
    	
    	
        int[][] answer = new int[2][nodeArr.length];
        answer[0] = preOrderList.stream().mapToInt(Integer::intValue).toArray();
        answer[1] = postOrderList.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
    
    void preOrder(Node cur, List<Integer> result) {
    	if (cur == null) {
    		return;
    	}
    	
    	result.add(cur.num);
    	preOrder(cur.left, result);
    	preOrder(cur.right, result);
    }
    
    void postOrder(Node cur, List<Integer> result) {
    	if (cur == null) {
    		return;
    	}
    	
    	postOrder(cur.left, result);
    	postOrder(cur.right, result);
    	result.add(cur.num);
    }
}