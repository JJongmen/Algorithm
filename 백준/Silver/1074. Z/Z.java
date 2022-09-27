import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int r = in.nextInt();
		int c = in.nextInt();
		int order = 0;
		int length = (int)Math.pow(2, N);
		
		while (length >= 2) {
			length /= 2;
			if (0 <= r && r < length && 0 <= c && c < length) {		// 1사분면
				continue;
			} else if (0 <= r && r < length && length <= c && c < length * 2) {	// 2사분면
				c -= length;
				order += length * length;
			} else if (length <= r && r < length * 2 && 0 <= c && c < length) {	// 3사분면
				r -= length;
				order += length * length * 2;
			} else if (length <= r && r < length * 2 && length <= c && c < length * 2) {	// 4사분면
				r -= length;
				c -= length;
				order += length * length * 3;
			}
		}
		System.out.println(order);

	}
}