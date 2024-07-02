import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P1043 {
	public static int getRoot(int[] union, int cur) {
		if(union[cur] == cur) return cur;
		union[cur] = getRoot(union, union[cur]);
		return union[cur];
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] truth = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		int[][] parties = new int[input[1]+1][input[1]+1];
		int[] union = new int[input[0] + 1];

		for(int i = 0; i < union.length; i++) {
			union[i] = i;
		}

		for(int i = 0; i < input[1]; i++) {
			//read party participants
			parties[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if(parties[i].length == 2 ) continue;
			for(int j = 1; j < parties[i].length-1; j++) {
				int x = getRoot(union, parties[i][j]);
				int y = getRoot(union, parties[i][j + 1]);
				union[x] = y;
			}
		}


		Set<Integer> set = new HashSet<>();
		for(int i = 1; i < truth.length; i++) {
			set.add(getRoot(union, truth[i]));
		}



		int ret = 0;
		for(int i = 0; i < input[1]; i++) {
			int cnt = 1;
			for(int j = 1; j < parties[i].length; j++) {
				if(set.contains(getRoot(union, parties[i][j]))) {
					cnt = 0;
					break;
				}
			}
			ret += cnt;
		}
		System.out.println(ret);
	}
}
