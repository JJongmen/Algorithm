import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] nameLength = new int[300000];
    static int[] lengthCounts = new int[21];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        long result = 0;
        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            nameLength[i] = name.length();
            result += lengthCounts[nameLength[i]]++;
            if (i < K) continue;
            lengthCounts[nameLength[i - K]]--;
        }
        System.out.println(result);
    }
}