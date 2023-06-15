import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque
import java.util.StringTokenizer
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()

    val dq = ArrayDeque<Int>()

    repeat(N){
        st = StringTokenizer(br.readLine())

        val commend = st.nextToken().toString()

        when(commend){
            "push" ->{
                dq.add(st.nextToken().toInt())
            }
            "pop" ->{
                if(!dq.isEmpty()){
                    bw.appendLine(dq.poll().toString())
                } else {
                    bw.appendLine("-1")
                }
            }
            "size" ->{
                bw.appendLine("${dq.size}")
            }
            "empty" ->{
                if(dq.isEmpty()){
                    bw.appendLine("1")
                } else {
                    bw.appendLine("0")
                }
            }
            "front" ->{
                if(!dq.isEmpty()){
                    bw.appendLine("${dq.peek()}")
                } else {
                    bw.appendLine("-1")
                }
            }
            "back" ->{
                if(!dq.isEmpty()){
                    bw.appendLine("${dq.peekLast()}")
                } else {
                    bw.appendLine("-1")
                }
            }
        }

    }


    bw.flush()
    bw.close()
}