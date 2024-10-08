package com.cts.ecommerceservices;
import java.sql.*;
import java.util.Scanner;

import com.cts.database.DatabaseConnection;
import com.cts.exceptions.InvalidCustomerException;

public class Customer {

    public void addCustomer() throws InvalidCustomerException {
        try {Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in); 

        	System.out.println("Enter customer id: ");
            int customer_id = scanner.nextInt();
        	
            System.out.println("Enter name: ");
            String name = scanner.next();
            
            System.out.println("Enter email: ");
            String email = scanner.next();
            
            System.out.println("Enter address: ");
            String address = scanner.next();

            System.out.println("Enter phone_number: ");
            int phone_number = scanner.nextInt();
            
            if(customer_id==-1 || name==null || email==null || address==null || phone_number==-1) {
            	throw new InvalidCustomerException("Enter all the details to proceed");
            }
            
            String query = "INSERT INTO Customer (customer_id, name, email, address, phone_number) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            	pstmt.setInt(1, customer_id);
                pstmt.setString(2, name);
                pstmt.setString(3, email);
                pstmt.setString(4, address);
                pstmt.setInt(5, phone_number);
                pstmt.executeUpdate();
                System.out.println("Customer added successfully.");
                
                
            }
            conn.close();
        } 	catch (SQLException e) {
        		System.out.println("A database error occured.");
    		}
    		catch(InvalidCustomerException e) {
    			System.out.println(e.getMessage());
    		}
    }

    
    public void updateCustomer() throws InvalidCustomerException {
        try {Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in); 

            System.out.println("Enter current customer ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            int customer_id = id;
            
            System.out.println("Enter new customer name: ");
            String name = scanner.next();

            System.out.println("Enter new email: ");
            String email = scanner.next();
            
            System.out.println("Enter new address: ");
            String address = scanner.next();

            System.out.println("Enter new phone number: ");
            int phone_number = scanner.nextInt();

            if(customer_id==-1 || name==null || email==null || address==null || phone_number==-1) {
            	throw new InvalidCustomerException("Enter all the details to proceed");
            }
            
            String query = "UPDATE Customer SET name = ?, email= ?, address = ?, phone_number = ? WHERE customer_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {  
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, address);
                pstmt.setInt(4, phone_number);
                pstmt.setInt(5, customer_id);

                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Customer details updated successfully.");
                } else {
                    System.out.println("Customer not found.");
                }
            }
            conn.close();
        } 	catch (SQLException e) {
        		System.out.println("A database error occured.");
    		}
    		catch(InvalidCustomerException e) {
    			System.out.println(e.getMessage());
    		}
    }

    public void deleteCustomer() throws InvalidCustomerException {
        try {Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in); 

            System.out.println("Enter customer ID to delete: ");
            int id = scanner.nextInt();

            String query = "DELETE FROM Customer WHERE customer_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, id);
                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Customer details deleted successfully.");
                } else {
                		throw new InvalidCustomerException("Invalid Customer ID");   
                		}
            }
            conn.close();
        } 	catch (SQLException e) {
        		System.out.println("A database error occured.");
    		}
    		catch(InvalidCustomerException e) {
    			System.out.println(e.getMessage());
    		}
    }

    public void viewCustomer() throws InvalidCustomerException {
        try {Connection conn = DatabaseConnection.getConnection();

            String query = "SELECT * FROM Customer";
            
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                	if(!(rs.isBeforeFirst())) {
                		throw new InvalidCustomerException("No customers found");
                	}
                	
                    System.out.println("Customer List:");
                    System.out.println("customer_id | name 	 | email 		     | address 	  | phone_number");
                    System.out.println("---------------------------------------------------------------------------------");


                    while(rs.next()) {
                        int customer_id = rs.getInt("customer_id");
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        String address = rs.getString("address");
                        int phone_number = rs.getInt("phone_number");
                        System.out.printf("%-11d | %-10s | %-25s | %-10s | %-10d%n", customer_id, name, email, address, phone_number);
                    }
                }
            }
            conn.close();
        } 	catch (SQLException e) {
            	System.out.println("A database error occured.");
        	}
        	catch(InvalidCustomerException e) {
        		System.out.println(e.getMessage());
        	}
    }
}