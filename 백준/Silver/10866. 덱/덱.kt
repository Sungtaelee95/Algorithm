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
            "push_front" ->{
                dq.addFirst(st.nextToken().toInt())
            }

            "push_back" ->{
                dq.add(st.nextToken().toInt())
            }

            "pop_front" ->{
                if(!dq.isEmpty()){
                    bw.appendLine("${dq.poll()}")
                } else {
                    bw.appendLine("-1")
                }
            }

            "pop_back" ->{
                if(!dq.isEmpty()){
                    bw.appendLine("${dq.pollLast()}")
                } else {
                    bw.appendLine("-1")
                }
            }

            "size" -> bw.appendLine("${dq.size}")

            "empty" ->{
                if(dq.isEmpty()){
                    bw.appendLine("1")
                } else {
                    bw.appendLine("0")
                }
            }

            "front" ->{
                if(!dq.isEmpty()){
                    bw.appendLine("${dq.peekFirst()}")
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