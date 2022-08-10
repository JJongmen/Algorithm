import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int A = in.nextInt();
		int B = in.nextInt();
		int tmp = B;
		int cnt = 0;
		
		while (tmp > A) {
			if (tmp % 2 == 0) {
				tmp /= 2;
			} else if (tmp % 10 == 1) {
				tmp /= 10;
			} else {
				break;
			}
			cnt++;
		}
		
		if (tmp == A) {
			cnt++;
		} else {
			cnt = -1;
		}
		System.out.println(cnt);

	}
}