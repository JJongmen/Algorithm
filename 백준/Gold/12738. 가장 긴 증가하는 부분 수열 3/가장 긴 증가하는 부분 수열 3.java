import java.util.Scanner;

public class Main {
    static int size;
    static int[] lis;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
        }

        lis = new int[N];
        lis[size++] = arr[0];
        for (int i = 1; i < N; i++) {
            if (lis[size - 1] < arr[i]) {
                lis[size++] = arr[i];
            } else {
                lis[lowerBound(arr[i])] = arr[i];
            }
        }
        System.out.println(size);
    }

    private static int lowerBound(int target) {
        int st = 0;
        int en = size;
        while (st < en) {
            int mid = (st + en) / 2;
            if (lis[mid] < target) st = mid + 1;
            else en = mid;
        }
        return st;
    }
}