package ui;

import categories.Categories;
import categories.CategoriesService;
import product.Product;
import product.ProductService;
import product.Status;
import user.User;
import user.UserRepository;
import user.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserUI {
    private final UserService userService = UserService.getInstance();
    private final CategoriesService categoriesService = CategoriesService.getInstance();
    private final ProductService productService = ProductService.getInstance();
    private final UserRepository userRepository = UserRepository.getInstance();
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerStr = new Scanner(System.in);

    public void start(User user) {
        boolean isExited = false;
        while (!isExited) {
            System.out.print("""
                             
                    1 ⇨ Mahsulot Xarid Qilish
                    2 ⇨ Savatim
                    3 ⇨ Xarid Qilgan Mahsulotlarim Tarixi
                    4 ⇨ Mablag
                    5 ⇨ Sozlamalar
                                        
                    0 ⇨ Chiqish
                    >>\s""");


            int command = scannerInt.nextInt();
            switch (command) {
                case 1 -> mahsulotXaridQilish(user);
                case 2 -> savatim(user.getId());
                case 3 -> xaridQilganMahsulotlarimTarixi(user);
                case 4 -> mablag(user);
                case 5 -> sozlamalar(user);
                case 0 -> isExited = true;
                default -> System.out.println("NoTogri buruq kiritdingiz");
            }
        }
    }

    private void mahsulotXaridQilish(User user) {
        if (categoriesService.getAll().size() > 0) {
            System.out.println("Mahsulotlar categoriyasini tanlang: ");
            int count = 0;
            for (Categories categories : categoriesService.getAll()) {
                count++;
                System.out.println(count + ". " + categories.getName());
            }

            int command = scannerInt.nextInt();
            if (command > 0 && command <= categoriesService.getAll().size()) {
                UUID id = categoriesService.getAll().get(command - 1).getId();
                List<Product> byCategoryId = productService.findByCategoryId(id);
                //Product product = new Product(UUID.randomUUID(), "telefon", "A53", id,4000,"zor",Status.DELIVERED);
                count = 0;
                for (Product product : byCategoryId) {
                    count++;
                    System.out.println(count + ". " + product.getName());
                }

                System.out.println(">> ");

                int command1 = scannerInt.nextInt();

                if (command1 > 0 && command1 <= categoriesService.getAll().size()) {

                    Product product = productService.getAll().get(command1 - 1);

                    System.out.print(""" 

                            1 ⇨ Savatga qo'shish
                            2 ⇨ Sotib olish

                            0 ⇨ Chiqish
                            >>\s""");
                    int command12 = scannerInt.nextInt();
                    switch (command12) {
                        case 1 -> addBacket(product, user);
                        case 2 -> purchase(product, user);
                        default -> System.out.println("NoTogri buruq kiritdingiz");
                    }
                } else {
                    System.out.println("Noto`g`ri buyruq kiritdingiz!");
                }
            }
        }

    }


    private void purchase(Product product, User user) {
        product.setStatus(Status.DELIVERED);
        if (user.getBalance() >= product.getPrice()) {
            List<Product> boughtProductsHistory = user.getBoughtProductsHistory();
            boughtProductsHistory.add(product);
            user.setBoughtProductsHistory(boughtProductsHistory);
            user.setBalance(user.getBalance() - product.getPrice());
            userRepository.update(user);
        } else {
            System.out.println("Hisobgingizdagi mablag yetarli emas");
        }
    }

    private void addBacket(Product product, User user) {
        List<Product> basket = user.getBasket();
        basket.add(product);
        user.setBasket(basket);
        userRepository.update(user);
    }


    private void savatim(UUID uuid) {
        User user = userService.findById(uuid).get();
        List<Product> basket = user.getBasket();
        int count = 1;
        double allPrice = 0;

        if (basket.size() > 0) {
            for (Product product : basket) {
                System.out.println(count + ". " + product.getName() + " | " + product.getModel() + " | " + product.getPrice());
                allPrice += product.getPrice();
            }
            System.out.println("Jami summa: " + allPrice);
        } else {
            System.out.println("Sizda birorta ham product yo'q❗" + "\n");
        }
    }

    private void xaridQilganMahsulotlarimTarixi(User user) {
        if (user.getBoughtProductsHistory().size() > 0) {
            for (Product product : user.getBoughtProductsHistory()) {
                System.out.println("Mahsulot nomi ⇨ " + product.getName());
                System.out.println("Mahsulot tarifi ⇨ " + product.getDescription());
                System.out.println("Mahsulot modeli ⇨ " + product.getModel());
                System.out.println("Mahsulot narxi ⇨ " + product.getPrice());
            }
        } else {
            System.out.println("Siz biror martta ham mahsulot harid qilmagansiz❗" + "\n");
        }
    }

    private void mablag(User user) {
        boolean isExited = false;
        while (!isExited) {
            System.out.println("Sizning idingiz ⇨ " + user.getId());
            System.out.println("Sizning ismingiz ⇨ " + user.getName());
            System.out.println("Sizning familiyangiz ⇨ " + user.getLastname());
            System.out.println("Sizning yoshingiz ⇨ " + user.getAge());

            String password = user.getPassword();
            int length = password.length();
            StringBuilder pas = new StringBuilder();
            for (int i = 0; i < length; i++) {
                pas.append("*");
            }
            System.out.println("Sizning parolingiz ⇨ " + pas);
            System.out.println("Sizning balansingiz ⇨ " + user.getBalance() + "\n");
            System.out.println("1.Balans qo'shish");
            System.out.println("0.Chiqish");

            int key = scannerInt.nextInt();
            switch (key) {
                case 1 -> addBalance(user);
                case 0 -> isExited = true;
                default -> System.out.println("Notog'ri buyrug' kiritdingiz❗" + "\n");
            }

        }
    }

    private void addBalance(User user) {
        System.out.print("Qo'shmoqchi bo'lgan mablag'ni kiriting ⇨ ");
        double balance = scannerInt.nextDouble();
        double userBalance = user.getBalance();
        user.setBalance(userBalance + balance);

        userRepository.update(user);
    }

    private void sozlamalar(User user) {
        boolean isExited = false;
        while (!isExited) {
            System.out.print("""
                    1. Parolni o'zgartirish
                    2. Telefon raqamni o'zgartirish
                    3. Email ni o'zgartirish
                    0. Chiqish
                    >> \s""");
            int command = scannerInt.nextInt();
            switch (command) {
                case 1 -> changePassword(user);
                case 2 -> changePhoneNumber(user);
                case 3 -> changeEmail(user);
                case 0 -> isExited = true;
                default -> System.out.println("Noto'g'ri buyruq kiritdingiz");
            }
        }

    }

    private void changeEmail(User user) {
        System.out.print("Yangi email kiriting: ");
        String newEmail = scannerStr.nextLine();
        List<User> users = userRepository.getAll();
        for (User user1 : users) {
            if (user1.getEmail().equals(newEmail)) {
                System.out.println("Bu email allaqachon mavjud");
            } else {
                user.setEmail(newEmail);
                userRepository.update(user);
                System.out.println("Email muvaffaqiyatli o'zagartirildi");
                System.out.println();
            }
        }
    }

    private void changePhoneNumber(User user) {
        System.out.print("Yangi raqam kiriting: ");
        String newPhoneNumber = scannerStr.nextLine();
        List<User> users = userRepository.getAll();
        for (User user1 : users) {
            if (user1.getPhoneNumber().equals(newPhoneNumber)) {
                System.out.println("Bu telefon raqam allaqachon foydalanilgan");
            } else {
                user.setPhoneNumber(newPhoneNumber);
                userRepository.update(user);
                System.out.println("Raqam muvaffaqiyatli o'zgartirildi");
                System.out.println();
            }
        }
    }

    private void changePassword(User user) {
        System.out.print("Yangi parol kiriting: ");
        String newPassword = scannerStr.nextLine();
        System.out.print("Parolni qayta kiriting: ");
        String newPassword2 = scannerStr.nextLine();
        if (newPassword.equals(newPassword2)) {
            user.setPassword(newPassword);
            System.out.println("Parol muvaffaqiyatli o'zgartirildi");
            userRepository.update(user);
            System.out.println();
        } else {
            System.out.println("Parol mos kelmadi");
        }
    }
}

