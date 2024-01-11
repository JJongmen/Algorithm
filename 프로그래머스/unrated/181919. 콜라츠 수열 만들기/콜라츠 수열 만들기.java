import java.util.*;

class Solution {
    public int[] solution(int n) {
        List<Integer> seq = new ArrayList();
        int tmp = n;
        seq.add(tmp);
        while (tmp != 1) {
            if (tmp % 2 == 0) { // 짝수
                tmp /= 2;
            } else { // 홀수
                tmp = 3 * tmp + 1;
            }
            seq.add(tmp);
        }
        return seq.stream().mapToInt(i->i).toArray();
    }
}