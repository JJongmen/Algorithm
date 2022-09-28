import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Main {
    private static int[] arr = new int[10];
    private static int N;
    private static int M;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static void bt(int cur) throws IOException {
        if (cur == M) {
            for (int i = 0; i < M; i++) {
                bw.write(arr[i] + " ");
            }
            bw.write('\n');
            return;
        }
        for (int i = 1; i <= N; i++) {
            arr[cur] = i;
            bt(cur + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        M = scan.nextInt();
        bt(0);
        bw.flush();
        bw.close();
    }
}