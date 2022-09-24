import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] tower = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            tower[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[N + 1];
        for (int i = N; i > 0; i--) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            while (!stack.isEmpty()) {
                if (tower[i] >= tower[stack.peek()]) {
                    answer[stack.pop()] = i;
                    continue;
                }
                break;
            }
            stack.push(i);
        }

        for (int i = 1; i <= N; i++) {
            bw.write(answer[i] + " ");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}