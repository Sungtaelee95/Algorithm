import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val l = st.nextToken().toInt()

    val holes = mutableListOf<Hole>()

    repeat(n) {
        st = StringTokenizer(br.readLine())
        val hole = Hole(st.nextToken().toInt(), st.nextToken().toInt())
        holes.add(hole)
    }

    // 시작점이 빠른 순 / 시작 점이 같다면 끝 점이 더 먼 곳으로
    holes.sortWith(kotlin.Comparator { o1, o2 ->
        if (o1.start != o2.start) o1.start.compareTo(o2.start) else o2.end.compareTo(
            o1.end
        )
    })

    var count = 0
    var lastIndex = 0
    holes.forEach {
        if (lastIndex < it.end) {
            if (lastIndex < it.start) lastIndex = it.start
            if ((it.end - lastIndex) % l == 0) {
                count += ((it.end - lastIndex) / l)
                lastIndex += l * ((it.end - lastIndex) / l)
            } else {
                count += ((it.end - lastIndex) / l) + 1
                lastIndex += l * (((it.end - lastIndex) / l) + 1)
            }
        }
    }

    bw.append("$count")

    bw.flush()
    bw.close()
}

data class Hole(val start: Int, val end: Int)
