class Name{
    private String name;
    void setName(String name){
        this.name = name;
    }
    String getName(){
        return name;
    }
}
public class GetSet{
    public static void main(String[] args) {
        Name p1 = new Name();
        p1.setName("Dharun");
        System.out.println(p1.getName());
    }
}