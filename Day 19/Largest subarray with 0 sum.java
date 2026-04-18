class Solution {
    int maxLength(int arr[]) {
            int max=0;
            int sum=0;
            Map<Integer,Integer> map=new HashMap<>();
            for(int i=0;i<arr.length;i++){
                sum+=arr[i];
                if(sum==0){
                    max=i+1;
                }
                else{
                    if(map.containsKey(sum)){
                        int len=i-map.get(sum);
                        if(max < len) max=len;
                    }else{
                        map.put(sum,i);
                    }
                }
            }
            return max;
    }
}