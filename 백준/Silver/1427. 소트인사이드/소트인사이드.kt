import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.max
import java.util.*

data class info(val idx: Int, val value: Int)

fun main() {
//    val s = System.currentTimeMillis()
    var br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    //var st = StringTokenizer(br.readLine())
    //val N = st.nextToken().toInt() // 자료의 개수
    //var sb = StringBuilder()

    var arr = br.readLine().toCharArray()
    Arrays.sort(arr)
    arr.reversed().forEach{
        bw.append(it)
    }
    bw.flush()
    bw.close()

//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)

}

