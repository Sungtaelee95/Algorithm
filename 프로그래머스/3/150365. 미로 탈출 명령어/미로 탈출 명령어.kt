class Solution {
    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        val commend = "dlru"
        val nextRow = intArrayOf(1,0,0,-1)
        val nextCol = intArrayOf(0,-1,1,0)
        val start = Node(x-1, y-1, "")
        val end = Node(r-1,c-1, "")
        var result = "impossible"
        
        val dq = ArrayDeque<Node>()
        dq.addLast(start)
        
        while(dq.isNotEmpty()) {
            val now = dq.removeFirst()
            if (now.commend.length == k && now.row == r-1 && now.col == c-1) {
                result = now.commend
                break
            }
            
            for (i in 0 .. 3) {
                val nr = now.row + nextRow[i]
                val nc = now.col + nextCol[i]
                val sb = StringBuilder(now.commend)
                sb.append(commend[i].toString())
                
                if (nr < 0 || nc < 0 || nr >= n || nc >= m) continue
                val targetDist = Math.abs(nr - end.row) + Math.abs(nc - end.col) // 목표까지의 거리
                val count = k - sb.length // 남은 카운트
                if (count == 0) {
                    if (targetDist == 0) {
                        dq.addLast(Node(nr,nc,sb.toString()))
                        break
                    }
                    continue
                }
                if (targetDist > count) continue // 카운트 보다 거리가 더 멀면 패스
                if (count >= targetDist) {
                    if (targetDist == 0) {
                        if (count % 2 == 0) {
                            dq.addLast(Node(nr,nc,sb.toString()))
                            break
                        }
                        continue
                    }
                    dq.addLast(Node(nr,nc,sb.toString()))
                    break
                }
            }
            
        }
        return result
    }
}
data class Node(val row: Int, val col: Int, val commend: String)