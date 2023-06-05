import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main(){
    var br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    var arr = IntArray(st.nextToken().toInt()){i: Int ->  i+1}

    repeat(st.nextToken().toInt()){
        st = StringTokenizer(br.readLine())
        var one = st.nextToken().toInt()-1
        var two = st.nextToken().toInt()-1

        var temp = arr[one]
        arr[one] = arr[two]
        arr[two] = temp
    }

    var bw = BufferedWriter(OutputStreamWriter(System.out))

    arr.forEach {
        bw.append("$it ")
    }

    bw.flush()
    bw.close()

}