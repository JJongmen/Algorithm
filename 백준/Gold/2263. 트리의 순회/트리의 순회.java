import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] in = new int[100000];
    static int[] post = new int[100000];
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            in[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }

        getPreOrder(0, N - 1, 0, N - 1);

        bw.flush();
        bw.close();
    }

    private static void getPreOrder(int is, int ie, int ps, int pe) throws IOException {
        if (is > ie || ps > pe) return;
        if (is == ie || ps == pe) {
            bw.write(post[pe] + " ");
            return;
        }
        int num = post[pe];
        int idx = is;
        for (; idx <= ie; idx++) {
            if (in[idx] == num) break;
        }
        bw.write(post[pe] + " ");
        getPreOrder(is, idx - 1, ps, ps + idx - is - 1);
        getPreOrder(idx + 1, ie, ps + idx - is, pe - 1);
    }
}