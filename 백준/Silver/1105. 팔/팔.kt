import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val st = StringTokenizer(br.readLine())

    val L = st.nextToken()
    val R = st.nextToken()

    if (L.length != R.length) bw.append("0")
    else {
        var answer = 0
        for (i in L.indices) {
            if (L[i] == R[i] && L[i] == '8') answer++
            if (L[i] != R[i]) break
        }
        bw.append("$answer")
    }

    bw.flush()
    bw.close()
}