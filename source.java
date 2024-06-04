public class HighErrorExample {
    public static void main(String[] args) {
        int x = 5;
        int y = 0;
        
        // This line will cause a division by zero error
        int z = x / y;
        
        System.out.println("Result: " + z)
    }
