import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    
    val folderPackage = hashMapOf<String, MutableList<String>>()
    val filePackage = hashMapOf<String, MutableList<String>>()
    
    repeat(n + m) {
        val (parent, child, kind) = br.readLine().split(" ")
        if (kind == "1") {
            if (folderPackage[parent].isNullOrEmpty()) {
                folderPackage[parent] = mutableListOf(child)
            } else {
                folderPackage[parent]!!.add(child)
            }
        } else {
            if (filePackage[parent].isNullOrEmpty()) {
                filePackage[parent] = mutableListOf(child)
            } else {
                filePackage[parent]!!.add(child)
            }
        }
    }

    repeat(br.readLine().toInt()) {
        val files = mutableListOf<String>()
        val folder = ("/" + br.readLine()).split("/").last()
        val dq = ArrayDeque<String>()
        dq.add(folder)
        while (dq.isNotEmpty()) {
            val now = dq.removeFirst()
            filePackage[now]?.let {
                files.addAll(it)
            }
            folderPackage[now]?.let {
                it.forEach {
                    dq.addLast(it)
                }
            }
        }
        bw.appendLine("${files.distinct().size} ${files.size}")
    }

    bw.flush()
    bw.close()
}