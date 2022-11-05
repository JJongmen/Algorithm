import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] stats = new int[1000][1000];
    static int[] pt = new int[1000];
    static int result = Integer.MAX_VALUE;

    static class Student {
        int stat;
        int num;

        public Student(int stat, int num) {
            this.stat = stat;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                stats[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(stats[i], 0, M);
        }

        int maxStat = 0;
        PriorityQueue<Student> pq = new PriorityQueue<>(Comparator.comparingInt(student -> student.stat));
        for (int num = 0; num < N; num++) {
            int stat = stats[num][0];
            maxStat = Math.max(maxStat, stat);
            pq.offer(new Student(stat, num));
        }

        while (!pq.isEmpty()) {
            Student minStudent = pq.poll();
            result = Math.min(result, maxStat - minStudent.stat);
            if (pt[minStudent.num] == M) break;
            int nxtStat = stats[minStudent.num][pt[minStudent.num]++];
            maxStat = Math.max(maxStat, nxtStat);
            pq.offer(new Student(nxtStat, minStudent.num));
        }
        System.out.println(result);
    }
}