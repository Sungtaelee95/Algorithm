import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val case = st.nextToken().toInt()

    repeat(case) {
        val map = TreeMap<Int, Int>()
        repeat(br.readLine().toInt()) {
            st = StringTokenizer(br.readLine())
            val commend = st.nextToken()
            val num = st.nextToken().toInt()
            when (commend) {
                "I" -> {
                    if (map[num] == null) map[num] = 1 else map[num] = map[num]!! + 1
                }

                "D" -> {
                    if (map.isNotEmpty()) {
                        if (num > 0) {
                            if (map.lastEntry().value > 0) {
                                map[map.lastKey()] = map[map.lastKey()]!! - 1
                            }
                            if (map[map.lastKey()]!! <= 0) map.pollLastEntry()
                        } else {
                            if (map.firstEntry().value > 0) {
                                map[map.firstKey()] = map[map.firstKey()]!! - 1
                            }
                            if (map[map.firstKey()]!! <= 0) map.pollFirstEntry()
                        }
                    }
                }
            }
        }
        if (map.isEmpty()) bw.appendLine("EMPTY") else bw.appendLine("${map.lastKey()} ${map.firstKey()}")
    }
    bw.flush()
    bw.close()
}

