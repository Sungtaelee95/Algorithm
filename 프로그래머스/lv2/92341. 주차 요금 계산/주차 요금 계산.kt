class Solution {
    fun solution(fees: IntArray, records: Array<String>): IntArray {
        var answer: IntArray = intArrayOf()
        var tempRecords = records.toMutableList()
        var carParkingTime = mutableMapOf<String,Int>()
        
        for(i in 0 .. tempRecords.size-1){
            if(tempRecords[i].split(" ")[2] == "IN"){
                var count = 0
                for( j in i+1 .. tempRecords.size-1){
                    if(tempRecords[i].split(" ")[1] == tempRecords[j].split(" ")[1] 
                       && tempRecords[j].split(" ")[2] == "OUT") {
                        count++
                        var outtime = (tempRecords[j].split(" ")[0].split(":")[0].toInt() * 60) + 
                                        tempRecords[j].split(" ")[0].split(":")[1].toInt()
                                    
                        var intime = (tempRecords[i].split(" ")[0].split(":")[0].toInt() * 60) +
                                        tempRecords[i].split(" ")[0].split(":")[1].toInt()
                                    
                        val parkingTime = outtime - intime
                        if(carParkingTime.containsKey(tempRecords[i].split(" ")[1])){
                           var temp = carParkingTime.get(tempRecords[i].split(" ")[1])!! + parkingTime
                            carParkingTime.put(tempRecords[i].split(" ")[1], temp)
                        } else carParkingTime.put(tempRecords[i].split(" ")[1], parkingTime)
                        break
                    }
                }
                if(count == 0){ // IN 하였지만 OUT이 없을 경우
                        var intime = (tempRecords[i].split(" ")[0].split(":")[0].toInt() * 60) +
                                        tempRecords[i].split(" ")[0].split(":")[1].toInt()
                        var outtime = 1439             
                        val parkingTime = outtime - intime
                        if(carParkingTime.containsKey(tempRecords[i].split(" ")[1])){
                           var temp = carParkingTime.get(tempRecords[i].split(" ")[1])!! + parkingTime
                            carParkingTime.put(tempRecords[i].split(" ")[1], temp)
                        } else carParkingTime.put(tempRecords[i].split(" ")[1], parkingTime)
                }
            }
        }
        carParkingTime = carParkingTime.toSortedMap()
        for( time in carParkingTime){
            if(time.value <= fees[0]) answer += fees[1]
            else{
                if( (time.value - fees[0]) % fees[2] == 0 ) {
                    answer += fees[1] + ( ( (time.value - fees[0]) / fees[2] ) * fees[3] )
                } else answer += fees[1] + ( ( ( (time.value - fees[0]) / fees[2] ) + 1 ) * fees[3] )
            }
        }
        
        return answer
    }
}