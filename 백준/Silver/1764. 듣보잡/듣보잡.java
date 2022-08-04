import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Set<String> set = new HashSet<>();
		List<String> unknowns = new LinkedList<>();
		while (N-- > 0) {
			set.add(br.readLine());
		}
		
		while (M-- > 0) {
			String name = br.readLine();
			if (set.contains(name)) {
				unknowns.add(name);
			}
		}
		
		Collections.sort(unknowns);
		int size = unknowns.size();
		bw.write(size + "\n");
		for (int i = 0; i < size; i++) {
			bw.write(unknowns.get(i) + "\n");
		}
		bw.flush();
		br.close();
		bw.close();

	}
}