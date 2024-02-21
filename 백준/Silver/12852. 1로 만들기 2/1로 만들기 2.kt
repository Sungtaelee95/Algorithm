import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())
    val X = st.nextToken().toInt()
    val dq = ArrayDeque<Info>()
    val visit = BooleanArray(X+1)
    visit[X] = true
    dq.add(Info(X, mutableListOf(X)))
    while(dq.isNotEmpty()) {
        val now = dq.removeFirst()
        if (now.num == 1) {
            bw.append("${now.numbers.size-1}\n${now.numbers.joinToString(" ")}")
            break
        }
        if (now.num % 3 == 0 && !visit[now.num/3]) {
            visit[now.num/3] = true
            val temp = mutableListOf<Int>()
            temp.addAll(now.numbers)
            temp.add(now.num/3)
            dq.addLast(Info(now.num/3, temp))
        }
        if (now.num % 2 == 0 && !visit[now.num/2]) {
            visit[now.num/2] = true
            val temp = mutableListOf<Int>()
            temp.addAll(now.numbers)
            temp.add(now.num/2)
            dq.addLast(Info(now.num/2, temp))
        }
        if (now.num -1 > 0 && !visit[now.num-1]) {
            visit[now.num-1] = true
            val temp = mutableListOf<Int>()
            temp.addAll(now.numbers)
            temp.add(now.num-1)
            dq.addLast(Info(now.num-1, temp))
        }
    }

    bw.flush()
    bw.close()
}

data class Info(val num: Int, val numbers: MutableList<Int>)
