import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P14595 {
	static int[] union = new int[1000001];

	public static int getParent(int[] union, int x) {
		if(union[x] == x) return x;
		return union[x] = getParent(union, union[x]);
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		for(int i = 0; i < N+1; i++) {
			union[i] =i;
		}

		int cnt = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] == o2[0]) return o2[1] - o1[1];
				return o1[0] - o2[0];
			}
		});

		for(int i = 0; i < M; i++) {
			int[] input = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
			pq.add(input);
		}

		while(!pq.isEmpty()) {
			int[] input = pq.poll();
			int x = getParent(union, input[0]);
			int y = getParent(union, input[1]);

			if (x == y)
				continue;
			for (int j = input[1]; j > input[0]; j--) {
				int parent = getParent(union, j);
				if (parent == x)
					break;
				union[j] = x;
			}
		}

		for(int i = 1; i <= N; i++) {
			if(union[i] == i) cnt++;
		}
		System.out.println(cnt);
	}
}
/*
*
*
* [ 1|2 | | | | | | | | | | | | | | ]
*
* 1 2 3 4 5
* 1 1 3 4 5
* 1 1 1 1 5
*
* */