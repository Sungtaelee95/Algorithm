import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    val infos = ArrayDeque<Info>()
    st = StringTokenizer(br.readLine())
    repeat(N) {
        infos.addLast(Info(it + 1, st.nextToken().toInt()))
    }
    infos.sortWith(compareBy({it.count} ,{it.num}))
    while (infos.isNotEmpty()) {
        val now = infos.removeFirst()
        bw.append("${now.num} ")
        for (info in infos) {
            if (info.num < now.num && info.count > 0) info.count--
        }
        infos.sortWith(compareBy({it.count} ,{it.num}))
    }

    bw.flush()
    bw.close()
}

data class Info(val num: Int, var count: Int)

