import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static PriorityQueue<Integer> ascending = new PriorityQueue<>();
    static PriorityQueue<Integer> descending = new PriorityQueue<>(Collections.reverseOrder());
    static {
        ascending.offer(10005);
        descending.offer(-10005);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (descending.peek() < num) {
                ascending.offer(num);
            } else {
                descending.offer(num);
            }
            if (descending.size() == ascending.size() + 2) {
                ascending.offer(descending.poll());
            }
            if (descending.size() + 1 == ascending.size()) {
                descending.offer(ascending.poll());
            }
            bw.write(descending.peek() + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}