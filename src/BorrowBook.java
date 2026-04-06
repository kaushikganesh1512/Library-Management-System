import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class BorrowBook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Connection con = DBConnection.getConnection();

            System.out.print("Enter Book ID: ");
            int bookId = sc.nextInt();

            System.out.print("Enter User ID: ");
            int userId = sc.nextInt();

            // 1. Update book availability
            String updateQuery = "UPDATE books SET available = false WHERE id = ? AND available = true";
            PreparedStatement ps1 = con.prepareStatement(updateQuery);
            ps1.setInt(1, bookId);

            int rows = ps1.executeUpdate();

            if (rows > 0) {
                // 2. Insert transaction
                String insertQuery = "INSERT INTO transactions (book_id, user_id, type) VALUES (?, ?, 'BORROW')";
                PreparedStatement ps2 = con.prepareStatement(insertQuery);

                ps2.setInt(1, bookId);
                ps2.setInt(2, userId);
                ps2.executeUpdate();

                System.out.println("Book borrowed successfully!");
            } else {
                System.out.println("Book not available!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}