import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val N = br.readLine().toInt()

    var result = 0

    for(i in 1 .. N){
        var con = i
        i.toString().forEach {
            con += it - '0'
        }
        if(con == N) {
            result = i
            break
        }
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}

