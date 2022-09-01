import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        Queue<Integer> que = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            que.offer(scan.nextInt());
        }

        if (N == 1) {
            System.out.println(0);
            return;
        }

        int sum = 0;
        while (!que.isEmpty()) {
            int temp = que.poll() + que.poll();
            if (!que.isEmpty()) {
                que.offer(temp);
            }
            sum += temp;
        }

        System.out.println(sum);
    }
}