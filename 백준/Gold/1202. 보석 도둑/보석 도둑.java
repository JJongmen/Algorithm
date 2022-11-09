import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static int N, K;
    static int[][] info = new int[300003][2];
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(info, 0, N, (o1, o2) -> o2[1] - o1[1]);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < K; i++) {
            int weight = Integer.parseInt(br.readLine());
            map.put(weight, map.getOrDefault(weight, 0) + 1);
        }
        for (int i = 0; i < N; i++) {
            int weight = info[i][0];
            Integer key = map.ceilingKey(weight);
            if (key == null) continue;
            result += info[i][1];
            if (map.put(key, map.get(key) - 1) == 1) {
                map.remove(key);
            }
            if (map.isEmpty()) break;
        }
        System.out.println(result);
    }
}