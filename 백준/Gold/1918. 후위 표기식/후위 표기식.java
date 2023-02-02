import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[] expr = scan.next().toCharArray();

        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char ch : expr) {
            if ('A' <= ch && ch <= 'Z') {
                result.append(ch);
            } else {
                if (ch == '(') {
                    stack.push(ch);
                } else if (ch == ')') {
                    while (stack.peek() != '(') {
                        result.append(stack.pop());
                    }
                    stack.pop();
                } else if (ch == '+' || ch == '-') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        result.append(stack.pop());
                    }
                    stack.push(ch);
                } else if (ch == '*' || ch == '/') {
                    while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                        result.append(stack.pop());
                    }
                    stack.push(ch);
                }
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        System.out.println(result);
    }
}