package Authentication;

import MainMenu.HotelMainMenu;
import UserOption.Customer.CustomerOptions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class WrongPasswordPortal {
    public void wrongPasswordRedirect() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\u001B[92m" +"""
                *****************************************************************************************************************
                *                                                                                                               *
                *                                >>>>>> Need help in password? <<<<<<                                           *
                *                                                                                                               *
                *        if you've forgotten password then follow there steps :|                                                *
                *                           >>> I. Forget Password & verify your self                                           *
                *                           >>> II. Generate Token (valid for some hours only)                                  *
                *                           >>> III. Copy generated token                                                       *
                *                           >>> IV. Choose Password Reset option                                                *
                *                           >>> V. Paste the token that you've copied                                           *
                *                           >>> VI. ENTER NEW PASSWORD & Enjoy our services                                     *
                *                                                                                                               *
                *****************************************************************************************************************
                """ + "\033[0m");

        Login loginObj = new Login();
        HotelMainMenu hotelMainMenuObj = new HotelMainMenu();
        ForgotPassword forgotPasswordObj = new ForgotPassword();
        ResetPassword resetPasswordObj = new ResetPassword();

        while (true) {
            System.out.println("1. Forget Password");
            System.out.println("2. Password Reset");
            System.out.println("3. Retry to login as Customer");
            System.out.println("4. Return to landing page");
            System.out.println("5. Exit");
            System.out.print("\u001B[97m" + "Choose suitable option >>> " + "\033[0m");
            try {
                int MenuChoice = sc.nextInt();
                switch (MenuChoice) {
                    case 1:
                        forgotPasswordObj.initiatePasswordReset();
                        break;
                    case 2:
                        resetPasswordObj.resetPassword();
                        break;
                    case 3:
                        loginObj.userLogin();
                        break;
                    case 4:
                        hotelMainMenuObj.HotelManagementSystemMenu();
                        break;
                    case 5:
                        System.out.println("\u001B[91m" + "Exiting the program. Thank you!" + "\033[0m");
                        System.exit(0);
                    default:
                        System.out.println("\u001B[91m" + "Choose between available options only!!!" + "\033[0m");
                }
            } catch (InputMismatchException e) {
                System.out.println("\u001B[91m" + "Invalid Option\nPlease choose from the available options" + "\033[0m");
                sc.next();
            }
        }
    }
}
