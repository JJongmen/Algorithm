import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Lesson> lessons = new ArrayList<>();
    static Queue<Integer> pq = new PriorityQueue<>();

    static class Lesson {
        int start;
        int end;

        Lesson(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lessons.add(new Lesson(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        lessons.sort(((o1, o2) -> (o1.start - o2.start != 0) ? o1.start - o2.start : o1.end - o2.end));

        pq.offer(lessons.get(0).end);
        lessons.remove(0);
        for (Lesson lesson : lessons) {
            if (pq.peek() <= lesson.start) {
                pq.poll();
            }
            pq.offer(lesson.end);
        }

        System.out.println(pq.size());
    }
}