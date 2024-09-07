import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.math.BigInteger


var result = BigInteger("1")
val bw = BufferedWriter(OutputStreamWriter(System.out))
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val board = br.readLine().toInt()
    result = result.shiftLeft(board)
    result = result.minus(BigInteger("1"))

    if (board <= 20) {
        bw.appendLine("$result")
        hanoi(board, 1, 2,3)
    } else {
        bw.append("$result")
    }

    bw.flush()
    bw.close()
}

fun hanoi(n: Int, start: Int, mid: Int, end: Int) {
    if (n == 0) return
    hanoi(n - 1, start, end, mid)
    bw.appendLine("$start $end")
    hanoi(n - 1, mid, start, end)
}