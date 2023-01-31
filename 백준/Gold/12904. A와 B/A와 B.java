import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        StringBuilder sb = new StringBuilder(T);
        int sLength = S.length();
        while (sLength < sb.length()) {
            int lastIdx = sb.length() - 1;
            char last = sb.charAt(lastIdx);
            sb.deleteCharAt(lastIdx);
            if (last == 'B') {
                sb.reverse();
            }
        }
        System.out.println(S.equals(sb.toString()) ? 1 : 0);
    }
}