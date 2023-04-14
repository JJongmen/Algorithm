import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static class LikeNumber {
        int num;
        StringBuilder str;

        public LikeNumber(int num, StringBuilder str) {
            this.num = num;
            this.str = str;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            boolean[] visit = new boolean[20001];
            Queue<LikeNumber> que = new LinkedList<>();
            que.offer(new LikeNumber(1, new StringBuilder("1")));
            visit[1] = true;
            while (!que.isEmpty()) {
                LikeNumber cur = que.poll();
                int remain = cur.num % N;
                if (cur.str.length() > 100) {
                    bw.write("BRAK\n");
                    break;
                }
                if (remain == 0) {
                    bw.write(cur.str + "\n");
                    break;
                }
                int nxt = (remain * 10) % N;
                if (!visit[nxt]) {
                    que.offer(new LikeNumber(nxt, new StringBuilder(cur.str).append(0)));
                    visit[nxt] = true;
                }
                nxt = (remain * 10 + 1) % N;
                if (!visit[nxt]) {
                    que.offer(new LikeNumber((remain * 10 + 1) % N, cur.str.append(1)));
                    visit[nxt] = true;
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}