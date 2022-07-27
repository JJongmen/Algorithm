import java.util.Scanner;

public class Main {
	
	static long pow(int a, int b, int c) {
		if(b == 0)
			return 1;
		a %= c;
		if(b % 2 == 0) {
			long tmp = pow(a, b / 2, c);
			return (tmp*tmp) % c;
		}
		long tmp = pow(a, (b-1) / 2, c);
		return (((tmp*tmp)%c)*a)%c;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int A = in.nextInt();
		int B = in.nextInt();
		int C = in.nextInt();
		System.out.println(pow(A, B, C));
		

	}
}