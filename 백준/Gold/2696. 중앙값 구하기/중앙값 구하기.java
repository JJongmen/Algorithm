import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int M = Integer.parseInt(br.readLine());
            StringBuilder result = new StringBuilder();
            result.append(M / 2 + 1).append("\n");
            PriorityQueue<Integer> maxPq = new PriorityQueue<>((o1, o2) -> o2 - o1);
            PriorityQueue<Integer> minPq = new PriorityQueue<>();

            for (int i = 0; i < M / 10; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 10; j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (j % 2 == 0) {
                        maxPq.offer(num);
                    } else {
                        minPq.offer(num);
                    }

                    if (!minPq.isEmpty() && maxPq.peek() > minPq.peek()) {
                        minPq.offer(maxPq.poll());
                        maxPq.offer(minPq.poll());
                    }

                    if (j % 2 == 0) {
                        result.append(maxPq.peek()).append(" ");
                    }
                }
                if (i % 2 == 1) {
                    result.append("\n");
                }
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M % 10; i++) {
                int num = Integer.parseInt(st.nextToken());
                if (i % 2 == 0) {
                    maxPq.offer(num);
                } else {
                    minPq.offer(num);
                }

                if (!minPq.isEmpty() && maxPq.peek() > minPq.peek()) {
                    minPq.offer(maxPq.poll());
                    maxPq.offer(minPq.poll());
                }

                if (i % 2 == 0) {
                    result.append(maxPq.peek()).append(" ");
                }
            }

            bw.write(result.toString() + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}