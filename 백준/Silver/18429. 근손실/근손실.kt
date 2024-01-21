import java.util.*


lateinit var kits: IntArray
lateinit var uses: BooleanArray
var K = 0
var result = 0
fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var st = StringTokenizer(br.readLine())

    val N = st.nextToken().toInt()
    K = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    kits = IntArray(N) { st.nextToken().toInt() }
    uses = BooleanArray(N)

    for (number in kits.indices) {
        uses[number] = true
        dfs (number, 500-K)
        uses[number] = false
    }

    bw.append("$result")

    bw.flush()
    bw.close()
}
fun dfs(useNumber: Int, height: Int) {
    val nowHeight = kits[useNumber] + height
    if (nowHeight < 500) return
    if (uses.all { it }){
        result++
    }
    for (number in kits.indices) {
        if (!uses[number]) {
            uses[number] = true
            dfs (number, nowHeight-K)
            uses[number] = false
        }
    }
}

