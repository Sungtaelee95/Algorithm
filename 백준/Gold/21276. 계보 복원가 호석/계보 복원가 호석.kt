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

    val N = st.nextToken().toInt()

    val humanMap = HashMap<String, MutableList<String>>() // 동네 사람들
    val degree = HashMap<String, Int>()

    st = StringTokenizer(br.readLine())
    repeat(N){
        val human = st.nextToken()
        humanMap[human] = mutableListOf()
        degree[human] = 0
    }

    st = StringTokenizer(br.readLine())
    val M = st.nextToken().toInt()
    repeat(M){
        st = StringTokenizer(br.readLine())
        val child = st.nextToken()
        val parent = st.nextToken()
        humanMap[parent]!!.add(child)
        degree[child] = degree[child]!! + 1
    }

    var rootCount = 0
    val sb = StringBuilder()
    val dq = ArrayDeque<String>()

    val resultMap = HashMap<String, MutableList<String>>()

    degree.forEach{
        if(it.value == 0){
            sb.append("${it.key} ")
            dq.addLast(it.key)
            rootCount++
        }
        resultMap[it.key] = mutableListOf()
    }
    bw.appendLine("$rootCount")
    bw.appendLine(sb.toString())
    sb.clear()

    while (!dq.isEmpty()){
        val parent = dq.poll()
        humanMap[parent]!!.forEach {
            degree[it] = degree[it]!! - 1
            if(degree[it] == 0){
                resultMap[parent]!!.add(it)
                dq.addLast(it)
            }
        }
    }

    resultMap.keys.sorted().forEach {
        bw.append("$it ${resultMap[it]!!.size}")
        resultMap[it]!!.sorted().forEach { child ->
            bw.append(" $child")
        }
        bw.appendLine()
    }

    bw.flush()
    bw.close()

}
