import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] sequence = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int[][] memo = new int[N][3];
        //i번째 원소를 마지막 원소로 가지는 가장 긴 증가하는 부분 수열의 길이
        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i]) {
                    max = Math.max(max, memo[j][0]);
                }
            }
            memo[i][0] = max + 1;
        }

        //i번째 원소를 첫번째 원소로 가지는 가장 긴 감소하는 부분 수열의 길이
        for (int i = N - 1; i >= 0; i--) {
            int max = 0;
            for (int j = N - 1; j > i; j--) {
                if (sequence[j] < sequence[i]) {
                    max = Math.max(max, memo[j][1]);
                }
            }
            memo[i][1] = max + 1;
        }

        //i번째 원소를 변곡점으로 가지는 가장 긴 바이토닉 부분 수열의 길이
        int max = 0;
        for (int i = 0; i < N; i++) {
            memo[i][2] = memo[i][0] + memo[i][1] - 1;
            max = Math.max(max, memo[i][2]);
        }
        
        System.out.println(max);
    }
}