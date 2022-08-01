import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] stairs = new int[300];
		int[] maxHeight = new int[300];
		
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());
			stairs[i] = height;
		}
		
		maxHeight[0] = stairs[0];
		maxHeight[1] = stairs[0] + stairs[1];
		maxHeight[2] = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);
		for (int i = 3; i < N; i++) {
			maxHeight[i] = Math.max(maxHeight[i-2],
					maxHeight[i-3] + stairs[i-1]) + stairs[i];
		}
		
		System.out.println(maxHeight[N-1]);
	}
}