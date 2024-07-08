import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class P2812 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] number = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		Deque<Integer> stack = new ArrayDeque<>();
		int cnt = 0;
		for(int i = 0; i < number.length; i++) {
			while(!stack.isEmpty() && cnt < input[1] && stack.peekLast() < number[i]) {
				stack.pollLast();
				cnt++;
			}
			stack.addLast(number[i]);
		}
		StringBuilder sb = new StringBuilder();
		for(int i =0; i < input[0] - input[1]; i++) {
			sb.append(stack.pollFirst());
		}
		System.out.println(sb.toString());
	}
}

/*
*10 5
  4177252841
* 4775841
* 7841
*
* */