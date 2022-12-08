import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] table = new int[20][20];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                boolean needFlag = true;
                for (int k = 0; k < N; k++) {
                    if (i == k || j == k) continue;
                    int newTime = table[i][k] + table[k][j];
                    if (table[i][j] > newTime) {
                        System.out.println(-1);
                        return;
                    }
                    if (table[i][j] == newTime) {
                        needFlag = false;
                        break;
                    }
                }
                if (needFlag) {
                    result += table[i][j];
                }
            }
        }
        System.out.println(result);
    }
}