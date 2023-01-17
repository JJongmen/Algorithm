import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static class Info {
        int time;
        int cnt;
        int cbCnt;

        public Info(int time, int cnt, int cbCnt) {
            this.time = time;
            this.cnt = cnt;
            this.cbCnt = cbCnt;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int S = scan.nextInt();

        Queue<Info> que = new LinkedList<>();
        que.offer(new Info(0, 1, 0));
        boolean[][] visit = new boolean[2 * S + 1][2 * S + 1];
        visit[1][0] = true;
        while (!que.isEmpty()) {
            Info cur = que.poll();
            if (cur.cnt == S) {
                System.out.println(cur.time);
                return;
            }
            if (!visit[cur.cnt][cur.cnt]) {
                que.offer(new Info(cur.time + 1, cur.cnt, cur.cnt));
                visit[cur.cnt][cur.cnt] = true;
            }
            if (2 * cur.cnt + cur.cbCnt < 2 * S && !visit[cur.cnt + cur.cbCnt][cur.cbCnt]) {
                que.offer(new Info(cur.time + 1, cur.cnt + cur.cbCnt, cur.cbCnt));
                visit[cur.cnt + cur.cbCnt][cur.cbCnt] = true;
            }
            if (cur.cnt > 0 && !visit[cur.cnt - 1][cur.cbCnt]) {
                que.offer(new Info(cur.time + 1, cur.cnt - 1, cur.cbCnt));
                visit[cur.cnt - 1][cur.cbCnt] = true;
            }
        }
    }
}