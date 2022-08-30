import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] times;
    static List<Integer> currentWorks = new ArrayList<>();
    static List<List<Integer>> precedingWork = new ArrayList<>();
    static List<List<Integer>> precededWork = new ArrayList<>();
    static Queue<Integer> que = new PriorityQueue<>((o1, o2) -> times[o1] - times[o2]);

    static int solve() {
        int minTime = 0;
        while (!que.isEmpty()) {
            Integer work = que.poll();
            currentWorks.remove(work);
            minTime += times[work];
            for (int currentWork : currentWorks) {
                times[currentWork] -= times[work];
            }
            for (int nextWork : precededWork.get(work)) {
                precedingWork.get(nextWork).remove(work);
                if (precedingWork.get(nextWork).isEmpty()) {
                    que.offer(nextWork);
                    currentWorks.add(nextWork);
                }
            }
        }
        return minTime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        times = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            precedingWork.add(new LinkedList<>());
            precededWork.add(new LinkedList<>());
        }
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
            int workCnt = Integer.parseInt(st.nextToken());
            if (workCnt == 0) {
                que.offer(i);
                currentWorks.add(i);
            }
            for (int j = 0; j < workCnt; j++) {
                int work = Integer.parseInt(st.nextToken());
                precedingWork.get(i).add(work);
                precededWork.get(work).add(i);
            }
        }
        System.out.println(solve());
    }
}