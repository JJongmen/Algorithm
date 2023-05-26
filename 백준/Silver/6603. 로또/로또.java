import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] arr = new int[20];
    static int[] lotto = new int[6];
    static int k;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) break;
            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            bt(-1, 0);
            bw.write('\n');
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bt(int lastIdx, int idx) throws IOException {
        if (idx == 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                sb.append(lotto[i]).append(' ');
            }
            sb.append('\n');
            bw.write(sb.toString());
            return;
        }
        for (int i = lastIdx + 1; i < k; i++) {
            lotto[idx] = arr[i];
            bt(i, idx + 1);
        }
    }
}