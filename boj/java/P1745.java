import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P1745 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		 int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		 for(int i = 0; i < N; i++) {
			 int next = Integer.parseInt(br.readLine());
			 pq.add(next);
		 }
		 int cnt = 0;
		 while(pq.size() > 1) {
			 int op1 = pq.poll();
			 cnt += op1;
			 int op2 = pq.poll();
			 cnt += op2;
			 pq.add(op1 + op2);

		 }
		System.out.println(cnt);
	}
}
