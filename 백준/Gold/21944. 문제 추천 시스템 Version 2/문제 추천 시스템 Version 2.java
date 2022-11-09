import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static int N, M;
    static int P, L, G;
    static List<TreeSet<Problem>> algos = new ArrayList<>();
    static TreeSet<Problem> problems = new TreeSet<>(problemComparator());
    static Map<Integer, Problem> infos = new HashMap<>();

    static {
        for (int i = 0; i <= 100; i++) {
            algos.add(new TreeSet<>(problemComparator()));
        }
    }

    private static Comparator<Problem> problemComparator() {
        return (o1, o2) -> {
            if (o1.diff == o2.diff) {
                return o1.num - o2.num;
            }
            return o1.diff - o2.diff;
        };
    }

    static class Problem {
        int num;
        int diff;
        int algo;

        public Problem(int num, int diff, int algo) {
            this.num = num;
            this.diff = diff;
            this.algo = algo;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            add(st);
        }

        M = Integer.parseInt(br.readLine());
        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if ("add".equals(cmd)) {
                add(st);
            } else if ("solved".equals(cmd)) {
                solved(st);
            } else if ("recommend".equals(cmd)) {
                recommend(bw, st);
            } else if ("recommend2".equals(cmd)) {
                recommend2(bw, st);
            } else if ("recommend3".equals(cmd)) {
                recommend3(bw, st);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void recommend(BufferedWriter bw, StringTokenizer st) throws IOException {
        G = Integer.parseInt(st.nextToken());
        int type = Integer.parseInt(st.nextToken());
        if (type == 1) {
            P = algos.get(G).last().num;
        } else {
            P = algos.get(G).first().num;
        }
        bw.write(String.format("%d\n", P));
    }

    private static void recommend2(BufferedWriter bw, StringTokenizer st) throws IOException {
        int type = Integer.parseInt(st.nextToken());
        if (type == 1) {
            P = problems.last().num;
        } else {
            P = problems.first().num;
        }
        bw.write(String.format("%d\n", P));
    }

    private static void recommend3(BufferedWriter bw, StringTokenizer st) throws IOException {
        int type = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        Problem problem;
        if (type == 1) {
            problem = problems.ceiling(new Problem(1, L, 1));
        } else {
            problem = problems.lower(new Problem(0, L, 0));
        }
        if (problem == null) {
            bw.write("-1\n");
        } else {
            bw.write(String.format("%d\n", problem.num));
        }
    }

    private static void add(StringTokenizer st) {
        P = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        Problem problem = new Problem(P, L, G);
        algos.get(G).add(problem);
        problems.add(problem);
        infos.put(P, problem);
    }

    private static void solved(StringTokenizer st) {
        P = Integer.parseInt(st.nextToken());
        Problem problem = infos.get(P);
        algos.get(problem.algo).remove(problem);
        problems.remove(problem);
        infos.remove(P);
    }
}