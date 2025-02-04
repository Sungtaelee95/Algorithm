import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val stList = mutableListOf<Student>()
    repeat(3) {
        val (p,y,s) = br.readLine().split(" ")
        stList.add(Student(p.toInt(), y.toInt() % 100, "${s[0]}"))
    }

    stList.sortedWith { o1, o2 -> o1.y.compareTo(o2.y) }.forEach {
        bw.append("${it.y}")
    }
    bw.appendLine()
    stList.sortedWith { o1, o2 -> o2.p.compareTo(o1.p) }.forEach {
        bw.append("${it.s}")
    }
    bw.flush()
    bw.close()

}

data class Student(val p: Int, val y: Int, val s: String)