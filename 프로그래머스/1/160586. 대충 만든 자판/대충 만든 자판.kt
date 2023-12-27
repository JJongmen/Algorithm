class Solution {
    fun solution(keymaps: Array<String>, targets: Array<String>): List<Int> {
        val minCounts = IntArray(26) { INF }
        keymaps.forEach { keymap ->
            keymap.forEachIndexed { index, key -> 
                minCounts[key - 'A'] = minOf(minCounts[key - 'A'], index + 1)
            }
        }
        return targets.map { target ->
            target.map { minCounts[it - 'A'] }.sum().let { if (it >= INF) -1 else it }
        }
    }
    
    companion object {
        private const val INF = 10005
    }
}