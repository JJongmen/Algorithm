import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static final int INF = 500005;
    static int N;
    static int[][] studentRanks = new int[500001][3];
    static List<Student> ranking = new ArrayList<>();
    static int[] tree = new int[500000 * 4 + 1];

    static class Student {
        int rank1, rank2, rank3;

        public Student(int rank1, int rank2, int rank3) {
            this.rank1 = rank1;
            this.rank2 = rank2;
            this.rank3 = rank3;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int rank = 1; rank <= N; rank++) {
                int student = Integer.parseInt(st.nextToken());
                studentRanks[student][i] = rank;
            }
        }

        // 첫 번째 시험을 기준으로 오름차순 정렬
        for (int student = 1; student <= N; student++) {
            ranking.add(new Student(studentRanks[student][0], studentRanks[student][1], studentRanks[student][2]));
        }
        ranking.sort(Comparator.comparingInt(o -> o.rank1));

        // 트리를 최대값으로 초기화
        Arrays.fill(tree, INF);

        int result = 0;
        for (Student student : ranking) {
            int minRank = min(1, 1, N, 1, student.rank2 - 1);
            if (minRank == INF || minRank > student.rank3) result++;
            update(1, 1, N, student.rank2, student.rank3);
        }
        System.out.println(result);
    }

    private static void update(int node, int start, int end, int index, int val) {
        if (end < index || index < start) return;
        if (start == end) {
            tree[node] = val;
            return;
        }
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        update(lc, start, mid, index, val);
        update(rc, mid + 1, end, index, val);
        tree[node] = Math.min(tree[lc], tree[rc]);
    }

    private static int min(int node, int start, int end, int left, int right) {
        if (end < left || right < start) return INF;
        if (left <= start && end <= right) return tree[node];
        int lc = node * 2;
        int rc = lc + 1;
        int mid = (start + end) / 2;
        return Math.min(min(lc, start, mid, left, right), min(rc, mid + 1, end, left, right));
    }

}