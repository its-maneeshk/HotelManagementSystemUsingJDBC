package Authentication;

import Database.ConnectingDB;
import Database.User;
import UserOption.Customer.CustomerOptions;

import java.util.List;
import java.util.Scanner;

public class Login {
    public void userLogin() {

        ConnectingDB connectingDBObj = new ConnectingDB();
        List<User> users = connectingDBObj.databaseConnection();
        CustomerOptions customerOptionsObj = new CustomerOptions();
        WrongPasswordPortal wrongPasswordPortalObj = new WrongPasswordPortal();

        Scanner sc = new Scanner(System.in);

        System.out.println("""
                **********************************************************************************
                *                                                                                *
                *                    >>>>>> Welcome To Login Page <<<<<<                         *
                *                                                                                *
                **********************************************************************************
                """);
        System.out.print("ENTER USERNAME: ");
        String userLoginUserName = sc.nextLine();
        System.out.print("ENTER PASSWORD: ");
        String userLoginPassword = sc.nextLine();


        for (User user : users) {
            if (userLoginUserName.equals(user.getUserName()) && userLoginPassword.equals(user.getPassword())) {
                System.out.println("\n");
                System.out.println("\u001B[92m" + "                             Hola!! LOGGED IN SUCCESSFULLY" + "\033[0m");
                customerOptionsObj.loginAsCustomer();
            }
            else {
                System.out.println("\u001B[91m" + "Sorry :( \nWrong User Name or Password!!! \n" + "\u001B[92m" + "Please check and Try again." + "\033[0m");
                wrongPasswordPortalObj.wrongPasswordRedirect();
            }
        }
    }
}
