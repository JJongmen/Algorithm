class Solution {
    fun solution(s: String, skip: String, index: Int): String {
        var answer = ""
        s.forEach { ch ->
            var cnt = 0
            var tmp = ch
            while (cnt < index) {
                while (true) {
                    if (tmp++ == 'z') tmp = 'a'
                    if (!skip.contains(tmp)) break
                }
                cnt++
            }
            answer += tmp
        }
        return answer
    }
}