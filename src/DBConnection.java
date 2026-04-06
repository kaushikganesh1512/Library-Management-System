import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/library_db?useSSL=false&allowPublicKeyRetrieval=true";
            String user = "library_user";
            String password = "1234"; // change if needed

            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}