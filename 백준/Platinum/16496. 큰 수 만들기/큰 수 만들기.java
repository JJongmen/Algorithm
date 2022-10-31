import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static String[] numStr = new String[1000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numStr[i] = st.nextToken();
        }

        Arrays.sort(numStr, 0, N, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < N; i++) {
            result.append(numStr[i]);
        }
        System.out.println(new BigInteger(result.toString()));
    }
}