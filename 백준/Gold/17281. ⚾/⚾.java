import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] hits = new int[51][10];    // i번째 이닝에 j번째 선수가 몇 타점을 치는지 저장한다.
    static int[] order = new int[10];   // i번째 타순에 몇 번 선수가 치는지 저장한다.
    static boolean[] check = new boolean[10];   // 순서 정할 때 이미 쓴 숫자인지 확인
    static int N;
    static int result;

    static {
        order[4] = 1;
        check[1] = true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                hits[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        setOrder(1);

        System.out.println(result);
    }

    // 타순을 정하고 득점을 구한다.
    static void setOrder(int cur) {
        if (cur == 10) {
            result = Math.max(result, getScore());
            return;
        }
        if (cur == 4) {
            setOrder(5);
            return;
        }
        for (int i = 1; i <= 9; i++) {
            if (check[i]) continue;
            check[i] = true;
            order[cur] = i;
            setOrder(cur + 1);
            check[i] = false;
        }
    }

    static int getScore() {
        int score = 0;
        int j = 1;
        for (int i = 1; i <= N; i++) {
            int out = 0;
            boolean[] status = new boolean[4];  // status[i] = i루에 선수가 있다면 true
            while (out < 3) {
                int num = order[j]; // j 번째 타자는 num 번 선수
                int hit = hits[i][num]; // 타점
                if (hit == 0) out++;
                else if (hit == 4) {
                    score++;
                    for (int k = 1; k <= 3; k++) {
                        if (!status[k]) continue;
                        score++;
                        status[k] = false;
                    }
                }
                else {
                    // 진루했던 주자가 홈으로 돌아오는 경우
                    for (int k = 3; k >= 4 - hit; k--) {
                        if (!status[k]) continue;
                        score++;
                        status[k] = false;
                    }
                    // 진루했던 주자가 홈이 아닌 베이스로 가는 경우
                    for (int k = 3; k >= hit + 1; k--) status[k] = status[k - hit];
                    // 타자가 출루함
                    status[hit] = true;
                    for (int k = 1; k < hit; k++) status[k] = false;
                }
                j++;
                if (j == 10) j = 1;
            }
        }
        return score;
    }
}