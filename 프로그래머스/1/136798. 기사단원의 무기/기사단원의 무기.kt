import kotlin.math.*

class Solution {
    fun countDivisors(number: Int): Int {
        var count = 0
        val sqrtNumber = sqrt(number.toDouble()).toInt()
        for (num in 1..sqrtNumber) {
            if (number % num != 0) continue
            count += 2
        }
        if (sqrtNumber * sqrtNumber == number) count--
        return count
    }
    
    fun solution(number: Int, limit: Int, power: Int): Int {
        var answer: Int = 0
        for (num in 1..number) {
            val divisorCnt = countDivisors(num)
            if (divisorCnt <= limit) {
                answer += divisorCnt
            } else {
                answer += power
            }
        } 
        return answer
    }
}