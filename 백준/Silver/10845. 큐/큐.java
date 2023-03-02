import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int N = Integer.parseInt(br.readLine());
        Deque<Integer> que = new LinkedList<>();
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if ("push".equals(cmd)) {
                int num = Integer.parseInt(st.nextToken());
                que.offer(num);
            } else if ("pop".equals(cmd)) {
                if (que.isEmpty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(que.poll() + "\n");
                }
            } else if ("size".equals(cmd)) {
                bw.write(que.size() + "\n");
            } else if ("empty".equals(cmd)) {
                if (que.isEmpty()) {
                    bw.write("1\n");
                } else {
                    bw.write("0\n");
                }
            } else if ("front".equals(cmd)) {
                if (que.isEmpty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(que.peek() + "\n");
                }
            } else if ("back".equals(cmd)) {
                if (que.isEmpty()) {
                    bw.write("-1\n");
                } else {
                    bw.write(que.peekLast() + "\n");
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}