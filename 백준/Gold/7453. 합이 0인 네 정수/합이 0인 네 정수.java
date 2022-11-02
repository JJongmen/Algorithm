import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] nums = new int[4][4000];
    static int[][] two = new int[2][16000000];
    static int size;
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                nums[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                two[0][size] = nums[0][i] + nums[1][j];
                two[1][size++] = nums[2][i] + nums[3][j];
            }
        }

        Arrays.sort(two[0], 0, size);
        Arrays.sort(two[1], 0, size);
        int l = 0;
        int r = size - 1;
        while (l < size && r >= 0) {
            int lValue = two[0][l];
            int rValue = two[1][r];
            int sum = lValue + rValue;
            if (sum > 0) r--;
            else if (sum < 0) l++;
            else {
                long lCnt = 0;
                while (l < size && two[0][l] == lValue) {
                    lCnt++;
                    l++;
                }
                long rCnt = 0;
                while (0 <= r && two[1][r] == rValue) {
                    rCnt++;
                    r--;
                }
                result += lCnt * rCnt;
            }
        }
        System.out.println(result);
    }
}