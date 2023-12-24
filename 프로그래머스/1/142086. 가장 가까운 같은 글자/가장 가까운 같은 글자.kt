class Solution {
    fun solution(s: String): IntArray {
        var answer: IntArray = intArrayOf()
        var lastIndices: IntArray = IntArray(26) { -1 }
        s.forEachIndexed { index, ch ->
            answer += if (lastIndices[ch - 'a'] == -1) -1 else index - lastIndices[ch - 'a']
            lastIndices[ch - 'a'] = index
        }
        return answer
    }
}