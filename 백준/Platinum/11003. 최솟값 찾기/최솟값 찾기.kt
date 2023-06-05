import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

data class Dcl(val idx: Int, val vle: Int)

fun main() {
//    val s = System.currentTimeMillis()
    var br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt()
    val check = st.nextToken().toInt()

    var dq = ArrayDeque<Dcl>()

    st = StringTokenizer(br.readLine())
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(N){
        var temp = Dcl(it,st.nextToken().toInt())
        while(!dq.isEmpty() && dq.peekLast().vle > temp.vle){
            dq.removeLast()
        }
        dq.addLast(temp)
        while(dq.peekFirst().idx <= it-check){
            dq.removeFirst()
        }
        bw.write("${dq.peekFirst().vle}")
        bw.write(" ")
    }
    bw.flush()
    bw.close()
//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)

}


