import java.util.Scanner;

public class Main {
    static final int ROOT = 1;

    static int unused;
    static int[][] nxt;
    static boolean[] chk;
    static boolean result;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            initTrie(n);
            while (n-- > 0) {
                insert(scanner.next());
            }
            System.out.println(result ? "YES" : "NO");
        }
    }

    private static void insert(String s) {
        int cur = ROOT;
        for (char c : s.toCharArray()) {
            if (nxt[cur][c2i(c)] == 0) {
                nxt[cur][c2i(c)] = unused++;
            }
            cur = nxt[cur][c2i(c)];
            if (chk[cur]) result = false;
        }
        chk[cur] = true;
        for (int idx : nxt[cur]) {
            if (idx > 0) {
                result = false;
                return;
            }
        }
    }

    private static int c2i(char c) {
        return c - '0';
    }

    private static void initTrie(int n) {
        unused = 2;
        int max = n * 10 + 1;
        nxt = new int[max][10];
        chk = new boolean[max];
        result = true;
    }
}