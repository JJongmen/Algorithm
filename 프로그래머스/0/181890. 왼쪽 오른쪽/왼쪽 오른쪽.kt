class Solution {
    fun solution(str_list: Array<String>): List<String> {
        val index = str_list.indexOfFirst { it == "l" || it == "r" }
        if (index == -1) return listOf()
        return if (str_list[index] == "l") str_list.slice(0 until index) else str_list.slice(index + 1 until str_list.size)
    }
}