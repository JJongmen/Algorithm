import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private final static int CLOCKWISE = 1;
    private final static int ANTI_CLOCKWISE = -1;
    private static int[] upGear;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] gears = new String[5];
        for (int i = 1; i <= 4; i++) {
            gears[i] = br.readLine();
        }
        upGear = new int[5];
        int K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int[] turnDirection = new int[5];
            turnDirection[num] = direction;

            /**
             * 0이면 회전 X
             * 1이면 시계방향 회전
             * -1이면 반시계방향 회전
             */
            int tmp = num;
            int tmpDir = direction;
            while (tmp > 1) {
                int rightIdx = (upGear[tmp - 1] + 2) % 8;
                int leftIdx = (upGear[tmp] + 6) % 8;
                if (gears[tmp].charAt(leftIdx) != gears[tmp - 1].charAt(rightIdx)) {
                    turnDirection[tmp - 1] = turnDirection[tmp] * -1;
                } else break;
                tmp--;
            }
            tmp = num;
            tmpDir = direction;
            while (tmp < 4) {
                int rightIdx = (upGear[tmp] + 2) % 8;
                int leftIdx = (upGear[tmp + 1] + 6) % 8;
                if (gears[tmp].charAt(rightIdx) != gears[tmp + 1].charAt(leftIdx)) {
                    turnDirection[tmp + 1] = turnDirection[tmp] * -1;
                } else break;
                tmp++;
            }
            for (int i = 1; i <= 4; i++) {
                turn(i, turnDirection[i]);
            }
        }

        int sum = 0;
        for (int i = 1; i <= 4; i++) {
            sum += (1 << (i - 1)) * (gears[i].charAt(upGear[i]) - '0');
        }
        System.out.println(sum);
    }

    private static void turn(int num, int direction) {
        if (direction == CLOCKWISE) {
            upGear[num] = (upGear[num] + 7) % 8;
        } else if (direction == ANTI_CLOCKWISE) {
            upGear[num] = (upGear[num] + 1) % 8;
        }
    }
}