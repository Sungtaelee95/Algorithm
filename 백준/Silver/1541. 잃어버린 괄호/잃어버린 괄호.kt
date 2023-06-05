import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    var N = st.nextToken().toString()

    var numList = ArrayList<String>()
    var temp = ""
    N.forEach {
        if(it == '+'){
            if(temp!=""){
                numList.add(temp)
                temp = ""
            }
            numList.add("+")
        }
        if(it == '-'){
            if(temp!=""){
                numList.add(temp)
                temp = ""
            }
            numList.add("-")
        }
        if(it in ('0' .. '9')){
            temp += it.toString()
        }
    }
    if(temp != "") numList.add(temp)

    var result = 0
    var check = true // true 일때 plus 값 증가
    for(i in 0 .. numList.size-1){
        if(numList[i] == "-") {
            check = false
            continue
        }
        if(numList[i] == "+") continue
        if(check) {
            result += numList[i].toInt()
        } else {
            result -= numList[i].toInt()
        }
    }
    println(result)

    //bw.flush()
    //bw.close()
}

