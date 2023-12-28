class Solution {
    fun solution(surveys: Array<String>, choices: IntArray): String {
        var answer: String = ""
        val typeMap = mutableMapOf<Char, Int>(
            'R' to 0, 'T' to 0,
            'C' to 0, 'F' to 0,
            'J' to 0, 'M' to 0,
            'A' to 0, 'N' to 0,
        )
        surveys.toList().zip(choices.toList()).forEach { (survey, choice) ->
            if (choice in 1..3) {
                typeMap.put(survey[0], typeMap[survey[0]]!! + 4 - choice)
            } else if (choice in 5..7) {
                typeMap.put(survey[1], typeMap[survey[1]]!! + choice - 4)
            }
        }
        return listOf('R' to 'T', 'C' to 'F', 'J' to 'M', 'A' to 'N').map {
            if (typeMap[it.first]!! >= typeMap[it.second]!!) it.first else it.second
        }.joinToString("")
    }
}