import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, d, k, c;
    static int[] plates = new int[30005];
    static int[] eatCnt = new int[3005];
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            plates[i] = Integer.parseInt(br.readLine());
        }

        int cnt = 0;
        for (int i = 0; i < k; i++) {
            int num = plates[i];
            if (eatCnt[num]++ == 0) cnt++;
        }
        for (int l = 1; l < N; l++) {
            int r = (l + k - 1) % N;
            if (--eatCnt[plates[l - 1]] == 0) cnt--;
            if (eatCnt[plates[r]]++ == 0) cnt++;
            result = Math.max(result, eatCnt[c] == 0 ? cnt + 1 : cnt);
        }
        System.out.println(result);
    }
}