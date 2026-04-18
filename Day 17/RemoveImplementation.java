import java.util.*;
public class RemoveImplementation {
    public static void main(String[] args){
        List<Integer> list=new ArrayList<>();
        list.add(10);
        list.add(20);    
        list.add(30);
        System.out.println("Before removal: "+list);
        for(Integer num:list){
            if(num%2==0){
                list.remove(num);
            }
        }
        System.out.println("After removal: "+list);
    }
}