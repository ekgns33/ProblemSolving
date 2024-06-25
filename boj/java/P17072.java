import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P17072 {
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	public static int calcIntensity(int r, int g, int b) {
		return 2126 * r + 7152 * g + 722 * b;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = input[0];
		M = input[1];

		for(int i = 0; i < N; i++) {
			int[] pixels = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int p = 0;
			for(int j = 0; j < M; j++) {
				int intensity = calcIntensity(pixels[p], pixels[p+1], pixels[p+2]);
				printASCII(intensity);
				p+=3;
			}
			sb.append('\n');

		}
		System.out.println(sb.toString());
	}

	private static void printASCII(int intensity) {
		if(intensity >= 0 && 510000 > intensity) {
			sb.append("#");
		} else if (intensity >= 510000 && intensity < 1020000) {
			sb.append("o");
		} else if (intensity >= 1020000 && intensity < 1530000) {
			sb.append("+");
		} else if (intensity >= 1530000 && intensity < 2040000) {
			sb.append("-");
		} else {
			sb.append(".");
		}
 	}
}
