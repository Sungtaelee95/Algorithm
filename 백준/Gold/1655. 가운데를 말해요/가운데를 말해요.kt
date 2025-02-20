import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.min

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val front = PriorityQueue<Int> { o1, o2 -> o2.compareTo(o1) }
    val back = PriorityQueue<Int> { o1, o2 -> o1.compareTo(o2) }
    for(i in 1 .. n) {
        st = StringTokenizer(br.readLine())
        val num = st.nextToken().toInt()
        if (front.isEmpty()) {
            front.add(num)
            bw.appendLine("$num")
            continue
        }
        if (back.isEmpty()) {
            if (front.peek() <= num) {
                back.add(num)
                bw.appendLine("${front.peek()}")
                continue
            } else {
                back.add(front.poll())
                front.add(num)
                bw.appendLine("${front.peek()}")
                continue
            }
        }

        val min = front.peek()
        val max = back.peek()

        if (num < min) {
            if (front.size > back.size) {
                back.add(front.poll())
                front.add(num)
            } else {
                front.add(num)
            }
        } else if (num in min .. max) {
            if (front.size > back.size) {
                back.add(num)
            } else {
                front.add(num)
            }
        } else {
            if (front.size <= back.size) {
                front.add(back.poll())
                back.add(num)
            } else {
                back.add(num)
            }
        }
        bw.appendLine("${front.peek()}")
    }
    bw.flush()
    bw.close()
}