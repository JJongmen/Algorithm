import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val subSum = LongArray(1000001)
    val divisorSum = LongArray(1000001)
    for (i in 1..1000000) {
        for (j in 1..(1000000 / i)) {
            divisorSum[i * j] += i.toLong()
        }
        subSum[i] = subSum[i - 1] + divisorSum[i]
    }

    val N = readLine().toInt()

    val bw = System.`out`.bufferedWriter()
    repeat(N) {
        val n = readLine().toInt()
        bw.write("${subSum[n]}\n")
    }
    bw.close()
}