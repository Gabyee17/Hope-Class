import java.util.Objects;
public class MethodOverride {
    public static void main(String[] args) {
        MethodOverride obj=new MethodOverride();
        System.out.println(obj);
    }
    @Override
    public String toString(){
        return "This is a method override example.";
    }
}
class MethodOverride1 extends MethodOverride{
    @Override
    public String toString(){
        return "This is a method override example in subclass.";
    }
}