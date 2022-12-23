import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int Q;
    static long l = -1000000000000000000L;
    static long r = 1000000000000000000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Q = Integer.parseInt(br.readLine());

        int order = 200000;
        for (int i = 1; i <= Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            String token = st.nextToken();
            if (token.equals("^")) {
                l = Math.max(l, x + 1);
            } else {
                r = Math.min(r, x - 1);
            }
            if (l > r) {
                System.out.println("Paradox!");
                System.out.println(i);
                return;
            }
            if (l == r) {
                order = Math.min(order, i);
            }
        }
        if (l == r) {
            System.out.println("I got it!");
            System.out.println(order);
            return;
        }
        System.out.println("Hmm...");
    }
}