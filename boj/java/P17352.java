import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class P17352 {

	static int[] union = new int[300001];


	public static int getParent(int x) {
		if(union[x] == x) return x;
		union[x] = getParent(union[x]);
		return union[x];
	}

	public static void union(int p1, int p2) {
		int x = getParent(p1);
		int y = getParent(p2);
		union[x] = y;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		for(int i = 0; i <= N; i++) {
			union[i] = i;
		}
		for(int i = 0; i < N-2; i++) {
			int[] edge = Arrays.stream(br.readLine().split(" "))
						  .mapToInt(Integer::parseInt)
						  .toArray();
			union(edge[0], edge[1]);
		}

		HashSet<Integer> set = new HashSet<>();

		for(int i = 1; i <= N; i++) {
			set.add(getParent(i));
		}

		int[] result = set.stream().mapToInt(Integer::intValue).toArray();


		System.out.println(result[0] + " " + result[1]);

	}
}
