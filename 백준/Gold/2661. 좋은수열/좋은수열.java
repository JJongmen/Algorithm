import java.util.Scanner;

public class Main {
    static int N;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();

        dfs("", 0);
    }

    private static void dfs(String cur, int last) {
        if (cur.length() == N) {
            System.out.println(cur);
            System.exit(0);
        }
        for (int i = 1; i <= 3; i++) {
            if (i == last) continue;
            String nxt = cur + i;
            if (!isGoodSeq(nxt)) continue;
            dfs(nxt, i);
        }
    }

    private static boolean isGoodSeq(String seq) {
        int size = seq.length();
        for (int len = 2; size - 2 * len >= 0; len++) {
            if (seq.substring(size - 2 * len, size - len)
                    .equals(seq.substring(size - len))) {
                return false;
            }
        }
        return true;
    }
}