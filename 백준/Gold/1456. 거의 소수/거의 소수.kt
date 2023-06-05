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
    var M = st.nextToken().toLong()
    var arr = LongArray(10000001){0}

    for(i in 0 .. arr.size-1){
        arr[i] = i.toLong()
    }

    for(i in 2 ..Math.sqrt(arr.size.toDouble()).toInt()+1){
        if(arr[i] == 0L){
            continue
        }
        for(j in i+i .. arr.size-1 step i){
            arr[j] = 0L
        }
    }

    var count = 0
    for(i in 2 .. 10000000){
        if(arr[i] != 0L){
            var temp = arr[i]
            while(arr[i]<= M.toDouble() /temp.toDouble() ){
                if(arr[i].toDouble()  >= N.toDouble()/temp.toDouble() )count++
                temp *= arr[i]
            }
        }
    }
    println(count)


    //bw.flush()
    //bw.close()
}


