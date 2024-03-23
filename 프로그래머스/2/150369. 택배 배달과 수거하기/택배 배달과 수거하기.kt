// 아이디어 (그리디)
// 가장 먼집 부터 배달을 진행
// 돌아올때 먼집 부터 회수
// 트럭의 초기 위치 공장, 마지막 위치 공장

class Solution {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer: Long = 0
        var endPoint = -1
        
        for (i in n-1 downTo 0) { // 끝 집부터 배달 혹은 수거해야하는게 있는지 확인
            if (deliveries[i] != 0 || pickups[i] != 0){
                endPoint = i
                break
            } 
        }
        
        while(true) {
            var count = cap
            var tempEndPoint = -1
            
            if (endPoint == -1) break // 배달 및 수거가 없으면 중단.
            answer += (endPoint.toLong()+1L)*2L // 인덱스의 차이와 왕복이므로
            
            for (i in endPoint downTo 0) { // 배달
               when {
                   count >= deliveries[i] -> {
                       count -= deliveries[i]
                       deliveries[i] = 0
                   }
                   count < deliveries[i] -> {
                       deliveries[i] -= count
                       count = 0
                       tempEndPoint = maxOf(tempEndPoint, i)
                       break
                   }
               }
            }
            count = cap
            for (i in endPoint downTo 0) { // 수거
               when {
                   count >= pickups[i] -> {
                       count -= pickups[i]
                       pickups[i] = 0
                   }
                   count < pickups[i] -> {
                       pickups[i] -= count
                       count = 0
                       tempEndPoint = maxOf(tempEndPoint, i)
                       break
                   }
               }
            }
            
            endPoint = tempEndPoint
            if (tempEndPoint == -1) break
        }
        
        return answer
    }
}