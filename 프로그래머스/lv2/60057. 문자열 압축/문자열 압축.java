import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = s.length();
        // i개 단위로 나누었을 때 (1 <= i <= 문자열의 길이의 절반)
        for (int i = 1; i <= s.length() / 2; i++) {
            List<String> subStrs = new ArrayList<>();
            List<Integer> subStrCnts = new ArrayList<>();
            subStrs.add(s.substring(0, i));
            int cnt = 1;
            int idx = i;
            while (idx + i <= s.length()) {
                String temp = s.substring(idx, idx + i);
                if (temp.equals(subStrs.get(subStrs.size() - 1))) {
                    cnt++;
                } else {
                    subStrCnts.add(cnt);
                    cnt = 1;
                    subStrs.add(temp);
                }
                idx += i;
            }
            subStrCnts.add(cnt);
            if (idx < s.length()) {
                subStrs.add(s.substring(idx));
                subStrCnts.add(1);
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < subStrs.size(); j++) {
                int subStrCnt = subStrCnts.get(j);
                if (subStrCnt > 1) {
                    sb.append(subStrCnt);
                }
                sb.append(subStrs.get(j));
            }
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }
}