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
    var N = st.nextToken().toInt()

    repeat(N){
        st = StringTokenizer(br.readLine())
        var a = st.nextToken().toInt()
        var b = st.nextToken().toInt()
        bw.appendLine((a*b/gcd(a,b)).toString())
    }

    bw.flush()
    bw.close()
}

fun gcd(a: Int, b:Int):Int{
    if(b == 0) {
        return a
    }else return gcd(b,(a%b))
}

