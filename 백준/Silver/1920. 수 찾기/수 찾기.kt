import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.max
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    var N = st.nextToken().toInt()
    var arr = HashSet<Int>()
    st = StringTokenizer(br.readLine())
    repeat(N){
        arr.add(st.nextToken().toInt())
    }

    st = StringTokenizer(br.readLine())
    val M = st.nextToken().toInt()
    st = StringTokenizer(br.readLine())
    for(i in 1 .. M){
        val find = st.nextToken().toInt() // 찾아야 할 값
        if(arr.contains(find)){
            bw.appendLine("1")
            continue
        }
        bw.appendLine("0")
    }
    bw.flush()
    bw.close()
}

