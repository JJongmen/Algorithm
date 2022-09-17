import java.util.regex.*;

class Solution {
    public int solution(String dartResult) {
        Pattern pattern = Pattern.compile("(\\d+)([SDT])([*#]*)");
        Matcher matcher = pattern.matcher(dartResult);
        int sum = 0;
        int[] scores = new int[4];
        int currentIdx = 1;
        while (matcher.find()) {
            scores[currentIdx] = Integer.parseInt(matcher.group(1));
            String area = matcher.group(2);
            if (area.equals("D")) {
                scores[currentIdx] = (int) Math.pow(scores[currentIdx], 2);
            } else if (area.equals("T")) {
                scores[currentIdx] = (int) Math.pow(scores[currentIdx], 3);
            }
            String option = matcher.group(3);
            if (option.equals("*")) {
                scores[currentIdx - 1] *= 2;
                scores[currentIdx] *= 2;
            } else if (option.equals("#")) {
                scores[currentIdx] *= -1;
            }
            currentIdx++;
        }
        for (int i = 1; i <= 3; i++) {
            sum += scores[i];
        }
        return sum;
    }
}