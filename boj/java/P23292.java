import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class P23292 {
	static final int YEAR = 4;
	static final int MONTH = 2;
	static final int DAY = 2;
	static class BioRythm implements Comparable<BioRythm>{
		String date;
		int point;

		public BioRythm (String date, int point) {
			this.date = date;
			this.point = point;
		}

		@Override
		public int compareTo(BioRythm o) {
			if(this.point != o.point) {
				return o.point - this.point;
			}
			return -1 * o.date.compareTo(this.date);
		}
	}
	public static int getCodingBioRythm(String x, String operand) {

		int ret = 1;
		double tmp = 0;
		for(int i = 0; i < YEAR; i++) {
			tmp += Math.pow((x.charAt(i) - '0' - (operand.charAt(i) - '0')), 2);
		}
		ret *= tmp;
		tmp = 0;
		for(int i = 0; i < MONTH; i++) {
			tmp += Math.pow(((x.charAt(4 + i) - '0') - (operand.charAt(4 + i) - '0')), 2);
		}
		ret *= tmp;
		tmp = 0;
		for(int i = 0; i < DAY; i++) {
			tmp += Math.pow((x.charAt(6 + i) - '0' - (operand.charAt(6 + i) - '0')), 2);
		}
		ret*=tmp;
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String startDate = br.readLine();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<BioRythm> pq = new PriorityQueue<>();

		for(int i = 0; i < N; i++) {
			String date = br.readLine();
			pq.add(new BioRythm(date, getCodingBioRythm(startDate, date)));
		}
		System.out.println(pq.poll().date);
	}
}
