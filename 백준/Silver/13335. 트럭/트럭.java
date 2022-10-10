import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] trucks = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            trucks[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> que = new LinkedList<>();
        for (int i = 0; i < w; i++) {
            que.offer(0);
        }
        int time = 0;
        int totalWeight = 0;
        int truckIdx = 0;
        for (; time <= 100001; time++) {
            if (truckIdx == n) {
                time += w;
                break;
            }
            totalWeight -= que.poll();
            if (totalWeight + trucks[truckIdx] <= L) {
                totalWeight += trucks[truckIdx];
                que.offer(trucks[truckIdx++]);
            } else {
                que.offer(0);
            }
        }
        System.out.println(time);
    }
}