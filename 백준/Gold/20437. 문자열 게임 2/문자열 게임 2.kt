import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.HashMap


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())

    val t = st.nextToken().toInt()
    repeat(t) {
        val w = br.readLine()
        val k = br.readLine().toInt()

        var resultOne = Int.MAX_VALUE
        var resultTwo = -1

        val tempMap = HashMap<Char, MutableList<Int>>()

        for (i in w.indices) {
            val str = w[i]
            if (tempMap.containsKey(str)) {
                tempMap[str]!!.add(i)
            } else {
                tempMap[str] = mutableListOf(i)
            }
        }

        tempMap.forEach {
            if (it.value.size >= k) {
                for (j in 0 .. it.value.size - k) {
                    resultOne = minOf(resultOne, it.value[j + k -1] - it.value[j] + 1)
                    resultTwo = maxOf(resultTwo, it.value[j + k - 1] - it.value[j] + 1)
                }
            }
        }

        if (resultOne == Int.MAX_VALUE) {
            bw.appendLine("-1")
        } else {
            bw.appendLine("$resultOne $resultTwo")
        }
    }

    bw.flush()
    bw.close()
}
