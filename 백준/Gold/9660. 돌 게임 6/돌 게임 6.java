import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long N = scan.nextLong();
        if (N % 7 == 0 || (N - 2) % 7 == 0) {
            System.out.println("CY");
        } else {
            System.out.println("SK");
        }
    }
}