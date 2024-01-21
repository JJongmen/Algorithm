import java.util.*;
import java.util.stream.Collectors;

class Solution {
    
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        List<Data> datas = new ArrayList<>();
        for (int[] dataItem : data) {
            datas.add(new Data(dataItem));
        }
        
        return datas.stream()
            .filter(d -> d.isSatisfied(ext, val_ext))
            .sorted(Comparator.comparing(d -> d.getValue(sort_by)))
            .map(Data::toIntArray)
            .toArray(int[][]::new);
    }
}

class Data {
    int code;
    int date;
    int maximum;
    int remain;
    
    Data(int[] data) {
        code = data[0];
        date = data[1];
        maximum = data[2];
        remain = data[3];
    }
    
    boolean isSatisfied(String ext, int val_ext) {
        switch (ext) {
            case "code": return code < val_ext;
            case "date": return date < val_ext;
            case "maximum": return maximum < val_ext;
            case "remain": return remain < val_ext;
            default: throw new IllegalArgumentException();
        }
    }
    
    int getValue(String sort_by) {
        switch (sort_by) {
            case "code": return code;
            case "date": return date;
            case "maximum": return maximum;
            case "remain": return remain;
            default: throw new IllegalArgumentException();
        }
    }
    
    int[] toIntArray() {
        int[] arr = new int[4];
        arr[0] = code;
        arr[1] = date;
        arr[2] = maximum;
        arr[3] = remain;
        return arr;
    }
}
