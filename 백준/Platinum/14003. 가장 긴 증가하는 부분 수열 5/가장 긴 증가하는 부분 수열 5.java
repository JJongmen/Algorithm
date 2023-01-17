import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int size;
    static int[] L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        L = new int[N];
        int[] P = new int[N];
        L[size++] = arr[0];
        P[0] = 0;

        for (int i = 1; i < N; i++) {
            if (L[size - 1] < arr[i]) {
                P[i] = size;
                L[size++] = arr[i];
            } else {
                int idx = lowerBound(arr[i]);
                P[i] = idx;
                L[idx] = arr[i];
            }
        }

        Stack stack = new Stack();
        int target = size - 1;
        for (int i = N - 1; i >= 0; i--) {
            if (P[i] != target) continue;
            stack.push(arr[i]);
            if (--target == -1) break;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(size);
        System.out.println(sb);
    }

    private static int lowerBound(int target) {
        int st = 0;
        int en = size;
        while (st < en) {
            int mid = (st + en) / 2;
            if (target <= L[mid]) en = mid;
            else st = mid + 1;
        }
        return st;
    }
}