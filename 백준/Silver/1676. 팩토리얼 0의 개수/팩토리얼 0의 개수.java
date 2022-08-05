import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int cnt = 0;
		for (int i = 5; i <= N; i += 5) {
			int tmp = i;
			while (tmp >= 5) {
				if (tmp % 5 == 0) {
					cnt++;
					tmp /= 5;
				} else {
					break;
				}
			}
		}
		System.out.println(cnt);
	}
}
