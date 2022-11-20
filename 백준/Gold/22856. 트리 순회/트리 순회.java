import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int a, b, c;
    static int[] lc = new int[100001];
    static int[] rc = new int[100001];
    static int[] p = new int[100001];
    static boolean[] visit = new boolean[100001];
    static int end;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            lc[a] = b;
            rc[a] = c;
            if (b != -1) {
                p[b] = a;
            }
            if (c != -1) {
                p[c] = a;
            }
        }

        inorder(1);
        similarInorder(1);
        System.out.println(result - 1);
    }

    private static void similarInorder(int cur) {
        result++;
        visit[cur] = true;
        if (lc[cur] != -1 && !visit[lc[cur]]) {
            similarInorder(lc[cur]);
        } else if (rc[cur] != -1 && !visit[rc[cur]]) {
            similarInorder(rc[cur]);
        } else if (cur == end) {
            return;
        } else if (p[cur] != 0) {
            similarInorder(p[cur]);
        }
    }

    private static void inorder(int cur) {
        if (lc[cur] != -1) {
            inorder(lc[cur]);
        }
        end = cur;
        if (rc[cur] != -1) {
            inorder(rc[cur]);
        }
    }
}