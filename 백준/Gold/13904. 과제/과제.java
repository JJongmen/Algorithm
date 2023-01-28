import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 1000;

    static int N;
    static boolean[] schedule = new boolean[MAX + 1];

    static class Quest {
        int dDay;
        int point;

        public Quest(int dDay, int point) {
            this.dDay = dDay;
            this.point = point;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Quest> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.point == o2.point) {
                return o1.dDay - o2.dDay;
            }
            return o2.point - o1.point;
        });

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Quest(d, w));
        }

        int result = 0;
        while (!pq.isEmpty()) {
            Quest quest = pq.poll();
            int day = quest.dDay;
            while (day > 0 && schedule[day]) {
                day--;
            }

            if (day == 0) continue;
            schedule[day] = true;
            result += quest.point;
        }
        System.out.println(result);
    }
}