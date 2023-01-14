import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        Arrays.sort(arr);
        if (arr[0] != 1) {
            System.out.println(1);
            return;
        }

        int max = 1;
        for (int i = 1; i < N; i++) {
            int nxtMax = max + arr[i];
            if (arr[i] > max + 1) break;
            max = nxtMax;
        }
        System.out.println(max + 1);
    }
}