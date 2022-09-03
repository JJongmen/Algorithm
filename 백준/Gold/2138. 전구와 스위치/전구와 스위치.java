import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final int IMPOSSIBLE = 100001;

    private static void change(boolean[] temp, int idx) {
        temp[idx] = !temp[idx];
        if (idx == 0) {
            temp[idx + 1] = !temp[idx + 1];
        } else if (idx == temp.length - 1) {
            temp[idx - 1] = !temp[idx - 1];
        } else {
            temp[idx - 1] = !temp[idx - 1];
            temp[idx + 1] = !temp[idx + 1];
        }
    }

    public static int solve(String before, String after) {
        int answer = IMPOSSIBLE;
        boolean[] temp = getBooleans(before);
        boolean[] aft = getBooleans(after);

        int cnt = 0;
        cnt = getCnt(temp, aft, cnt);
        if (Arrays.equals(temp, aft)) {
            answer = Math.min(answer, cnt);
        }

        temp = getBooleans(before);
        change(temp, 0);
        cnt = 1;
        cnt = getCnt(temp, aft, cnt);
        if (Arrays.equals(temp, aft)) {
            answer = Math.min(answer, cnt);
        }
        return answer == IMPOSSIBLE ? -1 : answer;
    }

    private static int getCnt(boolean[] temp, boolean[] aft, int cnt) {
        for (int i = 1; i < temp.length; i++) {
            if (aft[i - 1] != temp[i - 1]) {
                change(temp, i);
                cnt++;
            }
        }
        return cnt;
    }

    private static boolean[] getBooleans(String lightBulbs) {
        boolean[] temp;
        temp = new boolean[lightBulbs.length()];
        for (int i = 0; i < lightBulbs.length(); i++) {
            if (lightBulbs.charAt(i) == '1') {
                temp[i] = true;
            }
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String before = br.readLine();
        String after = br.readLine();
        System.out.println(solve(before, after));
    }
}