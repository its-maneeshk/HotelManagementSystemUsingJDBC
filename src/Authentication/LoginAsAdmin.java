package Authentication;

import Database.ConnectingDB;
import Database.User;
import UserOption.Admin.AdminOptions;

import java.util.List;
import java.util.Scanner;

public class LoginAsAdmin {
    public void adminLoginPage() {

        ConnectingDB connectingDBObj = new ConnectingDB();
        List<User> users = connectingDBObj.databaseConnection();
        AdminOptions adminOptionsObj =  new AdminOptions();
        WrongPasswordPortal wrongPasswordPortalObj = new WrongPasswordPortal();

        Scanner sc = new Scanner(System.in);

        System.out.println("""
                **********************************************************************************
                *                                                                                *
                *                    >>>>>> Welcome To Admin Page <<<<<<                         *
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
                adminOptionsObj.adminOptions();
            }
            else {
                System.out.println("\u001B[91m" + "Sorry :( \nWrong User Name or Password!!! \n" + "\u001B[92m" + "Please check and Try again." + "\033[0m");
                wrongPasswordPortalObj.wrongPasswordRedirect();
            }
        }
    }
}
