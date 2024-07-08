import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P1931 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> intervals = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}
		});

		for(int i = 0; i < N; i++) {
			int[] interval = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt)
						.toArray();
			intervals.add(interval);
		}
		int cnt = 0;
		while(intervals.size() > 1) {
			int[] interval = intervals.poll();
			while(!intervals.isEmpty()) {
				if(interval[1] > intervals.peek()[0]) {
					intervals.poll();
				} else {
					break;
				}
			}
			cnt++;
		}
		if(!intervals.isEmpty()){
			cnt++;
		}

		System.out.println(cnt);
	}
}
