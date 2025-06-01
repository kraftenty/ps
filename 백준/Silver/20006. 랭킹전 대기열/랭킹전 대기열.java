import java.util.*;
import java.io.*;

public class Main {
	
	static class Player implements Comparable<Player> {
		public int level;
		public String name;
		
		public Player(int l, String n) {
			this.level = l;
			this.name = n;
		}
		
		@Override
		public int compareTo(Player o) {
			return this.name.compareTo(o.name);
		}
		
		@Override
		public String toString() {
			return this.level + " " + this.name;
		}
	}
	
	static class Room {
		public List<Player> list;
		public boolean started;
		public int roomLevel;
		
		public Room(Player p) {
			this.list = new ArrayList<>();
			this.started = false;
			this.roomLevel = p.level;
			list.add(p);
			if (list.size() == m) {
				started = true;
			}
		}
		
		public boolean enter(Player p) {
			if (started) {
				return false;
			}
			if (list.size() >= m || p.level < roomLevel-10 || p.level > roomLevel+10) {
				return false;
			}
			list.add(p);
			if (list.size() == m) {
				started = true;
			}
			return true;
		}
		
		public void print(BufferedWriter bw) throws IOException {
			StringBuilder sb = new StringBuilder();
			if (started) {
				sb.append("Started!\n");
			} else {
				sb.append("Waiting!\n");
			}
			Collections.sort(list);
			for (Player p : list) {
				sb.append(p + "\n");
			}
			bw.write(sb.toString());
		}
	}

	static int p; // 플레이어 수
	static int m; // 방의 정원
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		List<Room> rooms = new ArrayList<>();
		
		
		for (int i=0; i<p; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken()); // 레벨
			String n = st.nextToken(); // 닉네임
			
			Player p = new Player(l, n);
			
			// 방 진입 시도
			boolean entered = false;
			for (Room r : rooms) {
				if (r.enter(p)) {
					entered = true;
					break;
				}
			}
			
			// 방 생성
			if (!entered) {
				Room room = new Room(p);
				rooms.add(room);
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (Room r : rooms) {
			r.print(bw);
		}
		bw.flush();
 	}

}