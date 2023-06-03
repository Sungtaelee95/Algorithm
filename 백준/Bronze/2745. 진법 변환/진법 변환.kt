import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var result = 0

    val N = st.nextToken().toCharArray()
    val B = st.nextToken().toInt()

    var num = 1

    for(i in N.size-1 downTo 0){
        
        if(N[i].hashCode() in 48 .. 57){
            result += (N[i].hashCode()-48) * num
            num *= B
        } else {
            result += (N[i].hashCode()-55) * num
            num *= B
        }

    }

    bw.append(result.toString())

    bw.flush()
    bw.close()

}