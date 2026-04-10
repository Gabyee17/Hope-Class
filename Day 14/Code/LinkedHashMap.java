import java.util.*;
public class LinkedHashMap {
    public static void main(String[] args) {
        LinkedHashMap<Integer,String> map=new LinkedHashMap<>();
        map.put(1, "One");
        map.put(2,"Two");
        map.put(3, "Three");
        System.out.println(map);
    }
}