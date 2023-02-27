import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static char[] operator = new char[10];
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            makeExpr(1);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void makeExpr(int cur) throws IOException {
        if (cur == N) {
            if (calc() == 0) {
                StringBuilder expr = new StringBuilder();
                for (int i = 1; i < N; i++) {
                    expr.append(i).append(operator[i]);
                }
                expr.append(N);
                bw.write(expr + "\n");
            }
            return;
        }
        int nxt = cur + 1;
        operator[cur] = ' ';
        makeExpr(nxt);
        operator[cur] = '+';
        makeExpr(nxt);
        operator[cur] = '-';
        makeExpr(nxt);
    }

    private static int calc() {
        int result = 0;
        for (int i = N; i > 1; i--) {
            if (operator[i - 1] == '+') {
                result += i;
            } else if (operator[i - 1] == '-') {
                result -= i;
            } else {
                int num = i;
                int digit = 1;
                while (operator[i - 1] == ' ') {
                    num += --i * Math.pow(10, digit++);
                }
                if (i - 1 >= 1 && operator[i - 1] == '-') {
                    result -= num;
                } else {
                    result += num;
                }
            }
        }
        if (operator[1] != ' ') {
            result++;
        }
        return result;
    }
}