import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int K = in.nextInt();
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 1; i <= N; i++)
			q.offer(i);
		System.out.print("<");
		while(!q.isEmpty()) {
			for(int i = 0; i < K-1; i++)
				q.offer(q.poll());
			if(q.size() == 1) {
				System.out.print(q.poll());
				break;
			}
			System.out.print(q.poll() + ", ");
		}
		System.out.print(">");
			
	}
}