import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static class Height {
        int height;
        int cnt;

        public Height(int height, int cnt) {
            this.height = height;
            this.cnt = cnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] heights = new int[N];
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        long result = 0;
        Stack<Height> stack = new Stack<>();
        stack.push(new Height(heights[0], 1));
        for (int i = 1; i < N; i++) {
            int cur = heights[i];
            while (!stack.isEmpty() && stack.peek().height < cur) {
                result += stack.pop().cnt;
            }
            int sameCnt = 1;
            if (!stack.isEmpty() && stack.peek().height == cur) {
                sameCnt = stack.pop().cnt;
                result += sameCnt++;
            }
            if (!stack.isEmpty()) {
                result++;
            }
            stack.push(new Height(cur, sameCnt));
        }
        System.out.println(result);
    }
}