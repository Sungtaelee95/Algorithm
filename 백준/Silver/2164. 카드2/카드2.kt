import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
//    val s = System.currentTimeMillis()
    var br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt() // 자료의 개수
    //var sb = StringBuilder()
    var dq = ArrayDeque<Int>()

    repeat(N){
        dq.addLast(it+1)
    }

    while(dq.size != 1){
        dq.removeFirst()
        dq.addLast(dq.pollFirst())
    }
    bw.append("${dq.peekFirst()}")
    bw.flush()
    bw.close()

//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)

}


