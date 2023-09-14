package ui;

import user.User;
import user.UserService;
import user.UserType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Controller implements UI {

    private final UserService userService = UserService.getInstance();

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
        System.out.print("Ismingni kirit: ");
        String name = scannerStr.nextLine();

        System.out.print("Familyangni kirit: ");
        String surname = scannerStr.nextLine();

        System.out.print("Yoshingni kiriti: ");
        int age = scannerInt.nextInt();

        System.out.print("Emailingni kiriti: ");
        String email = scannerStr.nextLine();

        System.out.print("Parolingni kirit: ");
        String password = scannerStr.nextLine();

        System.out.print("Parolni-Qayta kirit: ");
        String repeatPassword = scannerStr.nextLine();

        System.out.print("Telefon raqamni kirit: ");
        String number = scannerStr.nextLine();

        if (password.equals(repeatPassword)) {
            if (userService.isExist(email)) {
                System.out.println("Emaildan foydalanilingan!");
            } else {
                User user = new User(UUID.randomUUID(), name, surname, password, email, number, age, new ArrayList<>(), 0, UserType.USER, new ArrayList<>());
                userService.add(user);
                new UserUI().start(user);
            }
        } else {
            System.out.println("Parol mos kelmadi!");
        }
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
                } else {
                    System.out.println("Email yoki password xato❗");
                }
            }
        } else {
            System.out.println("Email topilmadi. Qaytadan urinib ko'ring ↻");
        }

        //-Maxsulotlarni kiritish
        //-Maxsulotlar ro`yxatini ko`rish
        //-Admin qo`shish
        //-Admin o`chirish
        //-Mahsulot categoriyalariga o`zgartirish kiritish
        //-Mahsulotlarga o`zgartirish kirirtish

    }
}
