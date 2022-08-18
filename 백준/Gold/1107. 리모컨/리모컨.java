import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int min;
	static String N;
	static List<Integer> buttons = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8,9));
	
	static void recur(String comp) {
		if (comp.length() == N.length()) {
			min = Math.min(min, N.length() +
					Math.abs(Integer.parseInt(comp) - Integer.parseInt(N)));
			return;
		}
		
		for (int i = 0; i < buttons.size(); i++) {
			recur(comp + buttons.get(i));
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = br.readLine();
		
		int M = Integer.parseInt(br.readLine());
		min = Math.abs(Integer.parseInt(N) - 100);
		if (M == 0) {
			System.out.println(Math.min(min, N.length()));
			return;
		} else {
			List<Integer> brokenButtons = new ArrayList<>(M);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				brokenButtons.add(Integer.parseInt(st.nextToken()));
			}
			buttons.removeAll(brokenButtons);
		}
		
		
		if (M != 10) {
			int minBtn = buttons.get(0);
			int maxBtn = buttons.get(buttons.size() - 1);
			StringBuilder sb = new StringBuilder();
			if (minBtn == 0 && buttons.size() > 1) {
				sb.append(buttons.get(1));
			} else if (minBtn != 0){
				sb.append(minBtn);
			}
			for (int i = 0; i < N.length(); i++) {
				sb.append(minBtn);
			}
			if (minBtn != 0 || buttons.size() > 1) {
				min = Math.min(min,
						N.length() + 1 + Integer.parseInt(sb.toString()) -
						Integer.parseInt(N));
			}
			

			if (N.length() >= 2) {
				sb = new StringBuilder();
				for (int i = 0; i < N.length() - 1; i++) {
					sb.append(maxBtn);
				}
				min = Math.min(min,
						N.length() - 1 + Integer.parseInt(N) -
						Integer.parseInt(sb.toString()));
			}
			
			recur("");
		}
		
		System.out.println(min);
	}
}