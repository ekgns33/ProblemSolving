import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P20040 {
	static int[] union = new int[500001];
	static int[] rank = new int[500001];

	public static int getParent(int x) {
		if(union[x] == x) return x;
		union[x] = getParent(union[x]);
		return union[x];
	}

	public static void union(int p1, int p2) {
		if(rank[p1] < rank[p2]) {
			union[p1] = union[p2];
			rank[p2] += rank[p1];
		} else {
			union[p2] = union[p1];
			rank[p1] += rank[p2];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		for(int i = 0; i < input[0]+1; i++) {
			union[i] = i;
			rank[i] = 1;
		}

		for(int i = 0; i < input[1]; i++) {
			int[] edge = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt)
							.toArray();
			int p1 = getParent(edge[0]);
			int p2 = getParent(edge[1]);
			union(p1, p2);
			if(p1 == p2) {
				System.out.println(i+1);
				return;
			}
		}
		System.out.println(0);

	}
}

/*
*
* solution1) union find
*
* time : O(N^2)  >  O(N)
* 			optimized
* space : O(N)
*
*
*
* */