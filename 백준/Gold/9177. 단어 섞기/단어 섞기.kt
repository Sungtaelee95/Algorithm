import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque

lateinit var one: String
lateinit var two: String
lateinit var three: String

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()

    repeat(n) {
        st = StringTokenizer(br.readLine())
        one = st.nextToken()
        two = st.nextToken()
        three = st.nextToken()
        if (bfs()) {
            bw.appendLine("Data set ${it + 1}: yes")
        } else {
            bw.appendLine("Data set ${it + 1}: no")
        }
    }
    bw.flush()
    bw.close()
}

fun bfs(): Boolean {
    val visit = Array(one.length+1) { BooleanArray(two.length+1) }
    visit[0][0] = true
    val dq = ArrayDeque<Word>()
    dq.add(Word(0,0,0))

    while (dq.isNotEmpty()) {
        val now = dq.removeFirst()
        if (now.oneIdx < one.length && !visit[now.oneIdx+1][now.twoIdx] && one[now.oneIdx] == three[now.deep]) {
            dq.addLast(Word(now.oneIdx+1, now.twoIdx, now.deep+1))
            visit[now.oneIdx+1][now.twoIdx] = true
        }
        if (now.twoIdx < two.length && !visit[now.oneIdx][now.twoIdx+1] && two[now.twoIdx] == three[now.deep]) {
            dq.addLast(Word(now.oneIdx, now.twoIdx+1, now.deep+1))
            visit[now.oneIdx][now.twoIdx+1] = true
        }
    }

    return visit[one.length][two.length]
}

data class Word(val oneIdx: Int, val twoIdx: Int, val deep: Int)


