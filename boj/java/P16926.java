import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P16926 {

	static int tmp = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] board = new int[N][];
		for(int i = 0; i < N; i++) {
			board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		for(int i = 0; i < N; i++) {
		for(int j = 0; j < M; j++) {
			System.out.print(board[i][j] + " ");
		}
		System.out.println();
	}
}

	private static void rotateBoard(int[][] board, int lr, int ud, int rl, int du) {

		for(int p = ud; p <= du; p++) {
			int t = board[p][lr];
			board[p][lr] = tmp;
			tmp = t;
		}
		for(int p = lr + 1; p <= rl; p++) {
			int t =board[du][p];
			board[du][p] = tmp;
			tmp = t;
		}

	}
}
/*
*
* solution 1) rotate K times
*
* O(NM * K) ~= 90000 * 1000 > 900000000 OK
*
* N * M -1 rotate > comback

*
*      i
*    1 2 3 4
* j  5 6 7 8
* j  9 8 7 6 s
*    5 4 3 2
*        r
* */