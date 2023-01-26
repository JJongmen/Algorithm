import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static final int INF = 500005;
    static int N, H;
    static int[] down, up;
    static int[] downCnt, upCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        int halfCnt = N / 2;
        down = new int[halfCnt];
        up = new int[halfCnt];
        for (int i = 0; i < halfCnt; i++) {
            down[i] = Integer.parseInt(br.readLine());
            up[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(down);
        Arrays.sort(up);
        downCnt = new int[H + 1];
        upCnt = new int[H + 1];
        for (int i = 1; i <= H; i++) {
            downCnt[i] = halfCnt - lowerBound(down, i);
            upCnt[i] = halfCnt - lowerBound(up, i);
        }

        int min = INF;
        int cnt = 0;
        for (int i = 1; i <= H; i++) {
            int breakCnt = downCnt[i] + upCnt[H + 1 - i];
            if (min > breakCnt) {
                min = breakCnt;
                cnt = 1;
            } else if (min == breakCnt) {
                cnt++;
            }
        }
        System.out.println(min + " " + cnt);
    }

    private static int lowerBound(int[] arr, int target) {
        int st = 0;
        int en = arr.length;
        while (st < en) {
            int mid = (st + en) / 2;
            if (arr[mid] < target) st = mid + 1;
            else en = mid;
        }
        return st;
    }
}