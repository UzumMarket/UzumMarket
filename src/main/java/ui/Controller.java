package ui;

import categories.CategoriesService;
import mail.Gmail;
import product.ProductService;
import user.UserService;

import java.util.Scanner;

public class Controller implements UI {

    private final UserService userService = UserService.getInstance();
    private final Gmail gmail = Gmail.getInstance();

    private final Scanner scannerInt = new Scanner(System.in);
    private final Scanner scannerStr = new Scanner(System.in);

    @Override
    public void start() {

        boolean isExit = false;

        while (!isExit) {
            System.out.println("""
                    1. Ro'yhatdan O'tish
                    2. Kirish
                    0. Chiqish
                     """);

            System.out.print(">> ");

            int key = scannerInt.nextInt();

            switch (key) {
                case 1 -> createAccount();
                case 2 -> login();
                case 0 -> isExit = true;
                default -> System.out.println("Noto'g'ri buyruq kiritildi!");
            }
        }

    }

    private void createAccount() {
        System.out.println("Create Account");
    }

    private void login() {
        System.out.println("Login");
    }
}
