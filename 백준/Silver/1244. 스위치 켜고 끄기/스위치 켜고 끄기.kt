import java.io.BufferedReader // ktlint-disable filename
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    val arr = BooleanArray(N + 1)
    repeat(N) {
        arr[it + 1] = st.nextToken().toInt() == 1
    }
    val studentCount = br.readLine().toInt()
    repeat(studentCount) {
        st = StringTokenizer(br.readLine())
        val std = st.nextToken().toInt()
        val num = st.nextToken().toInt()
        when (std) {
            1 -> {
                for (i in 1 until arr.size) {
                    if (i * num >= arr.size) break
                    arr[i * num] = !arr[i * num]
                }
            }

            2 -> {
                arr[num] = !arr[num]
                for (i in 1 until arr.size) {
                    if (num + i >= arr.size || num - i <= 0) break
                    if (arr[num + i] == arr[num - i]) {
                        arr[num + i] = !arr[num + i]
                        arr[num - i] = !arr[num - i]
                        continue
                    }
                    break
                }
            }

            else -> return
        }
    }
    for (i in 1 until arr.size) {
        if (arr[i]) {
            bw.append("1 ")
        } else {
            bw.append("0 ")
        }
        if (i % 20 == 0) bw.appendLine()
    }

    bw.flush()
    bw.close()
}
