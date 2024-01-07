import java.util.*

class Solution {
    fun solution(x: Int, y: Int, n: Int): Int {
        var answer: Int = 0
        val que: Queue<Int> = LinkedList()
        que.offer(y)
        while (!que.isEmpty()) {
            repeat(que.size) {
                val cur = que.poll()
                if (cur == x) return answer
                if (cur % 2 == 0 && cur / 2 >= x) que.offer(cur / 2)
                if (cur % 3 == 0 && cur / 3 >= x) que.offer(cur / 3)
                if (cur - n >= x) que.offer(cur - n)          
            }
            answer++
        }
        return -1
    }
}