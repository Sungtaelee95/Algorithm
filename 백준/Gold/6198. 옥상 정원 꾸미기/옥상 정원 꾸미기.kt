import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque
import java.util.StringTokenizer
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    //var st = StringTokenizer(br.readLine())

    val N = br.readLine().toInt()

    var dq = ArrayDeque<Info>() // first = 벤치마킹 가능한 건물 수, second = 현재 건물의 높이
    var count = 0L

    repeat(N){
        var now = Info(br.readLine().toInt(), 0)

        while(!dq.isEmpty()){
            if(dq.peekLast().height <= now.height){
                val del = dq.pollLast()

                now.count += del.count
                if(dq.isEmpty()) break

            } else {

                count += dq.size

                dq.addLast(now)
                break
            }
        }

        if(dq.isEmpty()) dq.addLast(now)

    }

    while(!dq.isEmpty()){
        count += dq.poll().count
    }

    bw.append("$count")

    bw.flush()
    bw.close()
}

data class Info(val height: Int, var count: Int)