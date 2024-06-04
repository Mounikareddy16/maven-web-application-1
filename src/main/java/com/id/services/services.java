import java.util.List;

public class UserService {

    private List<User> users;

    public UserService() {
        this.users = new ArrayList<>();
    }

    // Method to add a user
    public void addUser(User user) {
        if (user.getName() == null) {
            throw new IllegalArgumentException("User name cannot be null");
        }
        users.add(user);
    }

    // Method to get a user by ID
    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;  // Should return Optional<User> instead
    }

    // Method to remove a user
    public void removeUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        users.remove(user);  // Potentially throws ConcurrentModificationException
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        
        User user1 = new User(1, "Alice");
        User user2 = new User(2, "Bob");

        userService.addUser(user1);
        userService.addUser(user2);

        System.out.println(userService.getUserById(1).getName()); // Possible NullPointerException
        System.out.println(userService.getUserById(3).getName()); // Possible NullPointerException

        userService.removeUser(user1);

        System.out.println("Service execution completed.");
    }
}

// A simple User class
class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
