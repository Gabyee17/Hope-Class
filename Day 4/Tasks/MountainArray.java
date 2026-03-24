class Solution {
    public boolean validMountainArray(int[] arr) {
        if(arr.length < 3)
            return false;
        int i = 0, j = arr.length;
        boolean isMountain = false;
        while(arr[i] < arr[i + 1] && i < j - 2){
            if(arr[i] == arr[i + 1]) return false;
            i++;
            
        }
        if(i > 0){
            for(int k = i; k < arr.length - 1; k++){
            if(arr[k] > arr[k + 1]) isMountain = true;
            else {
                isMountain = false;
                break;
            }
        }
    }
    else return false;
       
        return isMountain;
    }
}