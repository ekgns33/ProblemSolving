import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class P11000 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N;
		N = Integer.parseInt(br.readLine());
		Map<Integer, Integer> intervals = new TreeMap<>();

		for(int i = 0; i < N; i++) {
			int[] interval = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			intervals.putIfAbsent(interval[0], 0);
			intervals.putIfAbsent(interval[1], 0);
			intervals.put(interval[0], intervals.get(interval[0])+1);
			intervals.put(interval[1], intervals.get(interval[1])-1);
		}
		int cur = 0;
		int maxRoom = 0;
		for(Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
			cur += entry.getValue();
			maxRoom = Math.max(maxRoom, cur);
		}
		System.out.println(maxRoom);
	}
}

/*
*
*
* 0 1 2 3 4 5 6 7 8 9 10
*   1   -1
*     1   -1
*       1  -1
* */