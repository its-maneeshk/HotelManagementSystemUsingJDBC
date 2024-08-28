package UserOption.Customer;

import Authentication.LogOut;
import Greeting.GreetingTemplate;
import MainMenu.HotelMainMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerOptions {
    public void loginAsCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n");
        GreetingTemplate greetingTemplateObj = new GreetingTemplate();
        greetingTemplateObj.greeting();
        HotelMainMenu hotelMainMenuObj = new HotelMainMenu();
        LogOut logOutObj = new LogOut();

        while (true) {
            System.out.println("\u001B[34m" + "Customer Options:" + "\u001B[0m");
            System.out.println("1. Room Booking");
            System.out.println("2. Hotel Booking for Party Function");
            System.out.println("3. Check Availability");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Log Out");
            System.out.println("6. Exit");
            System.out.print("\u001B[92m" + "Choose suitable option >>> " + "\033[0m");

            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Room Booking Functionality not added");
                        break;
                    case 2:
                        System.out.println("Hotel Booking for Party Function Functionality not added");
                        break;
                    case 3:
                        System.out.println("Check Availability Functionality not added");
                        break;
                    case 4:
                        System.out.println("Cancel Booking Functionality not added");
                        break;
                    case 5:
                        logOutObj.printLogOut();
                        hotelMainMenuObj.HotelManagementSystemMenu();
                        break;
                    case 6:
                        System.out.println("\u001B[91m" + "Exiting the program. Thank you!" + "\033[0m");
                        System.exit(0);
                    default:
                        System.out.println("Choose between available options only!!!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\u001B[91m" + "Invalid Option\nPlease choose from the available options" + "\033[0m");
                sc.next();
            }
        }
    }
}

