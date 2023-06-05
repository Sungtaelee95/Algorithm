class Solution {
    fun solution(nums: IntArray): Int {
        var answer = 0
        var sum : Int
        var count  = 0
        for(one in 0 .. nums.size - 3){
            for(two in one + 1  .. nums.size - 2){
                for(three in two + 1 .. nums.size -1){
                    sum = nums[one] + nums[two] + nums[three]
                    for(i in 1 .. sum){
                        if( sum % i == 0) count += 1
                    }
                    if(count == 2) {
                        answer += 1
                        count = 0
                    } else count = 0
                }
            }
            
        }

        return answer
    }
}