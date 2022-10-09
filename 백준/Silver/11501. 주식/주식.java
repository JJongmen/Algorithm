import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        int[] stocks = new int[1000005];
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                stocks[i] = Integer.parseInt(st.nextToken());
            }
            long result = 0;
            int tmpMax = 0;
            for (int i = N - 1; i >= 0; i--) {
                tmpMax = Math.max(tmpMax, stocks[i]);
                result += tmpMax - stocks[i];
            }
           bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}