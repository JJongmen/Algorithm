import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String magicStr = br.readLine();
        String devilStr = br.readLine();
        String angelStr = br.readLine();
        int[][] memoDevil = new int[magicStr.length() + 1][devilStr.length() + 1];
        int[][] memoAngel = new int[magicStr.length() + 1][devilStr.length() + 1];

        Arrays.fill(memoDevil[0], 1);
        Arrays.fill(memoAngel[0], 1);
        for (int i = 1; i <= magicStr.length(); i++) {
            for (int j = 1; j <= devilStr.length(); j++) {
                memoDevil[i][j] = memoDevil[i][j - 1];
                memoAngel[i][j] = memoAngel[i][j - 1];
                if (magicStr.charAt(i - 1) == devilStr.charAt(j - 1)) {
                    memoDevil[i][j] += memoAngel[i - 1][j - 1];
                }
                if (magicStr.charAt(i - 1) == angelStr.charAt(j - 1)) {
                    memoAngel[i][j] += memoDevil[i - 1][j - 1];
                }
            }
        }
        
        System.out.println(memoDevil[magicStr.length()][devilStr.length()]
                + memoAngel[magicStr.length()][angelStr.length()]);
    }
}