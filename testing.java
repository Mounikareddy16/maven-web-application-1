import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsecureServlet extends HttpServlet {

    // Using insecure Random class
    private static final Random RANDOM = new Random();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Logging sensitive information (Insecure)
        System.out.println("Received login request from username: " + username + " with password: " + password);

        // Improper input validation
        if (username == null || password == null) {
            response.getWriter().println("Username or password cannot be null");
            return;
        }

        // Weak password check (Insecure)
        if (password.length() < 8) {
            response.getWriter().println("Password is too short");
            return;
        }

        // Insecure random number generation
        int verificationCode = RANDOM.nextInt(999999);
        response.getWriter().println("Your verification code is: " + verificationCode);

        // Lack of error handling
        try {
            // Simulate some processing that could fail
            processUserLogin(username, password);
        } catch (Exception e) {
            // Not handling the exception properly
            e.printStackTrace();
        }
    }

    private void processUserLogin(String username, String password) throws Exception {
        // Simulate processing
        if ("error".equals(username)) {
            throw new Exception("Simulated processing error");
        }
        // Assume the login is always successful
        System.out.println("User " + username + " logged in successfully with password: " + password);
    }
}
