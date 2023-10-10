import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.collections.HashMap


fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())

    val A = st.nextToken().toInt()
    val B = st.nextToken().toInt()

    val AMap = HashMap<Int,Int>()
    st = StringTokenizer(br.readLine())
    repeat(A){
        AMap[st.nextToken().toInt()] = 1
    }

    val BMap = HashMap<Int,Int>()
    st = StringTokenizer(br.readLine())
    repeat(B){
        BMap[st.nextToken().toInt()] = 1
    }

    BMap.forEach{
        if(AMap.containsKey(it.key)){
            AMap.remove(it.key)
        }
    }

    bw.append("${AMap.size}")
    if(AMap.isNotEmpty()){
        bw.appendLine()
        AMap.keys.sorted().forEach{
            bw.append("$it ")
        }
    }

    bw.flush()
    bw.close()
}