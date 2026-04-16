class Solution {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        List<Integer> arr = new ArrayList<>();
        for(int i=0;i<numOnes;i++)
            arr.add(1);
        for(int i=0;i<numZeros;i++)
            arr.add(0);
        for(int i=0;i<numNegOnes;i++)
            arr.add(-1);
        int sum=0;
        for(int i=0;i<k;i++)
            sum+=arr.get(i);
        return sum;
    }
}