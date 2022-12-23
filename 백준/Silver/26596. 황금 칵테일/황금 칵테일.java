import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX = 5000 * 100;
    static int M;
    static Map<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int x = Integer.parseInt(st.nextToken());
            map.put(s, map.getOrDefault(s, 0) + x);
        }

        int[] chk = new int[MAX + 1];
        for (Integer value : map.values()) {
            chk[value]++;
        }

        for (Integer value : map.values()) {
            if (chk[(int) Math.floor(value * 1.618)] > 0) {
                if (value == 1 && chk[value] == 1) continue;
                System.out.println("Delicious!");
                return;
            }
        }
        System.out.println("Not Delicious...");
    }
}