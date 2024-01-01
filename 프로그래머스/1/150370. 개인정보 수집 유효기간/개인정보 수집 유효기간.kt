import java.time.LocalDate

class Solution {
    private val termMap: HashMap<String, Long> = hashMapOf()
    
    fun toLocalDate(day: String): LocalDate {
        val (year, month, dayOfMonth) = day.split(".")
        return LocalDate.of(year.toInt(), month.toInt(), dayOfMonth.toInt())
    }
    
    fun shouldBreak(today: String, privacy: String): Boolean {
        val (date, type) = privacy.split(" ")
        return toLocalDate(today) >= toLocalDate(date).plusMonths(termMap[type]!!)
    }
    
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        terms.forEach {
            val (a, b) = it.split(" ")
            termMap[a] = b.toLong()
        }
        
        return privacies.withIndex().filter { (idx, privacy) -> 
            shouldBreak(today, privacy) 
        }.map { (idx, _) -> idx + 1 }.toIntArray()
    }
}