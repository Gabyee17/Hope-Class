import java.util.LinkedList; // Added the specific import
import java.util.List;
    
public class LinkedListImplementation {
    public static void main(String[] args) {
        List<String> runningRace = new LinkedList<String>();
        runningRace.add("A");
        runningRace.add("B");
        runningRace.add("C");
        System.out.println(runningRace);
        runningRace.add(0, "Start Race");
        runningRace.remove("B");
        System.out.println("B is disqualified due to early start");
        System.out.println(runningRace);
        runningRace.add("Race Completed");
        System.out.println(runningRace);
        System.out.println("A won in 0.001 ms");
        System.out.println(runningRace);

    }
    
}
