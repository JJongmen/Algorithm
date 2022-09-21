import java.util.*;

class Solution {
    private static int maxDiff = 0;
    private static String maxDiffStr = "00000000000";

    private static void dfs(int idx, int arrow, String comb, int[] info) {
        if (idx == info.length) {
            int scoreDiff = 0;
            for (int i = 0; i < info.length; i++) {
                if (info[i] == 0 && comb.charAt(i) == '0') {
                    continue;
                }
                if (info[i] >= comb.charAt(i) - '0') {
                    scoreDiff -= (10 - i);
                } else {
                    scoreDiff += (10 - i);
                }
            }
            if (scoreDiff > maxDiff) {
                maxDiff = scoreDiff;
                maxDiffStr = comb;
            } else if (scoreDiff == maxDiff) {
                for (int i = 10; i >= 0; i--) {
                    if (comb.charAt(i) > maxDiffStr.charAt(i)) {
                        maxDiffStr = comb;
                    } else if (comb.charAt(i) == maxDiffStr.charAt(i)) {
                        continue;
                    }
                    break;
                }
            }
            return;
        }
        // 0점을 쏠 차례일 때에는 남은 화살을 전부 써야함
        if (idx == info.length - 1) {
            dfs(idx + 1, 0, comb + (arrow), info);

        } else {
            //화살을 아예 안쏘는 경우
            dfs(idx + 1, arrow, comb + "0", info);
            //화살을 어피치가 쏜 화살 수보다 1개만큼 더 쏘는 경우
            int apeachArrow = info[idx];
            if (arrow > apeachArrow) {
                dfs(idx + 1, arrow - (apeachArrow + 1), comb + (apeachArrow + 1), info);
            }
        }
    }

    public int[] solution(int n, int[] info) {
        dfs(0, n, "", info);
        if (maxDiff == 0) {
            return new int[]{-1};
        }
        int[] answer = new int[11];
        for (int i = 0; i < maxDiffStr.length(); i++) {
            answer[i] = maxDiffStr.charAt(i) - '0';
        }
        return answer;
    }
}