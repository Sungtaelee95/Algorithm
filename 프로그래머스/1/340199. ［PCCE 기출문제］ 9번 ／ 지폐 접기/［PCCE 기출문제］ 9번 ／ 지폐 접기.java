class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        while(true) {
            int maxWallet = Math.max(wallet[0], wallet[1]);
            int minWallet = Math.min(wallet[0], wallet[1]);
            int maxBill = Math.max( bill[0], bill[1]);
            int minBill = Math.min( bill[0], bill[1]);
            if (maxWallet >= maxBill && minWallet >= minBill) break;
            answer++;
            if (bill[0] > bill[1]) {
                bill[0] /= 2;
                continue;
            }
            bill[1] /= 2;
        }
        return answer;
    }
}