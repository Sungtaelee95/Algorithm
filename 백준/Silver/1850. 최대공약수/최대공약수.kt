import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.sqrt


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    //var N = st.nextToken().toInt()

    var a = st.nextToken().toLong()
    var b = st.nextToken().toLong()

    var result = gcd(a,b).toInt()

    repeat(result){
        bw.append("1")
    }

    bw.flush()
    bw.close()
}

fun gcd(a: Long, b:Long):Long{
    if(b == 0L) {
        return a
    }else return gcd(b,(a%b))
}

