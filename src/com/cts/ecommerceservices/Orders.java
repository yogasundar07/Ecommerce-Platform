package com.cts.ecommerceservices;
import java.sql.*;
import java.util.Scanner;

import com.cts.database.DatabaseConnection;

public class Orders {

    public void addOrders() {
        try {Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in); 

        	System.out.println("Enter order id: ");
            int order_id = scanner.nextInt();
        	
            System.out.println("Enter customer id: ");
            int customer_id = scanner.nextInt();
            
            System.out.println("Enter product id: ");
            int product_id = scanner.nextInt();
            
            System.out.println("Enter quantity: ");
            int quantity = scanner.nextInt();

            System.out.println("Enter order date(YYYY-MM-DD): ");
            String order_date = scanner.next();
            
            System.out.println("Enter order status: ");
            String status = scanner.next();

            String query = "INSERT INTO Orders (order_id, customer_id, product_id, quantity, order_date, status) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            	pstmt.setInt(1, order_id);
                pstmt.setInt(2, customer_id);
                pstmt.setInt(3, product_id);
                pstmt.setInt(4, quantity);
                pstmt.setDate(5, Date.valueOf(order_date));
                pstmt.setString(6, status);
                pstmt.executeUpdate();
                System.out.println("Order added successfully.");
                
                
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to add order. Please try again.");
        }
    }
    
    public void updateOrders() {
        try {Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in); 

            System.out.println("Enter current Order ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            int order_id = id;
            
            System.out.println("Enter new customer_id name: ");
            int customer_id = scanner.nextInt();

            System.out.println("Enter new product_id: ");
            int product_id = scanner.nextInt();
            
            System.out.println("Enter new order quantity: ");
            int quantity = scanner.nextInt();

            System.out.println("Enter new order date: ");
            String order_date = scanner.next();

            System.out.println("Enter new order status: ");
            String status = scanner.next();
            
            String query = "UPDATE Product SET customer_id = ?, product_id= ?, quantity = ?, order_date = ?, status= ? WHERE order_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {  
                pstmt.setInt(1, customer_id);
                pstmt.setInt(2, product_id);
                pstmt.setInt(3, quantity);
                pstmt.setDate(4, Date.valueOf(order_date));
                pstmt.setString(5, status);
            	pstmt.setInt(6, order_id);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Order updated successfully.");
                } else {
                    System.out.println("Order not found.");
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update Order. Please try again.");
        }
    }

    public void deleteOrders() {
        try {Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in); 

            System.out.println("Enter Order ID to delete: ");
            int id = scanner.nextInt();

            String query = "DELETE FROM Orders WHERE order_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id);
                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Order deleted successfully.");
                } else {
                    System.out.println("Order not found.");
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to delete order. Please try again.");
        }
    }

    public void viewOrders() {
        try {Connection conn = DatabaseConnection.getConnection();

            String query = "SELECT * FROM Orders";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                try (ResultSet rs = pstmt.executeQuery()) {

                    System.out.println("Orders List:");
                    System.out.println("order_id | customer_id | product_id | quantity 	 | order_date | status");
                    System.out.println("---------------------------------------------------------------------------");


                    while(rs.next()) {
                        int order_id = rs.getInt("order_id");
                        int customer_id = rs.getInt("customer_id");
                        int product_id = rs.getInt("product_id");
                        int quantity = rs.getInt("quantity");
                        String order_date = rs.getString("order_date");
                        String status = rs.getString("status");
                        System.out.printf("%-8d | %-11d | %-10d | %-10d | %-10s | %-10s%n", order_id, customer_id, product_id, quantity, order_date, status);
                    }
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to retrieve orders. Please try again.");
        }
    }
}