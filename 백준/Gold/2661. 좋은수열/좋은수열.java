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
        for (int i = 0; i < seq.length(); i++) {
            for (int len = 1; i + 2 * len <= seq.length(); len++) {
                if (seq.substring(i, i + len).equals(seq.substring(i + len, i + 2 * len))) {
                    return false;
                }
            }
        }
        return true;
    }
}