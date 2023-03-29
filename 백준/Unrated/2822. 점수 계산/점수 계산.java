import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    static class ScoreInfo {
        int score, idx;

        public ScoreInfo(int score, int idx) {
            this.score = score;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<ScoreInfo> pq = new PriorityQueue<>((o1, o2) -> o2.score - o1.score);
        for (int i = 1; i <= 8; i++) {
            int score = Integer.parseInt(br.readLine());
            pq.offer(new ScoreInfo(score, i));
        }
        int[] finalScore = new int[5];
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            ScoreInfo scoreInfo = pq.poll();
            finalScore[i] = scoreInfo.idx;
            sum += scoreInfo.score;
        }
        Arrays.sort(finalScore);
        StringBuilder sb = new StringBuilder();
        sb.append(sum).append('\n');
        for (int i = 0; i < 5; i++) {
            sb.append(finalScore[i]).append(' ');
        }
        System.out.println(sb);
    }
}