import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N, M;
    static int[] diffs = new int[100003];
    static TreeSet<Problem> problems = new TreeSet<>((o1, o2) -> {
        if (o1.diff == o2.diff) {
            return o1.num - o2.num;
        }
        return o1.diff - o2.diff;
    });

    static class Problem {
        int num;
        int diff;

        public Problem(int num, int diff) {
            this.num = num;
            this.diff = diff;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Problem problem = (Problem) o;
            return num == problem.num;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            problems.add(new Problem(P, L));
            diffs[P] = L;
        }

        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if ("add".equals(command)) {
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                problems.add(new Problem(P, L));
            } else if ("recommend".equals(command)) {
                int x = Integer.parseInt(st.nextToken());
                int P;
                if (x == 1) {
                    P = problems.last().num;
                } else {
                    P = problems.first().num;
                }
                bw.write(String.format("%d\n", P));
            } else {
                int P = Integer.parseInt(st.nextToken());
                problems.remove(new Problem(P, diffs[P]));
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}