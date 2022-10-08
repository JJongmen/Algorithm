import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] freq = new int[2000005];
        for (int i = 0; i < N; i++) {
            freq[1000000 + Integer.parseInt(br.readLine())]++;
        }
        for (int i = -1000000; i <= 1000000; i++) {
            for (int j = 0; j < freq[1000000 + i]; j++) {
                bw.write(i + "\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}