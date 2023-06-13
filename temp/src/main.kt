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

    st = StringTokenizer(br.readLine())

    var dq = ArrayDeque<Pair<Int,Int>>()

    for(i in 1 .. N){

        val new = Pair(st.nextToken().toInt(), i)

        while(!dq.isEmpty()){
            if(dq.peekLast().first <= new.first) {
                dq.pollLast()
            } else {
                bw.append("${dq.peekLast().second} ")
                dq.add(new)
                break
            }
        }

        if(dq.isEmpty()){
            dq.add(new)
            bw.append("0 ")
        }

    }


    bw.flush()
    bw.close()
}