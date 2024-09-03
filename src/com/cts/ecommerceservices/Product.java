package com.cts.ecommerceservices;
import java.sql.*;
import java.util.Scanner;

import com.cts.database.DatabaseConnection;
import com.cts.exceptions.InvalidProductException;
public class Product {

    public void addProduct() throws InvalidProductException {
        try {Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in); 

        	System.out.println("Enter product id: ");
            int product_id = scanner.nextInt();
        	
            System.out.println("Enter product name: ");
            String name = scanner.next();
            
            System.out.println("Enter product description: ");
            String description = scanner.next();
            
            System.out.println("Enter product price: ");
            int price = scanner.nextInt();

            System.out.println("Enter product quantity: ");
            int quantity = scanner.nextInt();
            
            System.out.println("Enter product category: ");
            String category = scanner.next();
            
            if(product_id==-1 || name==null || description==null || price==-1 || quantity==-1 || category==null) {
            	throw new InvalidProductException("Enter all the details to proceed");
            }

            String query = "INSERT INTO Product (product_id, name, description, price, quantity, category) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            	pstmt.setInt(1, product_id);
                pstmt.setString(2, name);
                pstmt.setString(3, description);
                pstmt.setInt(4, price);
                pstmt.setInt(5, quantity);
                pstmt.setString(6, category);
                pstmt.executeUpdate();
                System.out.println("Product added successfully.");
                
                
            }
            conn.close();
        } 
        catch (SQLException | InvalidProductException e) {
            System.out.println("Failed to add product. Please try again.");
        }
    }
    
    public void updateProduct() throws InvalidProductException {
        try {Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in); 

            System.out.println("Enter product ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            int product_id = id;
            
            System.out.println("Enter new product name: ");
            String name = scanner.next();

            System.out.println("Enter new description: ");
            String description = scanner.next();
            
            System.out.println("Enter new product price: ");
            int price = scanner.nextInt();

            System.out.println("Enter new product quantity: ");
            int quantity = scanner.nextInt();

            System.out.println("Enter new cagegory: ");
            String category = scanner.next();
            
            if(product_id==-1 || name==null || description==null || price==-1 || quantity==-1 || category==null) {
            	throw new InvalidProductException("Enter all the details to proceed");
            }
            
            String query = "UPDATE Product SET name = ?, description= ?, price = ?, quantity = ?, category= ? WHERE product_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {  
                pstmt.setString(1, name);
                pstmt.setString(2, description);
                pstmt.setInt(3, price);
                pstmt.setInt(4, quantity);
                pstmt.setString(5, category);
            	pstmt.setInt(6, product_id);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Product updated successfully.");
                } else {
                    System.out.println("Product not found.");
                }
            }
            conn.close();
        } catch (SQLException | InvalidProductException e ) {
            System.out.println("Failed to update product. Please try again.");
        }
    }

    public void deleteProduct() throws InvalidProductException {
        try {Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in); 

            System.out.println("Enter product ID to delete: ");
            int id = scanner.nextInt();

            String query = "DELETE FROM Product WHERE product_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id);
                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Product deleted successfully.");
                } else {
                	throw new InvalidProductException("Invalid ID");
                }
            }
            conn.close();
        } catch (SQLException | InvalidProductException e) {
            System.out.println("Failed to delete product. Please try again.");
        }
    }

    public void viewProduct() throws InvalidProductException {
    	try {
            Connection conn = DatabaseConnection.getConnection();

            String query = "SELECT * FROM Product";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                	if(!(rs.next())) {
                		throw new InvalidProductException("No products found");
                	}
                	System.out.println("Product List:");
                    System.out.println("product_id | name 	| description | price | quantity | category");
                    System.out.println("----------------------------------------------------------------------");

                    while(rs.next()) {
                        int product_id = rs.getInt("product_id");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        int price = rs.getInt("price");
                        int quantity = rs.getInt("quantity");
                        String category = rs.getString("category");
                        System.out.printf("%-10d | %-10s | %-11s | %-5d | %-8d | %-20s%n", product_id, name, description, price, quantity, category);
                    }
                }
            }
            conn.close();
        } catch (SQLException | InvalidProductException e) {
            System.out.println("Failed to retrieve products. Please try again.");
        }
    }
}