import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    static String A;
    static HashMap<Character, Integer> dictionary;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            A = br.readLine();
            String W = br.readLine();
            String S = br.readLine();

            dictionary = new HashMap<>();
            for (int i = 0; i < A.length(); i++) {
                dictionary.put(A.charAt(i), i);
            }

            int[] f = failure(W);
            List<Integer> result = new ArrayList<>();
            for (int shift = 0; shift < A.length(); shift++) {
                String original = decrypt(S, shift);
                if (isMatchOne(original, W, f)) {
                    result.add(shift);
                }
            }
            if (result.size() == 0) {
                bw.write("no solution\n");
            } else if (result.size() == 1) {
                bw.write("unique: " + result.get(0) + "\n");
            } else {
                bw.write("ambiguous:");
                for (Integer shift : result) {
                    bw.write(" " + shift);
                }
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isMatchOne(String s, String p, int[] f) {
        int j = 0;
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) j = f[j - 1];
            if (s.charAt(i) == p.charAt(j)) j++;
            if (j == p.length()) {
                j = f[j - 1];
                if (++cnt == 2) return false;
            }
        }
        return cnt == 1;
    }

    private static int[] failure(String s) {
        int[] f = new int[s.length()];
        int j = 0;
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) j = f[j - 1];
            if (s.charAt(i) == s.charAt(j)) f[i] = ++j;
        }
        return f;
    }

    private static String decrypt(String password, int shift) {
        StringBuilder original = new StringBuilder();
        shift = A.length() - shift;
        for (int i = 0; i < password.length(); i++) {
            original.append(A.charAt((dictionary.get(password.charAt(i)) + shift) % A.length()));
        }
        return original.toString();
    }
}