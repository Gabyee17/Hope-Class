import java.util.*;
public class SetImplementation {
    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
    set.add(10);
    set.add(20);
    set.remove(10);
    set.add(10);
    set.add(30);
    Iterator<Integer> it=set.iterator();
    while(it.hasNext()){
        System.out.print(it.next() +" ");
    }
    }
}