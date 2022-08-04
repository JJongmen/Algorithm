import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Map<String, Integer> pokeMap = new LinkedHashMap<>();
		for (int i = 1; i <= N; i++) {
			pokeMap.put(br.readLine(), i);
		}
		String[] pokeNames = pokeMap.keySet().toArray(new String[0]);
		
		for (int i = 0; i < M; i++) {
			String pokemon = br.readLine();
			if ('0' <= pokemon.charAt(0) && pokemon.charAt(0) <= '9') {
				int pokNum = Integer.parseInt(pokemon);
				bw.write(pokeNames[pokNum - 1] + "\n");
			} else {
				bw.write(pokeMap.get(pokemon) + "\n");
			}
		}
		
		bw.flush();
		br.close();
		bw.close();
	}
}