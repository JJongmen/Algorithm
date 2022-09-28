import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    private static int N;
    private static int M;
    private static int[] arr = new int[10];
    private static boolean[] isUsed = new boolean[10];
    private static BufferedWriter bw;

    private static void bt(int idx) throws IOException {
        if (idx == M) {
            for (int i = 0; i < M; i++) {
                bw.write(arr[i] + " ");
            }
            bw.write('\n');
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (!isUsed[i]) {
                arr[idx] = i;
                isUsed[i] = true;
                bt(idx + 1);
                isUsed[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bt(0);
        bw.flush();
        bw.close();
    }
}