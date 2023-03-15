import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static final int TEAM_CNT = 6;
    static int[][] schedule = new int[20][2];
    static int[] win = new int[TEAM_CNT + 1];
    static int[] tie = new int[TEAM_CNT + 1];
    static int[] lose = new int[TEAM_CNT + 1];
    static int matchCnt = 0;
    static boolean isFinished;

    static {
        for (int i = 1; i <= TEAM_CNT; i++) {
            for (int j = i + 1; j <= TEAM_CNT; j++) {
                schedule[++matchCnt][0] = i;
                schedule[matchCnt][1] = j;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = 4;
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean isPossible = true;
            for (int i = 1; i <= TEAM_CNT; i++) {
                win[i] = Integer.parseInt(st.nextToken());
                tie[i] = Integer.parseInt(st.nextToken());
                lose[i] = Integer.parseInt(st.nextToken());
                if (win[i] + tie[i] + lose[i] == 5) continue;
                isPossible = false;
                break;
            }
            if (!isPossible) {
                sb.append("0 ");
                continue;
            }
            isFinished = false;
            matching(1);
            if (isFinished) {
                sb.append("1 ");
            } else {
                sb.append("0 ");
            }
        }
        System.out.println(sb);
    }

    private static void matching(int cur) {
        if (isFinished) {
            return;
        }
        if (cur == matchCnt + 1) {
            isFinished = true;
            return;
        }
        int home = schedule[cur][0];
        int away = schedule[cur][1];
        // home 승 / away 패
        if (win[home] > 0 && lose[away] > 0) {
            win[home]--;
            lose[away]--;
            matching(cur + 1);
            win[home]++;
            lose[away]++;
        }
        // home 무 / away 무
        if (tie[home] > 0 && tie[away] > 0) {
            tie[home]--;
            tie[away]--;
            matching(cur + 1);
            tie[home]++;
            tie[away]++;
        }
        // home 패 / away 승
        if (lose[home] > 0 && win[away] > 0) {
            lose[home]--;
            win[away]--;
            matching(cur + 1);
            lose[home]++;
            win[away]++;
        }
    }
}