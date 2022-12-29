import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        long[] alphabets = new long[26];
        while (N-- > 0) {
            String s = scan.next();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                alphabets[c - 'A'] += (long) Math.pow(10, s.length() - 1 - i);
            }
        }

        long[] value = new long[10];
        int cnt = 0;
        for (int i = 0; i < 26; i++) {
            if (alphabets[i] == 0) continue;
            value[cnt++] = alphabets[i];
        }
        Arrays.sort(value);
        int num = 9;
        long result = 0;
        for (int i = 9; i >= 10 - cnt; i--) {
            result += value[i] * num--;
        }
        System.out.println(result);
    }
}