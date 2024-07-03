import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class P6097 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BigInteger ret = new BigInteger(st.nextToken()).pow(Integer.parseInt(st.nextToken()));
		StringBuilder sb = new StringBuilder();
		String s = ret.toString();
		for(int i = 0; i < s.length(); i+= 70) {
			if(i + 70 > s.length()) {
				sb.append(s.substring(i));
				sb.append('\n');
				continue;
			}
			sb.append(s, i, i + 70);
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}
}
