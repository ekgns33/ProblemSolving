import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P16395 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] pascal = new int[inputs[0]+1];

		pascal[1] = 1;
		if(inputs[0] == 1) {
			System.out.println(1);
		}
		for(int step  = 2; step <= inputs[0]; step++) {
			for(int j = pascal.length-1; j >= 1; j--) {
				pascal[j] = pascal[j] + pascal[j-1];
				if(step == inputs[0] && j == pascal.length - inputs[1]) {
					System.out.println(pascal[j]);
					break;
				}
			}

		}
	}
}
/*
*
*
* 1 0 0 0 0 0 0 0 0
* 1 2 1 0 0 0 0 0 0
* 1 3 3 1 0 0 0 0 0 0
* 1 4 6 4 1 0 0 0 0
* 1 5 10 10 5 1 0 0 0
* 			K
* */
