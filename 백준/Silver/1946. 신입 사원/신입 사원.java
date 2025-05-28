import java.util.*;
import java.io.*;

class Person implements Comparable<Person> {
	int a;
	int b;
	public Person(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public int compareTo(Person o) {
		if (this.a != o.a) {
			return this.a - o.a; // a 오름차순
		} else {
			return this.b - o.b; // b 오름차순
		}
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());

			List<Person> list = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				list.add(new Person(a, b));
			}

			Collections.sort(list);

			int count = 1;
			int minVal = list.get(0).b;
			for (int i = 1; i < N; i++) {
				if (list.get(i).b < minVal) {
					count++;
					minVal = list.get(i).b;
				}
			}
			System.out.println(count);
		}
		
	}
}