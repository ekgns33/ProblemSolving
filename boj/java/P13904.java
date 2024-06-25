import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class P13904 {
	static class Task implements Comparable<Task>{
		int dueDate;
		int point;

		public Task(int date, int point) {
			this.dueDate = date;
			this.point = point;
		}
		@Override
		public int compareTo(Task o) {
			if(this.dueDate == o.dueDate) {
				return o.point - this.point;
			}
			return this.dueDate - o.dueDate;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Task> pq = new PriorityQueue<>();

		for(int i = 0; i < N; i++){
			int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			pq.add(new Task(input[0], input[1]));
		}

		int curDate = 1;
		int totalPoint = 0;
		while(!pq.isEmpty()) {
			Task curTask = pq.poll();
			System.out.println(curTask.dueDate + " : " + curTask.point);
			if(curTask.dueDate - curDate < 0) continue;
			totalPoint += curTask.point;
			curDate++;
		}
		System.out.println(totalPoint);
	}
}
