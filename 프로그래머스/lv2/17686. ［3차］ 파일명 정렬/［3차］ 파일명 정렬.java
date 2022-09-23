import java.util.*;
import java.util.regex.*;

class Solution {
    private static class File {
        String head;
        String number;
        String tail;

        public File(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
    }

    public String[] solution(String[] files) {
        Pattern pattern = Pattern.compile("(\\D+)([0-9]+)(.*)");
        List<File> fileList = new ArrayList<>();
        for (String file : files) {
            Matcher matcher = pattern.matcher(file);
            if (matcher.matches()) {
                fileList.add(new File(matcher.group(1), matcher.group(2), matcher.group(3)));
            }
        }
        fileList.sort((o1, o2) -> {
            if (o1.head.equalsIgnoreCase(o2.head)) {
                return Integer.parseInt(o1.number) - Integer.parseInt(o2.number);
            } else {
                return o1.head.toUpperCase().compareTo(o2.head.toUpperCase());
            }

        });
        String[] answer = new String[fileList.size()];
        for (int i = 0; i < fileList.size(); i++) {
            answer[i] = fileList.get(i).head + fileList.get(i).number + fileList.get(i).tail;
        }

        return answer;
    }
}