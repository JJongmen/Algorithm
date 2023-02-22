import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());


        int[] primes = new int[K];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            primes[i] = Integer.parseInt(st.nextToken());
            pq.offer(primes[i]);
        }

        long prime = 0;
        while (N-- > 0) {
            prime = pq.poll();
            for (int i = 0; i < K; i++) {
                long nxt = prime * primes[i];
                if (nxt >= 1L << 31) continue;
                if (prime < primes[i]) continue;
                pq.offer((int) nxt);
                if (prime % primes[i] == 0) break;
            }
        }
        System.out.println(prime);
    }
}