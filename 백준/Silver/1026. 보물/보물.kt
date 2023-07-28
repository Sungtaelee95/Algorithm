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
    var Barr = IntArray(S){st.nextToken().toInt()}.sorted()

    var sum = 0

    // A,B 오름차순
    for(i in 0 .. Aarr.size-1){
        sum += Aarr[i]*Barr[i]
    }

    // A: 오름, B: 내림
    Barr = Barr.reversed()
    var sum2 = 0
    for(i in 0 .. Aarr.size-1){
        sum2 += Aarr[i]*Barr[i]
    }
    sum = Math.min(sum, sum2)

    // A: 내림, B: 내림
    Aarr = Aarr.reversed()
    var sum3 = 0
    for(i in 0 .. Aarr.size-1){
        sum3 += Aarr[i]*Barr[i]
    }
    sum = Math.min(sum, sum3)

    // A: 내림, B: 올림
    Barr = Barr.reversed()
    var sum4 = 0
    for(i in 0 .. Aarr.size-1){
        sum4 += Aarr[i]*Barr[i]
    }
    sum = Math.min(sum, sum4)

    bw.append("$sum")

    bw.flush()
    bw.close()
}