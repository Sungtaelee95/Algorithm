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

    val parentByChild = HashMap<String, MutableList<String>>() // 자식 후보 리스트
    val parentBydegree = HashMap<String, Int>()

    val parentByChildReverse = HashMap<String, MutableList<String>>()
    val parentByDegreeReverse = HashMap<String, Int>()

    st = StringTokenizer(br.readLine())
    repeat(N){
        val human = st.nextToken()
        parentByChild[human] = mutableListOf()
        parentBydegree[human] = 0

        parentByChildReverse[human] = mutableListOf()
        parentByDegreeReverse[human] = 0
    }

    st = StringTokenizer(br.readLine())
    val M = st.nextToken().toInt()
    repeat(M){
        st = StringTokenizer(br.readLine())
        val child = st.nextToken()
        val parent = st.nextToken()
        parentByChild[parent]!!.add(child)
        parentBydegree[child] = parentBydegree[child]!! + 1

        parentByChildReverse[parent]!!.add(child)
        parentByDegreeReverse[child] = parentByDegreeReverse[child]!! + 1

    }

    val resultMap = HashMap<String, MutableList<String>>()

    var rootCount = 0
    val sb = StringBuilder()
    val dq = ArrayDeque<String>()

    parentBydegree.keys.sorted().forEach{
        if(parentBydegree[it] == 0){
            sb.append("$it ")
            dq.addLast(it)
            rootCount++
        }
        resultMap[it] = mutableListOf()
    }
    bw.appendLine("$rootCount")
    bw.appendLine(sb)
    sb.clear()

    while (!dq.isEmpty()){ // 정방향
        val parent = dq.pollFirst()
        parentByChild[parent]!!.sorted().forEach {
            parentBydegree[it] = parentBydegree[it]!! - 1
            if(parentBydegree[it]!! <= 0){
                resultMap[parent]!!.add(it)
                dq.addLast(it)
            }
        }
    }

    parentByDegreeReverse.keys.sortedDescending().forEach{
        if(parentByDegreeReverse[it] == 0){
            dq.addLast(it)
        }
    }
    while (!dq.isEmpty()){ // 역방향
        val parent = dq.pollFirst()
        parentByChildReverse[parent]!!.sortedDescending().forEach {
            parentByDegreeReverse[it] = parentByDegreeReverse[it]!! - 1
            if(parentByDegreeReverse[it]!! <= 0){
                resultMap[parent]!!.add(it)
                dq.addLast(it)
            }
        }
    }

    resultMap.keys.sorted().forEach {
        bw.append("$it ${resultMap[it]!!.distinct().size}")
        resultMap[it]!!.sorted().distinct().forEach { child ->
            bw.append(" $child")
        }
        bw.appendLine()
    }

    bw.flush()
    bw.close()

}
