import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs

fun main() {
//    val s = System.currentTimeMillis()
    var br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt() // 자료의 개수
    //var sb = StringBuilder()

    var arr = IntArray(N){0}
    repeat(N){
        st = StringTokenizer(br.readLine())
        arr[it] = st.nextToken().toInt()
    }
    arr.sorted().forEach{
        bw.appendLine(it.toString())
    }
    
    
    bw.flush()
    bw.close()

//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)

}


