import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX = 10000 * 500 + 5;
    public static final int ROOT = 1;
    static int N, M;
    static int unused = 2;
    static boolean[] chk = new boolean[MAX];
    static int[][] nxt = new int[MAX][26];

    static {
        for (int i = 0; i < MAX; i++) {
            Arrays.fill(nxt[i], -1);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            insert(str);
        }

        int result = 0;
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            if (find(str)) result++;
        }
        System.out.println(result);
    }

    private static boolean find(String s) {
        int cur = ROOT;
        for (int i = 0; i < s.length(); i++) {
            int idx = c2i(s.charAt(i));
            if (nxt[cur][idx] == -1) {
                return false;
            }
            cur = nxt[cur][idx];
        }
        return chk[cur];
    }

    private static void insert(String s) {
        int cur = ROOT;
        for (int i = 0; i < s.length(); i++) {
            int idx = c2i(s.charAt(i));
            if (nxt[cur][idx] == -1) {
                nxt[cur][idx] = unused++;
            }
            cur = nxt[cur][idx];
        }
        chk[cur] = true;
    }

    private static int c2i(char c) {
        return c - 'a';
    }
}