import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P1697 {
	static int MAX = 100001;
	static boolean[] v = new boolean[MAX+1];
	static Queue<Integer> q = new LinkedList<>();
	static int level = 0;
	static int qs = 1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		q.add(input[0]);

		while(!q.isEmpty()) {
			for(int i = 0; i < qs; i++) {
				int cur = q.poll();

				if(v[cur]) continue;
				v[cur] = true;

				if(cur == input[1]) {
					System.out.println(level);
					return;
				}
				if(cur + 1 <= MAX) {
					q.add(cur + 1);
				}
				if(cur - 1 >= 0) {
					q.add(cur - 1);
				}
				if(cur * 2 <= MAX) {
					q.add(cur*2);
				}
			}
			qs = q.size();
			level++;
		}
	}
}
