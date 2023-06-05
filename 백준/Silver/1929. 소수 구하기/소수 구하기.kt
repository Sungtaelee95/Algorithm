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
    val start = st.nextToken().toInt()
    val end = st.nextToken().toInt()
    var arr = BooleanArray(end+1){true}

    for(num in start .. end){
        if(num == 1) continue
        for(i in 2 ..sqrt(num.toDouble()).toInt()+1){
            if(num % i == 0){
                arr[num] = false
                break
            }
        }
    }
    arr[0] = false
    arr[1] = false
    arr[2] = true

    for(i in start .. arr.size-1){
        if(arr[i]) bw.appendLine(i.toString())
    }

    bw.flush()
    bw.close()
}

