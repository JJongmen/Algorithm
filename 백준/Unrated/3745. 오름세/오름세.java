import java.io.*;
import java.util.Scanner;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            N = sc.nextInt();
            int[] arr = new int[N];
            int idx = 0;
            for (int i = 0; i < N; i++) {
                int p = sc.nextInt();
                if (i == 0) {
                    arr[idx++] = p;
                    continue;
                }
                if (arr[idx - 1] < p) {
                    arr[idx++] = p;
                    continue;
                }
                arr[lower_bound(arr, 0, idx, p)] = p;
            }
            System.out.println(idx);
        }
    }

    private static int lower_bound(int[] arr, int start, int end, int target) {
        int l = start;
        int r = end;
        while (l < r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}