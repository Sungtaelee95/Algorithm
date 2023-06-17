import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayDeque
import java.util.LinkedList
import java.util.StringTokenizer
fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    //var st = StringTokenizer(br.readLine())

    val N = br.readLine().toInt()

    repeat(N){
        val commend = br.readLine()

        var dqSize = br.readLine().toInt()

        var dq = ArrayDeque(br.readLine().replace("[","").replace("]","").split(","))


        var chk = true
        var start = true // t = 앞에서, f = 뒤에서
        for(i in commend){
            when(i){
                'R' ->{
                    start = !start
                }
                'D' ->{
                    if(dq.isEmpty() || dq.peekLast() == "") {
                        chk = false
                        break
                    }
                    if(start){
                        dq.pollFirst()
                    } else {
                        dq.pollLast()
                    }

                }
            }
        }

        if(chk){

            if(start){
                bw.append("[")
                while(!dq.isEmpty()){
                    bw.append("${dq.pollFirst()}")
                    if(!dq.isEmpty()) bw.append(",")
                }
                bw.append("]")
            } else {
                bw.append("[")
                while(!dq.isEmpty()){
                    bw.append("${dq.pollLast()}")
                    if(!dq.isEmpty()) bw.append(",")
                }
                bw.append("]")
            }
            bw.appendLine()

        } else{
            bw.appendLine("error")
        }
    }

    bw.flush()
    bw.close()
}

