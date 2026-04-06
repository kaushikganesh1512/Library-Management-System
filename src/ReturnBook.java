import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class ReturnBook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();

            System.out.print("Enter User ID: ");
            int userId = sc.nextInt();

            // Step 1: Make book available again
            String updateQuery = "UPDATE books SET available = true WHERE id = ?";
            PreparedStatement ps1 = con.prepareStatement(updateQuery);
            ps1.setInt(1, bookId);

            int updated = ps1.executeUpdate();

            if (updated > 0) {

                // Step 2: Add RETURN transaction
                String insertQuery = "INSERT INTO transactions (book_id, user_id, type) VALUES (?, ?, 'RETURN')";
                PreparedStatement ps2 = con.prepareStatement(insertQuery);

                ps2.setInt(1, bookId);
                ps2.setInt(2, userId);
                ps2.executeUpdate();

                System.out.println("Book returned successfully!");
            } else {
                System.out.println("Invalid Book ID!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}