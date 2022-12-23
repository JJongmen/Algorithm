import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final int MAX = 500 * 80 + 5;
    static final int ROOT = 1;

    static int N;
    static int unused = 2;
    static int[][] nxt = new int[MAX][128];
    static boolean[] chk = new boolean[MAX];
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            insert(br.readLine());
        }

        print(ROOT, '\0', 0, new StringBuilder());

        System.out.println(result);
    }

    private static void print(int cur, char ch, int depth, StringBuilder comb) {
        StringBuilder sb = new StringBuilder(comb);
        if (cur != ROOT) {
            if (ch == '\0') {
                sb = new StringBuilder(" ".repeat(depth));
            } else {
                sb.append(ch);
            }
        }
        if (chk[cur]) {
            System.out.println(sb);
        }
        for (char c = 0; c < 128; c++) {
            if (nxt[cur][c] == 0) continue;
            if (c == '\0') {
                print(nxt[cur][c], c, depth + 1, sb);
                continue;
            }
            print(nxt[cur][c], c, depth, sb);
        }
    }

    private static void insert(String s) {
        int cur = ROOT;
        for (char c : s.toCharArray()) {
            if (c == '\\') {
                chk[cur] = true;
                c = 0;
            }
            if (nxt[cur][c] == 0) {
                nxt[cur][c] = unused++;
            }
            cur = nxt[cur][c];
        }
        chk[cur] = true;
    }
}