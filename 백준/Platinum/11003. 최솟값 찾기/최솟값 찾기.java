import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!deque.isEmpty() && arr[deque.peekLast()] > arr[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (deque.peekFirst() == i - L) {
                deque.pollFirst();
            }
            bw.write(arr[deque.peekFirst()] + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}