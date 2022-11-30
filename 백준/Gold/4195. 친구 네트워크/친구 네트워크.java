import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int F;
    static int[] parent;
    static int[] level;
    static Map<String, Integer> indexes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            F = Integer.parseInt(br.readLine());
            init();
            int index = 0;
            while (F-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String A = st.nextToken();
                String B = st.nextToken();
                if (!indexes.containsKey(A)) {
                    indexes.put(A, index++);
                }
                if (!indexes.containsKey(B)) {
                    indexes.put(B, index++);
                }
                bw.write(union(indexes.get(A), indexes.get(B)) + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void init() {
        parent = new int[F * 2];
        level = new int[F * 2];
        indexes = new HashMap<>();
        for (int i = 0; i < F * 2; i++) {
            parent[i] = i;
            level[i] = 1;
        }
    }

    private static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static int union(int x, int y) {
        x = find(x);
        y = find(y);
        // x < y
        if (x != y) {
            parent[y] = x;
            level[x] += level[y];
        }
        return level[x];
    }
}