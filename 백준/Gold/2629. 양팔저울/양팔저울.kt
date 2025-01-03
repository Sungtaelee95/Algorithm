import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val n = br.readLine().toInt()
    val chus = br.readLine().split(" ").map { it.toInt() }
    val dp = mutableSetOf<Int>()
    dp.add(chus[0])
    for (i in 1 until n) {
        val chu = chus[i]
        val temp = mutableSetOf<Int>()
        temp.add(chu)
        for (j in dp) {
            temp.add(abs(chu - j))
            temp.add(chu + j)
        }
        dp.addAll(temp)
    }

    val c = br.readLine().toInt()
    val nums = br.readLine().split(" ").map { it.toInt() }

    for (num in nums) {
        if (dp.contains(num)) bw.append("Y ") else bw.append("N ")
    }

    bw.flush()
    bw.close()
}