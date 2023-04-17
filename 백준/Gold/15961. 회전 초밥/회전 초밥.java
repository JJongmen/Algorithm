import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, d, k, c;
    static int[] plates = new int[3003001];
    static int[] kinds = new int[3001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            plates[i] = Integer.parseInt(br.readLine());
            if (i <= k) {
                plates[N + i] = plates[i];
            }
        }

        int result = 0;
        int cnt = 0;
        for (int i = 1; i <= N + k; i++) {
            if (kinds[plates[i]]++ == 0) cnt++;
            if (i < k) continue;
            if (kinds[c] > 0) result = Math.max(result, cnt);
            else result = Math.max(result, cnt + 1);
            if (--kinds[plates[i - k + 1]] == 0) cnt--;
        }
        System.out.println(result);
    }
}