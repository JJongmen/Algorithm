import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int result = 0;
        char first = s.charAt(0);
        for (char c : s.toCharArray()) {
            if (first == c) {
                result++;
            }
        }
        System.out.println(result);
    }
}