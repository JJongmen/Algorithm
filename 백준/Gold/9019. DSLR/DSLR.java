import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Command {
        String cmd;
        int num;

        public Command(String cmd, int num) {
            this.cmd = cmd;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            boolean[] visit = new boolean[10000];
            Queue<Command> que = new LinkedList<>();
            que.offer(new Command("", A));
            while (!que.isEmpty()) {
                Command cur = que.poll();
                if (cur.num == B) {
                    bw.write(cur.cmd + "\n");
                    break;
                }
                if (visit[cur.num]) continue;
                visit[cur.num] = true;
                que.offer(new Command(cur.cmd + "D", D(cur.num)));
                que.offer(new Command(cur.cmd + "S", S(cur.num)));
                que.offer(new Command(cur.cmd + "L", L(cur.num)));
                que.offer(new Command(cur.cmd + "R", R(cur.num)));
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int D(int n) {
        return (n * 2) % 10000;
    }

    private static int S(int n) {
        if (n == 0) {
            return 9999;
        }
        return n - 1;
    }

    private static int L(int n) {
        int d1 = n / 1000;
        return (n % 1000) * 10 + d1;
    }

    private static int R(int n) {
        int d4 = n % 10;
        return 1000 * d4 + n / 10;
    }
}