ReadMe

ECommerce Platform
Overview
This E-Commerce Management System is a simple Java-based console application that allows users to manage products, customers, and orders within a retail context. The system connects to a MySQL database for storing and retrieving information related to products, customers, and orders. It also includes error handling via custom exceptions to manage invalid operations.

Features:

Product Management:

	1. Add a new product
	2. View all products
	3. Update existing product details
	4. Delete a product

Customer Management:

	1. Add a new customer
	2. View all customers
	3. Update existing customer details
	4. Delete a customer

Order Management:

	1. Place a new order
	2. View all orders
	3. Update an order's status
	4. Delete an order

Project Structure:
Main.java: The main entry point for the application. It contains the main menu and routes to the respective management modules for products, customers, and orders.
DatabaseConnection.java: Manages the connection to the MySQL database.
Product.java: Contains the implementation for managing products (add, view, update, delete).
Customer.java: Contains the implementation for managing customers (add, view, update, delete).
Orders.java: Contains the implementation for managing orders (add, view, update, delete).

Exceptions:
InvalidProductException.java: Custom exception for invalid product operations.
InvalidCustomerException.java: Custom exception for invalid customer operations.
InvalidOrdersException.java: Custom exception for invalid order operations.

Database Setup:
To run this application, you need to set up a MySQL database with the following structure:

Database Name: ecommerce
Tables:
Product:
product_id (INT, Primary Key)
name (VARCHAR)
description (VARCHAR)
price (INT)
quantity (INT)
category (VARCHAR)
Customer:
customer_id (INT, Primary Key)
name (VARCHAR)
email (VARCHAR)
address (VARCHAR)
phone_number (INT)
Orders:
order_id (INT, Primary Key)
customer_id (INT, Foreign Key)
product_id (INT, Foreign Key)
quantity (INT)
order_date (DATE)
status (VARCHAR)

Prerequisites:
Java: JDK 8 or above.
MySQL: MySQL Server 5.7 or above.
MySQL Connector/J: Include the JDBC driver in your project’s classpath.
How to Run
Clone the repository:

bash
Copy code
git clone https://github.com/yogasundar07/Ecommerce-Platform.git
Navigate to the project directory:

bash
Copy code
cd Ecommerce-Platform
Set up the MySQL database by executing the provided SQL script.

Compile the Java files:

bash
Copy code
javac -d bin src/com/cts/**/*.java
Run the application:

bash
Copy code
java -cp bin com.cts.main.Main
Configuration
Database Configuration: The database connection details are hardcoded in DatabaseConnection.java. Update the URL, USER, and PASSWORD fields to match your MySQL setup.
Error Handling
Custom exceptions (InvalidProductException, InvalidCustomerException, InvalidOrdersException) are used to handle scenarios where invalid data or operations are encountered.

Contact:
For any inquiries or support, please contact yogasundar007@gmail.com.
