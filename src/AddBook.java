import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddBook {
    public static void main(String[] args) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO books (title, author, available) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, "Operating Systems");
            ps.setString(2, "Galvin");
            ps.setBoolean(3, true);

            ps.executeUpdate();
            System.out.println("Book added successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}