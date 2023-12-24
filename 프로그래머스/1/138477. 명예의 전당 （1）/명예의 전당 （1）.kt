import java.util.PriorityQueue

class Solution {
    fun solution(k: Int, scores: IntArray): IntArray {
        var answer: IntArray = intArrayOf()
        val ranking = PriorityQueue<Int>()
        for (score in scores) {
            if (ranking.size < k) {
                ranking.offer(score)
            } else {
                if (ranking.peek() < score) {
                    ranking.poll()
                    ranking.offer(score)
                }
            }
            answer += ranking.peek() ?: 0
        }
        return answer
    }
}