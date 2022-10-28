import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] table = new int[10][10];
    static int[][] pattern = new int[3][21];    // A, B ,C 의 손동작
    static boolean[] isUsed = new boolean[10];
    static int[] winCount = new int[3];
    static int[] order = new int[3];    // 한 사람당 몇 번째 판인지 저장한다.
    static int result = 0;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 20; j++) {
                pattern[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        permutation(1);
        simulaton();
        System.out.println(result);
    }

    private static void simulaton() {
        Arrays.fill(order, 1);
        Arrays.fill(winCount, 0);
        int a = 0;  // a참가자 (a < b)를 만족
        int b = 1;  // b참가자 (a < b)를 만족
        while (order[0] <= N && order[1] <= 20 && order[2] <= 20) {
            if (canAWin(a, b)) {
                winCount[a]++;
                b = 3 - (a + b);
            } else {
                winCount[b]++;
                a = 3 - (a + b);
            }
            if (winCount[0] == K || winCount[1] == K || winCount[2] == K) {
                if (winCount[0] == K) {
                    result = 1;
                }
                break;
            }
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }
        }
    }

    /**
     * 가위바위보의 경기 결과를 판단하는 메서드
     * @param a : 순서상 앞인 사람
     * @param b : 순서상 뒤인 사람
     * @return a참가자가 이기면 true, b참가자가 이기면 false을 반환한다.
     */
    private static boolean canAWin(int a, int b) {
        int aCase = pattern[a][order[a]++];
        int bCase = pattern[b][order[b]++];
        if (table[aCase][bCase] == 2) {
            return true;
        }
        return false;
    }

    private static void permutation(int cur) {
        if (cur == N + 1) {
            simulaton();
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (isUsed[i]) continue;
            isUsed[i] = true;
            pattern[0][cur] = i;
            permutation(cur + 1);
            isUsed[i] = false;
        }
    }
}