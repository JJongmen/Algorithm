import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        Queue<String> que = new LinkedList<>();
        que.offer(T);
        while (!que.isEmpty()) {
            String cur = que.poll();
            if (cur.length() < S.length()) break;
            if (cur.equals(S)) {
                System.out.println(1);
                return;
            }
            if (cur.length() <= 1) continue;
            if (cur.charAt(cur.length() - 1) == 'A') {
                que.offer(cur.substring(0, cur.length() - 1));
            }
            if (cur.charAt(0) == 'B') {
                StringBuilder sb = new StringBuilder(cur);
                que.offer(sb.reverse().substring(0, sb.length() - 1));
            }
        }
        System.out.println(0);
    }
}