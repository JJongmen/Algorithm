import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] counts = new int[10];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        int st = 1;
        int en = N;
        int place = 1;
        while (st <= en) {
            while (st <= en && st % 10 != 0) {
                countDigit(st, place);
                st++;
            }
            if (st > en) {
                break;
            }
            while (st <= en && en % 10 != 9) {
                countDigit(en, place);
                en--;
            }

            st /= 10;
            en /= 10;
            for (int i = 0; i < 10; i++) {
                counts[i] += place * (en - st + 1);
            }
            place *= 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int count : counts) {
            sb.append(count).append(" ");
        }
        System.out.println(sb);
    }

    private static void countDigit(int number, int place) {
        while (number > 0) {
            counts[number % 10] += place;
            number /= 10;
        }
    }
}