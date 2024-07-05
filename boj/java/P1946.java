import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P1946 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			int resumeLast = N;
			int interviewLast = N;
			int cnt = 1;
			List<int[]> applicants = new ArrayList<>();
			for(int j = 0; j < N; j++) {
				applicants.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
			}

			applicants.sort((a,b) -> a[0] - b[0]);
			int min = applicants.get(0)[1];
			for(int j = 1; j< N; j++) {
				int score = applicants.get(j)[1];
				if(score < min) {
					min = score;
					cnt++;
				}
			}

			System.out.println(cnt);
		}


	}
}
/*
*
* resume score / interview score
*
* 5 5
*
* 3 2 ok
* 1 4 ok
* 4 1 ok
* 2 3 ok
* 5 5 no
*
* 7 7
* 3 6 ok
* 7 3 ok
* 4 2 ok
* 1 4
* 5 7
* 2 5
* 6 1
*
*
*
* */

