package ui;

import mail.Gmail;
import user.User;
import user.UserService;
import user.UserType;

import java.util.List;
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
        System.out.print("Emailingizni kiriting ⇨ ");
        String noTrimEmail = scannerStr.nextLine();
        String email = noTrimEmail.trim();

        System.out.println("Passwordni kiriting ⇨ ");
        String password = scannerStr.nextLine();

        if (userService.isExist(email)) {
            List<User> all = userService.getAll();

            for (User user : all) {
                if (user.getEmail().equalsIgnoreCase(email)
                        && user.getPassword().equals(password)) {
                    UserType userType = user.getUserType();

                    if (userType == UserType.ADMIN) {
                        AdminUI adminUI = new AdminUI();
                        adminUI.start(user);
                    } else {
                        UserUI userUI = new UserUI();
                        userUI.start(user);
                    }
                }
            }
        } else {
            System.out.println("Notog'ri buyrug' kiritdingiz.Qaytadan urinib ko'ring ↻");
        }

    }

}
