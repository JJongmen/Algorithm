import java.util.Scanner;

public class Main {
    static int idx;
    static int[] arr;
    static int[] memo;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = scan.nextInt();
        }

        memo = new int[n + 1];
        memo[idx++] = arr[1];
        for (int i = 2; i <= n; i++) {
            if (memo[idx - 1] < arr[i]) {
                memo[idx++] = arr[i];
            } else {
                memo[lowerBound(arr[i])] = arr[i];
            }
        }
        System.out.println(idx);
    }

    private static int lowerBound(int target) {
        int st = 0;
        int en = idx;
        while (st < en) {
            int mid = (st + en) / 2;
            if (memo[mid] >= target) en = mid;
            else st = mid + 1;
        }
        return st;
    }
}