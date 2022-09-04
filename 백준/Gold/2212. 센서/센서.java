import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        if (N <= K) {
            System.out.println(0);
            return;
        }
        int[] sensors = new int[N];
        int[] distance = new int[N];
        for (int i = 0; i < N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensors);
        int answer = sensors[N - 1] - sensors[0];
        for (int i = 1; i < N; i++) {
            distance[i] = sensors[i] - sensors[i - 1];
        }
        Arrays.sort(distance);
        for (int i = N - 1; i > N - K; i--) {
            answer -= distance[i];
        }
        System.out.println(answer);
    }
}