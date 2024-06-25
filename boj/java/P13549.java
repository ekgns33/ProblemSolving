import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class P13549 {
	private static final int MAX = 100001;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// Read input values
		int[] input = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();

		int start = input[0];
		int end = input[1];

		int[] dist = new int[MAX + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)-> a[1] - b[1]);
		pq.add(new int[] {start, 0});

		while (!pq.isEmpty()) {
			int[] currentNode = pq.poll();
			int currentPosition = currentNode[0];
			int currentDistance = currentNode[1];

			if (dist[currentPosition] < currentDistance) continue;

			// Add possible positions to the queue
			if (currentPosition + 1 <= MAX && dist[currentPosition + 1] > currentDistance + 1) {
				dist[currentPosition + 1] = currentDistance  + 1;
				pq.add(new int[]{currentPosition + 1, currentDistance + 1});
			}
			if (currentPosition - 1 >= 0 && dist[currentPosition - 1] > currentDistance + 1) {
				dist[currentPosition -1] = currentDistance  + 1;
				pq.add(new int[]{currentPosition - 1, currentDistance + 1});
			}
			if (currentPosition * 2 <= MAX && dist[currentPosition * 2] > currentDistance) {
				dist[currentPosition * 2] = currentDistance;
				pq.add(new int[]{currentPosition * 2, currentDistance});
			}
		}

		System.out.println(dist[end]);
	}
}
