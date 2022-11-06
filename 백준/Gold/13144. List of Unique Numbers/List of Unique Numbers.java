import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] seq = new int[100005];
    static boolean[] check = new boolean[100005];
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int r = 0;
        for (int l = 0; l < N; l++) {
            while (r < N && !check[seq[r]]) {
                check[seq[r++]] = true;
            }
            result += r - l;
            check[seq[l]] = false;
        }
        System.out.println(result);
    }
}