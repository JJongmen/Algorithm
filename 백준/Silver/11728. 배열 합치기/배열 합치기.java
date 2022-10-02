import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arrA = new int[1000003];
        int[] arrB = new int[1000003];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        int idxA = 0;
        int idxB = 0;
        while (idxA < N || idxB < M) {
            if (idxA >= N) {
                bw.write(arrB[idxB++] + " ");
                continue;
            } else if (idxB >= M) {
                bw.write(arrA[idxA++] + " ");
                continue;
            }
            if (arrA[idxA] < arrB[idxB]) {
                bw.write(arrA[idxA++] + " ");
            } else {
                bw.write(arrB[idxB++] + " ");
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }
}