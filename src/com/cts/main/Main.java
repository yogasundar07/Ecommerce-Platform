package com.cts.main;
import java.util.Scanner;
import com.cts.ecommerceservices.Customer;
import com.cts.ecommerceservices.Orders;
import com.cts.ecommerceservices.Product;
import com.cts.exceptions.InvalidProductException;
import com.cts.exceptions.InvalidCustomerException;
import com.cts.exceptions.InvalidOrdersException;

public class Main {
    public static void main(String[] args) throws InvalidProductException, InvalidCustomerException, InvalidOrdersException {
        Scanner sc = new Scanner(System.in);
        Product productManager = new Product();
        Customer customerManager = new Customer();
        Orders orderManager = new Orders();
        while (true) {
        	System.out.println("\n-------E-commerce Platform------- ");
            System.out.println("Menu:");
            System.out.println("1. Manage Products");
            System.out.println("2. Manage Customers");
            System.out.println("3. Manage Orders");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String option = sc.next();

            int choice= Integer.parseInt(option);
            
            switch (choice) {
            case 1:
                manageProducts(productManager);
                break;
            case 2:
                manageCustomers(customerManager);
                break;
            case 3:
                manageOrders(orderManager);
                break;
            case 4:
                System.out.println("Thank you!...");
                sc.close();
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
            }
            sc.nextLine();
        }
    }

    private static void manageProducts(Product product) throws InvalidProductException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nProduct Management:");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Update Product");
        System.out.println("4. Delete Product");
        System.out.print("Choose an option: ");
        int choice= scanner.nextInt();
        switch (choice) {
            case 1:
                product.addProduct();
                break;
            case 2:
                product.viewProduct();
                break;
            case 3:
                product.updateProduct();
                break;
            case 4:
                product.deleteProduct();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }




    private static void manageCustomers(Customer customer) throws InvalidCustomerException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nCustomer Management:");
        System.out.println("1. Add new Customer details");
        System.out.println("2. View Customer details");
        System.out.println("3. Update Customer details");
        System.out.println("4. Delete Customers details");
        System.out.print("Choose an option: ");
      int choice= scanner.nextInt();

        switch (choice) {
            case 1:
                customer.addCustomer();
                break;
            case 2:
                customer.viewCustomer();
                break;
            case 3:
                customer.updateCustomer();
                break;
            case 4:
                customer.deleteCustomer();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }


    private static void manageOrders(Orders orders) throws InvalidOrdersException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nOrder Management:");
        System.out.println("1. Place Order");
        System.out.println("2. View Order");
        System.out.println("3. Update Order Status");
        System.out.println("4. Cancel Orders");
        System.out.print("Choose an option: ");
        int choice= scanner.nextInt();

        switch (choice) {
            case 1:
                orders.addOrders();
                break;
            case 2:
                orders.viewOrders();
                break;
            case 3:
                orders.updateOrders();
                break;
            case 4:
            	orders.deleteOrders();
            	break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}