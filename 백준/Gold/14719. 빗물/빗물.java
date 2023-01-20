import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static int[] heights, lMax, rMax;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        heights = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        lMax = new int[W];
        lMax[0] = heights[0];
        for (int i = 1; i < W; i++) {
            lMax[i] = Math.max(lMax[i - 1], heights[i]);
        }

        rMax = new int[W];
        rMax[W - 1] = heights[W - 1];
        for (int i = W - 2; i >= 0; i--) {
            rMax[i] = Math.max(rMax[i + 1], heights[i]);
        }

        int result = 0;
        for (int i = 0; i < W; i++) {
            result += Math.min(lMax[i], rMax[i]) - heights[i];
        }
        System.out.println(result);
    }
}