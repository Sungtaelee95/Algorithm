import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

lateinit var visted : BooleanArray
var loadMap = mutableMapOf<Int,IntArray>()


fun main() {
    //val s = System.currentTimeMillis()
    val br = BufferedReader(InputStreamReader(System.`in`))
    //var bw = BufferedWriter(OutputStreamWriter(System.out))
    var st = StringTokenizer(br.readLine())
    val N = st.nextToken().toInt() // 노드의 개수
    val M = st.nextToken().toInt() // 에지의 개수
    repeat(M){
        st = StringTokenizer(br.readLine())
        val key = st.nextToken().toInt()
        val value = st.nextToken().toInt()
        if(loadMap.containsKey(key)){
            loadMap[key] = loadMap[key]!! + intArrayOf(value)
        }else{
            loadMap[key] = intArrayOf(value)
        }
        if(loadMap.containsKey(value)){
            loadMap[value] = loadMap[value]!! + intArrayOf(key)
        }else{
            loadMap[value] = intArrayOf(key)
        }
    }

    visted = BooleanArray(N+1){true} // 방문 여부

    var result = 0

    for(i in 1 .. N){
        if(visted[i]){
            check(i)
            result++
        }
    }
    println(result)
    //var sb = StringBuilder()
//    bw.flush()
//    bw.close()
//
//   val e = System.currentTimeMillis()
//   println((e-s)/1000.0)

}

fun check(viste: Int){
    visted[viste] = false
    loadMap[viste]?: return
    
    for (i in loadMap[viste]!!){
        if(visted[i]) {
            check(i)
        }
    }
    
}

