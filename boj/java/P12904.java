import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P12904 {
	public static void reverse(char[] arr, int l, int r) {
		while(l < r) {
			char tmp = arr[l];
			arr[l] = arr[r];
			arr[r] = tmp;
			l++;
			r--;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String target = br.readLine();
		char[] curr = br.readLine().toCharArray();

		int cnt = 0;
		int N = curr.length - target.length();
		int r = curr.length - 1;
		while(cnt < N) {
			if(curr[r] == 'B') {
				reverse(curr, 0, r-1);
			}
			r--;
			cnt++;
		}

		String res = String.valueOf(curr, 0, target.length());
		System.out.println(res.equals(target) ? 1 : 0);
	}
}

