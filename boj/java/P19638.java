import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P19638 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();

		int CENTI = input[1];

		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
		for (int i = 0; i < input[0]; i++) {
			int height = Integer.parseInt(br.readLine());
			pq.add(height);
		}

		for (int i = 0; i < input[2]; i++) {
			int nextHeight = pq.poll();
			if (nextHeight >= CENTI) {
				pq.add(Math.max(nextHeight / 2, 1));
			} else {
				System.out.println("YES");
				System.out.println(i);
				return;
			}

		}

		if(pq.peek() >= CENTI) {
			System.out.println("NO");
			System.out.println(pq.poll());
		} else {
			System.out.println("YES");
			System.out.println(input[2]);
		}
		return;
	}
}
