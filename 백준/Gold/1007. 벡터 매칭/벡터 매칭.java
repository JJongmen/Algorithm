import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 3000000;
    static int N;
    static int[][] point = new int[20][2];
    static double result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                point[i][0] = Integer.parseInt(st.nextToken());
                point[i][1] = Integer.parseInt(st.nextToken());
            }
            result = INF;
            func(0, 0, 0, 0);
            bw.write(result + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void func(int idx, int plusCnt, double xSum, double ySum) {
        if (plusCnt == N / 2) {
            for (int i = idx; i < N; i++) {
                xSum -= point[i][0];
                ySum -= point[i][1];
            }
            result = Math.min(result, Math.sqrt(xSum * xSum + ySum * ySum));
            return;
        }
        if (idx - plusCnt == N / 2) {
            for (int i = idx; i < N; i++) {
                xSum += point[i][0];
                ySum += point[i][1];
            }
            result = Math.min(result, Math.sqrt(xSum * xSum + ySum * ySum));
            return;
        }
        func(idx + 1, plusCnt + 1, xSum + point[idx][0], ySum + point[idx][1]);
        func(idx + 1, plusCnt, xSum - point[idx][0], ySum - point[idx][1]);
    }
}