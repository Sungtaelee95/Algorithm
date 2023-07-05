import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque
import java.util.LinkedList
import java.util.Stack
import java.util.StringTokenizer

data class Info(val high: Int,var count: Int)
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val dq = ArrayDeque<Info>()
    var count = 0L
    repeat(br.readLine().toInt()){
        val now= Info(br.readLine().toInt(),1)
        while(!dq.isEmpty()){
            if(now.high < dq.peekLast().high){
                count++
                break
            }
            if(now.high == dq.peekLast().high){
                now.count = dq.peekLast().count+1
            }
            count += dq.pollLast().count
        }
        dq.addLast(now)
    }

    bw.append("$count")

    bw.flush()
    bw.close()
}

