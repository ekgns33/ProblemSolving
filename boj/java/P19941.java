import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P19941 {
	final static char HAM = 'H';
	final static char PER = 'P';

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		char[] seq = br.readLine().toCharArray();
		int res = 0;
		for(int i = 0; i < seq.length; i++) {
			if(seq[i] != PER) continue;
			int left = Math.max(i - inputs[1], 0);
			int right = Math.min(i + inputs[1], seq.length-1);
			for(int k = left; k <= right; k++) {
				if(seq[k] == HAM) {
					res++;
					seq[k] = 'A';
					break;
				}
			}
		}

		System.out.println(res);
	}
}
/*
* solution 1) brute force
*
* K ^ N
*
* solution 2)
*
* NK > map person to hamburgers
*
*
*
*
* */