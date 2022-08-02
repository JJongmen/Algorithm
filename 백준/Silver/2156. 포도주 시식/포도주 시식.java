import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] amounts = new int[10000];
		int[] maxAmounts = new int[10000];
		for (int i = 0; i < n; i++) {
			amounts[i] = Integer.parseInt(br.readLine());
		}
		
		maxAmounts[0] = amounts[0];
		maxAmounts[1] = amounts[0] + amounts[1];
		maxAmounts[2] = Math.max(Math.max(amounts[0], amounts[1]) + amounts[2],
				maxAmounts[1]);
		for (int i = 3; i < n; i++) {
			maxAmounts[i] = Math.max(Math.max(maxAmounts[i - 2],
					maxAmounts[i - 3] + amounts[i - 1]) + amounts[i],
					maxAmounts[i - 1]);
		}
		
		System.out.println(Math.max(maxAmounts[n == 1 ? 0 : n - 2], maxAmounts[n - 1]));
	}
}