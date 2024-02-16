import java.util.*
import kotlin.collections.ArrayDeque

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()

    val parentToChild = hashMapOf<Int, MutableList<Int>>()
    repeat(N) {
        parentToChild[it] = mutableListOf()
    }
    st = StringTokenizer(br.readLine())
    repeat(N) {
        val parent = st.nextToken().toInt()
        val child = it
        if (parent != -1) {
            parentToChild[parent]!!.add(child)
        }
    }

    val removeNode = br.readLine().toInt()
    val dq = ArrayDeque<Int>()
    dq.add(removeNode)

    while (dq.isNotEmpty()) {
        val node = dq.removeFirst()
        val nextNodes = parentToChild[node]
        parentToChild.remove(node)
        nextNodes?.forEach {
            if (parentToChild.containsKey(it)) dq.addLast(it)
        }
    }

    var answer = 0
    parentToChild.forEach { (_, u) ->
        if (u.contains(removeNode)) u.remove(removeNode)
        if (u.isEmpty()) answer++
    }

    bw.append("$answer")

    bw.flush()
    bw.close()
}


