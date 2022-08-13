import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];
		Stack<Integer> stack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = N - 1; i >= 0; i--) {
			int top = -1;
			while (!stack.isEmpty()) {
				if (nums[i] < stack.peek()) {
					top = stack.peek();
					break;
				} else {
					stack.pop();
				}
			}
			stack.push(nums[i]);
			nums[i] = top;
		}
		
		for (int num : nums) {
			bw.write(num + " ");
		}
		
		bw.flush();
		br.close();
		bw.close();

	}
}