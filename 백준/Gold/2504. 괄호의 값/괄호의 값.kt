import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.math.min

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val input = br.readLine().toCharArray()

    var result = 0
    var temp = 1
    val dq = ArrayDeque<Char>()
    for (i in input.indices) {
        when (input[i]) {
            '(' -> {
                dq.addLast(input[i])
                temp *= 2
            }

            '[' -> {
                dq.addLast(input[i])
                temp *= 3
            }

            ')' -> {
                if (dq.isEmpty() || dq.last() != '(') {
                    result = 0
                    break
                } else if (input[i - 1] == '(') {
                    result += temp
                }
                dq.removeLast()
                temp /= 2
            }

            ']' -> {
                if (dq.isEmpty() || dq.last() != '[') {
                    result = 0
                    break
                } else if (input[i - 1] == '[') {
                    result += temp
                }
                dq.removeLast()
                temp /= 3
            }
        }
    }

    if (dq.isEmpty()) bw.append("$result") else bw.append("0")

    bw.flush()
    bw.close()
}