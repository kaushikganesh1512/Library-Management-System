import java.sql.*;
import java.util.Scanner;

public class LibraryApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Connection con = DBConnection.getConnection();

            while (true) {
                System.out.println("\n===== LIBRARY MENU =====");
                System.out.println("1. Add Book");
                System.out.println("2. View All Books");
                System.out.println("3. View Available Books");
                System.out.println("4. Borrow Book");
                System.out.println("5. Return Book");
                System.out.println("6. Exit");

                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1: addBook(con); break;
                    case 2: viewBooks(con); break;
                    case 3: viewAvailableBooks(con); break;
                    case 4: borrowBook(con); break;
                    case 5: returnBook(con); break;
                    case 6: System.exit(0);
                    default: System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ Add Book
    public static void addBook(Connection con) throws Exception {
        sc.nextLine(); // clear buffer

        System.out.print("Enter title: ");
        String title = sc.nextLine();

        System.out.print("Enter author: ");
        String author = sc.nextLine();

        String query = "INSERT INTO books (title, author, available) VALUES (?, ?, true)";
        PreparedStatement ps = con.prepareStatement(query);

        ps.setString(1, title);
        ps.setString(2, author);

        ps.executeUpdate();
        System.out.println("Book added successfully!");
    }

    // ✅ View All Books
    public static void viewBooks(Connection con) throws Exception {
        String query = "SELECT * FROM books";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println(
                rs.getInt("id") + " | " +
                rs.getString("title") + " | " +
                rs.getString("author") + " | " +
                rs.getBoolean("available")
            );
        }
    }

    // ✅ View Available Books
    public static void viewAvailableBooks(Connection con) throws Exception {
        String query = "SELECT * FROM books WHERE available = true";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println(
                rs.getInt("id") + " | " +
                rs.getString("title") + " | " +
                rs.getString("author")
            );
        }
    }

    // ✅ Borrow Book
    public static void borrowBook(Connection con) throws Exception {
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();

        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();

        String checkQuery = "SELECT available FROM books WHERE id = ?";
        PreparedStatement ps1 = con.prepareStatement(checkQuery);
        ps1.setInt(1, bookId);

        ResultSet rs = ps1.executeQuery();

        if (rs.next() && rs.getBoolean("available")) {

            String updateQuery = "UPDATE books SET available = false WHERE id = ?";
            PreparedStatement ps2 = con.prepareStatement(updateQuery);
            ps2.setInt(1, bookId);
            ps2.executeUpdate();

            String insertQuery = "INSERT INTO transactions (book_id, user_id, type) VALUES (?, ?, 'BORROW')";
            PreparedStatement ps3 = con.prepareStatement(insertQuery);
            ps3.setInt(1, bookId);
            ps3.setInt(2, userId);
            ps3.executeUpdate();

            System.out.println("Book borrowed successfully!");

        } else {
            System.out.println("Book not available!");
        }
    }

    // ✅ Return Book
    public static void returnBook(Connection con) throws Exception {
        System.out.print("Enter Book ID: ");
        int bookId = sc.nextInt();

        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();

        String updateQuery = "UPDATE books SET available = true WHERE id = ?";
        PreparedStatement ps1 = con.prepareStatement(updateQuery);
        ps1.setInt(1, bookId);

        int updated = ps1.executeUpdate();

        if (updated > 0) {

            String insertQuery = "INSERT INTO transactions (book_id, user_id, type) VALUES (?, ?, 'RETURN')";
            PreparedStatement ps2 = con.prepareStatement(insertQuery);
            ps2.setInt(1, bookId);
            ps2.setInt(2, userId);
            ps2.executeUpdate();

            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Invalid Book ID!");
        }
    }
}