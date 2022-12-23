import java.util.Scanner;

public class Main {
    static final int MAX = 500 * 10000 + 5;
    public static final int ROOT = 1;

    static int N, M;
    static int unused = 2;
    static int[][] nxt = new int[MAX][26];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();

        for (int i = 0; i < N; i++) {
            String str = scanner.next();
            insert(str);
        }

        int result = 0;
        for (int i = 0; i < M; i++) {
            String str = scanner.next();
            result += find(str);
        }
        System.out.println(result);
    }

    private static int find(String s) {
        int cur = ROOT;
        for (char c : s.toCharArray()) {
            if (nxt[cur][c2i(c)] == 0) {
                return 0;
            }
            cur = nxt[cur][c2i(c)];
        }
        return 1;
    }

    private static void insert(String s) {
        int cur = ROOT;
        for (char c : s.toCharArray()) {
            if (nxt[cur][c2i(c)] == 0) {
                nxt[cur][c2i(c)] = unused++;
            }
            cur = nxt[cur][c2i(c)];
        }
    }

    private static int c2i(char c) {
        return c - 'a';
    }
}