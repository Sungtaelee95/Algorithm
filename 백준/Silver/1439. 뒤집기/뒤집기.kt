import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var chk0 = 0
    var chk1 = 0
    val str = br.readLine().toString()
    str.forEachIndexed { index, c ->
        if(index > 0){
            if(str[index-1] == '0' && c == '1') chk1++
            if(str[index-1] == '1' && c == '0') chk0++
        } else {
            if(c == '0') chk0++
            else chk1++
        }
    }

    bw.append("${Math.min(chk0, chk1)}")

    bw.flush()
    bw.close()
}