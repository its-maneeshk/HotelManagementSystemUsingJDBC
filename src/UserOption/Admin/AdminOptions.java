package UserOption.Admin;

import Authentication.LogOut;
import MainMenu.HotelMainMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminOptions {
    public void adminOptions() {
        Scanner sc = new Scanner(System.in);
        LogOut logOutObj = new LogOut();
        HotelMainMenu hotelMainMenuObj = new HotelMainMenu();
        System.out.println("CAUTION!!! This is the admin page unwanted changes may cause error\n");
        System.out.println("Welcome Mr. Manish Patel");

        while (true) {
            System.out.println("1. See Hotel Occupancy");
            System.out.println("2. See Employee Details");
            System.out.println("3. Add New Employee");
            System.out.println("4. Logout");
            System.out.println("5. Exit");
            System.out.print("\u001B[92m" + "Choose suitable option >>> " + "\033[0m");

            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("See Hotel Occupancy Functionality not added");
                        break;
                    case 2:
                        System.out.println("See Employee Details Functionality not added");
                        break;
                    case 3:
                        System.out.println("add Employee Details Functionality not added");
                        break;
                    case 4:
                        logOutObj.printLogOut();
                        hotelMainMenuObj.HotelManagementSystemMenu();
                        break;
                    case 5:
                        System.out.println("\u001B[91m" + "Exiting the program. Thank you!" + "\033[0m");
                        System.exit(0);
                    default:
                        System.out.println("Add New Employee Functionality not added");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Option\nPlease choose from the available options");
                sc.next();
            }
        }
    }
}
