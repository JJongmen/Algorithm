import java.util.Scanner;

public class Main {
    static int N;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();

        dfs(new StringBuilder());
    }
    
    private static void dfs(StringBuilder cur) {
        if (cur.length() == N) {
            System.out.println(cur);
            System.exit(0);
        }
        for (int i = 1; i <= 3; i++) {
            StringBuilder nxt = new StringBuilder(cur).append(i);
            if (!isGoodSeq(nxt)) continue;
            dfs(nxt);
        }
    }

    private static boolean isGoodSeq(StringBuilder seq) {
        int size = seq.length();
        for (int len = 1; size - 2 * len >= 0; len++) {
            if (seq.substring(size - 2 * len, size - len)
                    .equals(seq.substring(size - len))) {
                return false;
            }
        }
        return true;
    }
}