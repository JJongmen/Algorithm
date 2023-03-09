import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1034 {
    static int N, M, K;
    static String[] board = new String[50];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine();
        }
        K = Integer.parseInt(br.readLine());

        int result = 0;
        for (int i = 0; i < N; i++) {
            int offCnt = 0;
            for (int j = 0; j < M; j++) {
                if (board[i].charAt(j) == '0') {
                    offCnt++;
                }
            }
            if (offCnt <= K && offCnt % 2 == K % 2) {
                int sameCnt = 0;
                for (int j = 0; j < N; j++) {
                    if (board[i].equals(board[j])) {
                        sameCnt++;
                    }
                }
                result = Math.max(result, sameCnt);
            }
        }
        System.out.println(result);
    }
}
