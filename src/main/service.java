public class ExampleService {

    private String name;

    public ExampleService(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Error: missing return type
    public greet() {
        return "Hello, " + name + "!";
    }

    // Error: unreachable code
    public void unreachableCodeExample() {
        System.out.println("This is reachable code.");
        return;
        System.out.println("This code will never be reached.");
    }

    // Error: incompatible types
    public int incompatibleTypesExample() {
        String result = "Hello, " + name + "!";
        return result;
    }

    // Error: division by zero
    public int divideByZeroExample(int x) {
        return x / 0;
    }

    // Error: null pointer exception
    public String nullPointerExceptionExample(String str) {
        return str.toUpperCase();
    }

    // Error: array index out of bounds
    public int arrayIndexOutOfBoundsExceptionExample(int[] arr) {
        return arr[5];
    }

    // Error: infinite loop
    public void infiniteLoopExample() {
        while (true) {
            System.out.println("This is an infinite loop.");
        }
    }
}
