import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        
        Stack<Character> stack = new Stack<>();
        int pCnt = 0;
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (ch == 'A') {
                if (pCnt >= 2 && i + 1 < S.length() && S.charAt(i + 1) == 'P') {
                    stack.pop();
                    pCnt--;
                    i++;
                } else {
                    pCnt = 0;
                    stack.push(ch);
                }
            } else {
                stack.push(ch);
                pCnt++;
            }
        }
        if (stack.size() == 1 && stack.peek() == 'P') {
            System.out.println("PPAP");
        } else {
            System.out.println("NP");
        }
    }
}