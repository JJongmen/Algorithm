import java.util.*;

class Solution {
    private static class Record {
        int time;
        String carNumber;
        boolean in;

        public Record(String record) {
            String[] tokens = record.split(" ");
            this.time = Integer.parseInt(tokens[0].substring(0, 2)) * 60 + Integer.parseInt(tokens[0].substring(3));
            this.carNumber = tokens[1];
            if (tokens[2].equals("IN")) {
                this.in = true;
            } else {
                this.in = false;
            }
        }
    }

    public int[] solution(int[] fees, String[] records) {
        // 1. 자동차당 누적 주차 시간 구하기
        Map<String, Integer> totalParkTime = new TreeMap<>();

        for (String recordStr : records) {
            Record record = new Record(recordStr);
            int totalTime = totalParkTime.getOrDefault(record.carNumber, 0);
            // 입차
            if (record.in) {
                totalParkTime.put(record.carNumber, totalTime - record.time);
            } else {    // 출차
                totalParkTime.put(record.carNumber, totalTime + record.time);
            }
        }
        // 입차 후에 출차하지 않은 차량 23:59에 출차한 것으로 처리
        for (String carNumber : totalParkTime.keySet()) {
            int totalTime = totalParkTime.get(carNumber);
            if (totalTime <= 0) {
                totalParkTime.put(carNumber, totalTime + 1439);
            }
        }

        // 주차 요금 계산
        int[] answer = new int[totalParkTime.size()];
        int defaultTime = fees[0];
        int defaultFee = fees[1];
        double unitTime = fees[2];
        int unitFee = fees[3];
        int idx = 0;
        for (int totalTime : totalParkTime.values()) {
            int fee = defaultFee;
            if (totalTime > defaultTime) {
                fee += (int) (Math.ceil((totalTime - defaultTime) / unitTime)) * unitFee;
            }
            answer[idx++] = fee;
        }
        
        return answer;
    }
}