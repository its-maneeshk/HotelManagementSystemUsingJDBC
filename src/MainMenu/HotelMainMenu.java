package MainMenu;

import Authentication.*;
import UserOption.Admin.AdminOptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelMainMenu {
    public void HotelManagementSystemMenu() {

        Scanner sc = new Scanner(System.in);

        AdminOptions adminOptionsObj = new AdminOptions();
        Login userLoginObj = new Login();
        CustomerSignUp customerSignUpObj = new CustomerSignUp();
        WrongPasswordPortal wrongPasswordPortalObj = new WrongPasswordPortal();
        LoginAsAdmin loginAsAdminObj = new LoginAsAdmin();

        System.out.println("""
                **********************************************************************************
                *                                                                                *
                *                    >>>>>> Welcome To Landing Page <<<<<<                       *
                *                                                                                *
                **********************************************************************************
                """);

        while (true) {
            System.out.println("1. Log In (as admin)");
            System.out.println("2. Log In (as customer)");
            System.out.println("3. Customer Sign Up");
            System.out.println("4. Forget Password");
            System.out.print("\u001B[92m" + "Choose suitable option >>> " + "\033[0m");


            try {
                int MenuChoice = sc.nextInt();
                switch (MenuChoice) {
                    case 1:
                        loginAsAdminObj.adminLoginPage();
                        break;
                    case 2:
                        userLoginObj.userLogin();
                        break;
                    case 3:
                        customerSignUpObj.customerCreateAccount();
                        break;
                    case 4:
                        wrongPasswordPortalObj.wrongPasswordRedirect();
                        break;
                    default:
                        System.out.println("Choose between available options only!!!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Option\nPlease choose from the available options");
                sc.next();
            }
        }
    }
}
