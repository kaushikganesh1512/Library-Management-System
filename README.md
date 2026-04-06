# 📚 Library Management System (Java + JDBC)

## 📌 Project Overview
This project is a console-based Library Management System developed using Java and JDBC (MySQL).  
It allows users to manage library operations such as adding books, viewing books, borrowing, and returning books using a menu-driven interface.

---

## 🚀 Features
- ➕ Add new books to the library  
- 📋 View all books  
- 📖 View available books only  
- 🔄 Borrow books (updates availability)  
- 🔁 Return books (updates availability)  
- 🧾 Maintain transaction history (BORROW / RETURN)  
- ⚠️ Input validation and error handling  

---

## 🛠️ Technologies Used
- Java  
- JDBC (Java Database Connectivity)  
- MySQL Database  
- SQL  

---

## 🧠 Concepts Covered
- Object-Oriented Programming (OOP)  
- Database Connectivity using JDBC  
- PreparedStatement and ResultSet  
- CRUD Operations  
- Menu-driven console application  
- SQL queries and transactions  

---

## 📂 Project Structure

LibraryManagementSystem/
│
├── lib/
│ └── mysql-connector-j-9.6.0.jar
│
└── src/
├── DBConnection.java
├── TestConnection.java
├── AddBook.java
├── BorrowBook.java
├── ReturnBook.java
└── LibraryApp.java


---

## ▶️ How to Run

### Step 1: Compile the code
javac -cp ".:lib/mysql-connector-j-9.6.0.jar" src/*.java


### Step 2: Run the application
java -cp ".:lib/mysql-connector-j-9.6.0.jar:src" LibraryApp


---

## 🖥️ Sample Menu
===== LIBRARY MENU =====

1.Add Book
2.View All Books
3.View Available Books
4.Borrow Book
5.Return Book
6.Exit


---

## 🗄️ Database Schema

### Books Table
- id (INT, Primary Key)
- title (VARCHAR)
- author (VARCHAR)
- available (BOOLEAN)

### Users Table
- id (INT, Primary Key)
- name (VARCHAR)

### Transactions Table
- id (INT, Primary Key)
- book_id (INT)
- user_id (INT)
- type (BORROW / RETURN)

---

## 🎯 Learning Outcomes
- Learned how to connect Java with MySQL using JDBC  
- Implemented CRUD operations with real database  
- Understood database-driven application development  
- Improved debugging and problem-solving skills  
- Built a complete backend project  

---

## 🔗 Future Enhancements
- Add user authentication system  
- Implement GUI using Java Swing or JavaFX  
- Add due date and fine calculation  
- Integrate REST API for web-based system  

---

## 👨‍💻 Author
**Kaushik Ganesh**

---

## 📜 License
This project is created for educational purposes.
