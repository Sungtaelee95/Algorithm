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
    var N = st.nextToken().toInt()
    var arr = IntArray(1111111){0}

    for(i in 0 .. arr.size-1){
        arr[i] = i
    }

    for(i in 2 ..Math.sqrt(arr.size.toDouble()).toInt()+1){
        if(arr[i] == 0){
            continue
        }
        for(j in i+i .. arr.size-1 step i){
            arr[j] = 0
        }
    }

    for(i in N .. arr.size-1){
        if(arr[i] == 0 || arr[i] == 1)continue
        if(arr[i].toString() == arr[i].toString().reversed()){
            println(arr[i])
            break
        }
    }


    //bw.flush()
    //bw.close()
}


