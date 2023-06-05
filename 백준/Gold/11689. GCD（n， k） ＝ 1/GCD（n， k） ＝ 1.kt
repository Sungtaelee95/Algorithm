import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.sqrt


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    var N = st.nextToken().toLong()
    var result = N

    for(i in 2 .. Math.sqrt(N.toDouble()).toInt()){

        if(N % i == 0L){
            result = result - (result/i)
            while(N%i == 0L){
                N /= i
            }
        }

    }
    if(N > 1){
        result = result - result/N
    }
    println(result)

    //bw.flush()
    //bw.close()
}


