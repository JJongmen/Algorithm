import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] useOrder = new int[100];
    static int[] multiTap = new int[100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            useOrder[i] = Integer.parseInt(st.nextToken());
        }

        int plugedCnt = 0;
        int result = 0;
        for (int i = 0; i < K; i++) {
            int num = useOrder[i];
            if (isPluged(num)) continue;
            if (plugedCnt < N) {
                multiTap[plugedCnt++] = num;
                continue;
            }
            int unplug = -1;
            int lastIdx = -1;
            for (int j = 0; j < N; j++) {
                int order = -1;
                for (int k = i + 1; k < K; k++) {
                    if (multiTap[j] == useOrder[k]) {
                        order = k;
                        break;
                    }
                }
                if (order == -1) {
                    unplug = j;
                    break;
                }
                if (lastIdx < order) {
                    lastIdx = order;
                    unplug = j;
                }
            }
            result++;
            multiTap[unplug] = num;
        }
        System.out.println(result);
    }

    private static boolean isPluged(int num) {
        for (int i = 0; i < N; i++) {
            if (multiTap[i] == num) return true;
        }
        return false;
    }
}