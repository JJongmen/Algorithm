class Solution {
    private val possibleWords = setOf("aya", "ye", "woo", "ma")
    
    fun solution(babbling: Array<String>): Int =
        babbling.count { canSpeak(it) }
        
    fun canSpeak(word: String): Boolean {
        var idx = 0
        var lastWord = ""
        while (idx < word.length) {
            val useWord = possibleWords.firstOrNull {
                word.startsWith(it, idx) && lastWord != it
            } ?: return false
            lastWord = useWord
            idx += useWord.length
        }
        return true
    }
}