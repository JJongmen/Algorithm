import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int pos = Integer.parseInt(st.nextToken());
            if (pos < 0) {
                left.add(-pos);
            } else {
                right.add(pos);
            }
        }

        Collections.sort(left);
        Collections.sort(right);
        int max = 0;
        int result = 0;
        for (int i = left.size() - 1; i >= 0; i -= M) {
            Integer pos = left.get(i);
            max = Math.max(max, pos);
            result += pos;
        }
        for (int i = right.size() - 1; i >= 0; i -= M) {
            Integer pos = right.get(i);
            max = Math.max(max, pos);
            result += pos;
        }

        result = result * 2 - max;
        System.out.println(result);
    }
}