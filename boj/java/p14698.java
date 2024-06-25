import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class p14698 {

	public static void Solution(BufferedReader br) throws IOException {
		int M = Integer.parseInt(br.readLine());

		long[] input = Arrays.stream(br.readLine().split(" "))
						.mapToLong(Long::parseLong)
						.toArray();
		PriorityQueue<Long> pq = new PriorityQueue<>();

		for(Long num : input) {
			pq.add(num);
		}

		long result = 1;
		while(!pq.isEmpty()) {

			long operand1 = pq.peek();
			pq.poll();
			if(pq.isEmpty()) break;
			long operand2 = pq.peek();
			pq.poll();

			long mul  = operand1 * operand2;
			result *= mul % 1000000007L;
			result %= 1000000007L;
			pq.add(mul);
		}
		System.out.println(result);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		for(int i = 0; i < N; i++) {
			Solution(br);
		}
	}
}
/*
*
* 전기 에너지를 곱한 값.
* a - z
* 중 제일 작은 애들끼리 곱해
*
* */