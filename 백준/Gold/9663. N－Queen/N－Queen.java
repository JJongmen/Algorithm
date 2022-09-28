import java.util.Scanner;

public class Main {
    private static int cnt = 0;
    private static int N;
    private static boolean[] rowCheck = new boolean[15];   // - check
    private static boolean[] lDiagCheck = new boolean[30]; // \ check
    private static boolean[] rDiagCheck = new boolean[30]; // / check

    private static void bt(int idx) {
        if (idx == N) {
            cnt++;
            return;
        }
        for (int i = 0; i < N; i++) {
            if (rowCheck[i] || lDiagCheck[N - 1 + idx - i] || rDiagCheck[idx + i]) continue;
            rowCheck[i] = true;
            lDiagCheck[N - 1 + idx - i] = true;
            rDiagCheck[idx + i] = true;
            bt(idx + 1);
            rowCheck[i] = false;
            lDiagCheck[N - 1 + idx - i] = false;
            rDiagCheck[idx + i] = false;
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        bt(0);
        System.out.println(cnt);
    }
}