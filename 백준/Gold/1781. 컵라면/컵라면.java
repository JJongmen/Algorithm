import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int result;
    static List<Problem> problems = new ArrayList<>();
    static Queue<Integer> prices = new PriorityQueue<>();
    
    static class Problem {
        int deadLine;
        int price;

        public Problem(int deadLine, int price) {
            this.deadLine = deadLine;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            problems.add(new Problem(deadLine, price));
        }

        problems.sort(Comparator.comparingInt(o -> o.deadLine));
        for (Problem problem : problems) {
            if (prices.size() < problem.deadLine) {
                prices.offer(problem.price);
            } else if (prices.size() == problem.deadLine && prices.peek() < problem.price){
                prices.poll();
                prices.offer(problem.price);
            }
        }

        for (Integer price : prices) {
            result += price;
        }
        System.out.println(result);
    }
}