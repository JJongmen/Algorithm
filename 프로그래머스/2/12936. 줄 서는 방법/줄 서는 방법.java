import java.util.*;

class Solution {
    private static long[] fact = new long[21];
    
    private long factorial(int n) {
        if (fact[n] != 0) return fact[n];
        if (n == 0) return fact[0] = 1;
        return fact[n] = n * factorial(n - 1);
    }
    
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> remain = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            remain.add(i);
        }
        int tmp = n - 1;
        k--;
        for (int i = 0; i < n; i++) {
            int a = (int) (k / factorial(tmp));
            k %= factorial(tmp--);
            answer[i] = remain.remove(a);
        }
        return answer;
    }
}