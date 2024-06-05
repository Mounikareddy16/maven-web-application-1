import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VulnerableServlet extends HttpServlet {

    // Hardcoded credentials (Insecure)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userInput = request.getParameter("userInput");
        
        // SQL Injection vulnerability
        String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
             
            while (rs.next()) {
                // XSS vulnerability
                String userData = rs.getString("data");
                response.getWriter().println("<html><body>");
                response.getWriter().println("User Data: " + userData); // User data is not sanitized
                response.getWriter().println("</body></html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
