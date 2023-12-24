import java.util.PriorityQueue

class Solution {
    fun solution(k: Int, scores: IntArray): IntArray {
        val ranking = PriorityQueue<Int>()
        return scores.map { score -> 
            ranking.offer(score)
            if (ranking.size > k) {
                ranking.poll()
            }
            ranking.peek() ?: 0   
        }.toIntArray()
    }
}