fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()
    var N = br.readLine().toCharArray()

    for (i in 0 until N.size) {
        if (N[i] == '.' || N[i] == 'A' || N[i] == 'B' ) continue
        if (i+3 < N.size && N[i] == 'X' && N[i+1] == 'X' && N[i+2] == 'X' && N[i+3] == 'X') {
            N[i] = 'A'
            N[i+1] = 'A'
            N[i+2] = 'A'
            N[i+3] = 'A'
        }
        if (i+1 < N.size && N[i] == 'X' && N[i+1] == 'X') {
            N[i] = 'B'
            N[i+1] = 'B'
        }
    }

    if (N.contains('X')) bw.append("-1") else bw.append(N.concatToString())

    bw.flush()
    bw.close()
}

