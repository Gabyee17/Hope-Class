public class FunctionalInterface{
    public static void main(String[] args) {
        Consumer<String> val=name -> System.out.println(name);
        val.accept("Edlyn");
        Supplier<Double> val=()->Math.random();
        System.out.println(val.get());
        
    }
}