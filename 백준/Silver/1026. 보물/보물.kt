import java.io.*
import java.util.*

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val S = st.nextToken().toInt()

    //val visit = BooleanArray(S)

    st = StringTokenizer(br.readLine())
    var Aarr = IntArray(S){st.nextToken().toInt()}.sorted()
    st = StringTokenizer(br.readLine())
    var Barr = IntArray(S){st.nextToken().toInt()}.sortedDescending()

    var sum = 0
    
    for(i in 0 .. Aarr.size-1){
        sum += Aarr[i]*Barr[i]
    }

    bw.append("$sum")

    bw.flush()
    bw.close()
}