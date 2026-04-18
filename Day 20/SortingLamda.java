import java.util.*;

public class SortingLambda {
    public static void main(String[] args) {
        List<String> words=new ArrayList<>();
        words.add("Banana");
        words.add("Watermelon");
        words.add("Grapes");
        words.add("Apple");
        Collections.sort(words,(a,b)->a.compareTo(b)); 
        System.out.println("Ascending order: "+words);
        Collections.sort(words,(a,b)->b.compareTo(a)); 
        words.sort(String::compareTo); 
        words.sort(Comparator.reverseOrder());
        System.out.println("Reverse order using method reference: "+words);
    }
}