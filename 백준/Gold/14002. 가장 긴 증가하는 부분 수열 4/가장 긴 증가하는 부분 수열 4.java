import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] sequence = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        // 수열의 i번째 원소를 마지막 원소로 가지는 가장 긴 증가하는 부분 수열의 길이
        int[] memo = new int[N];
        int maxIdx = 0; // 가장 긴 증가하는 부분 수열의 마지막 원소의 index
        for (int i = 0; i < N; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i]) {
                    max = Math.max(max, memo[j]);
                }
            }
            memo[i] = max + 1;
            if (memo[maxIdx] < memo[i]) {
                maxIdx = i;
            }
        }
        bw.write(memo[maxIdx] + "\n");

        // 가장 긴 증가하는 부분 수열 구하기
        Stack<Integer> stack = new Stack<>();
        stack.push(sequence[maxIdx]);
        int idx = maxIdx;
        while (memo[maxIdx] > 1) {
            if (memo[--idx] == memo[maxIdx] - 1) {
                stack.push(sequence[idx]);
                maxIdx = idx;
            }
        }

        // 가장 긴 증가하는 부분 수열 출력하기
        while (!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}