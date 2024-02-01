class Solution {
    fun solution(arr: IntArray, query: IntArray): IntArray {
        var answer = arr.toList()
        query.withIndex().forEach { (index, element) ->
            if (index % 2 == 0) answer = answer.slice(0..element)
            else answer = answer.slice(element until answer.size)
        }
        return answer.toIntArray()
    }
}