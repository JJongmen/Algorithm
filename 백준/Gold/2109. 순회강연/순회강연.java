import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static class Lecture {
        int d_day;
        int cost;

        public Lecture(int d_day, int cost) {
            this.d_day = d_day;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Lecture[] lectures = new Lecture[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(d, p);
        }

        Arrays.sort(lectures, (o1, o2) -> o2.cost - o1.cost);
        boolean[] schedule = new boolean[10001];
        int result = 0;
        for (Lecture lecture : lectures) {
            int day = lecture.d_day;
            while (day > 0 && schedule[day]) {
                day--;
            }
            if (day == 0) continue;
            schedule[day] = true;
            result += lecture.cost;
        }

        System.out.println(result);
    }
}