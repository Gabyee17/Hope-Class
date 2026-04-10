import java.util.*;
public class LinkedhashSet {
    public static void main(String[] args) {
        Set<String> set=new LinkedHashSet<>();
        set.add("Mother");

        System.out.println(set);

        set.add("RampeX");

        System.out.println(set);
        
        set.add("MYSELF");
        set.add("URSELF");
        set.add("OURSELF");

        System.out.println(set);

        for(String str:set){
            System.out.println(str);
        }
    }
}