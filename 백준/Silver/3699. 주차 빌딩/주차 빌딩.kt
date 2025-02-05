import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val t = br.readLine().toInt() 

    repeat(t) {
        val (h, l) = br.readLine().split(" ").map { it.toInt() }
        val parkingBuilding = Array(h) { br.readLine().split(" ").map { it.toInt() }.toIntArray() } 
        val tempFloor = IntArray(l) 

        var curNum = 1 
        var result = 0 

        while (true) {
            var isFound = false
            for (i in 0 until h) {
                for (j in 0 until l) { 
                    if (curNum == parkingBuilding[i][j]) {
                        result += i * 2 * 10
                        isFound = true

                        if (j <= l / 2) {
                            result += j * 5
                            for (k in 0 until l) {
                                tempFloor[(k - j + l) % l] = parkingBuilding[i][k]
                            }
                        } else {
                            result += (l - j) * 5
                            for (k in 0 until l) {
                                tempFloor[(k + (l - j)) % l] = parkingBuilding[i][k]
                            }
                        }
                        for (k in 0 until l) {
                            parkingBuilding[i][k] = tempFloor[k]
                        }
                        break
                    }
                }
                if (isFound) break
            }
            if (!isFound) break
            curNum++
        }
        bw.appendLine("$result")
    }

    bw.flush()
    bw.close()
}