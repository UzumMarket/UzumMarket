package ui;

import categories.Categories;
import categories.CategoriesService;
import jdk.jfr.Category;
import product.Product;
import product.ProductService;
import user.User;
import user.UserService;

import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class AdminUI {

    public static Scanner scannerInt = new Scanner(System.in);
    public static Scanner scannerStr = new Scanner(System.in);

    private final UserService userService = UserService.getInstance();
    private final CategoriesService categoriesService = CategoriesService.getInstance();
    private final ProductService productService = ProductService.getInstance();

    public void start(User user) {

        boolean isExited = false;
        while (!isExited) {
            System.out.print("""
                    1. Mahsulotlar kiritish
                    2. Mahsulotlar ro`yxatini ko`rish
                    3. Admin qo`shish
                    4. Admin o`chirish
                    5. Mahsulot categoriyasini qo`shish
                    6. Mahsulotlarga o`zgartirish kiritish
                                        
                    0. Chiqish
                    >>\s""");
            int command = scannerInt.nextInt();

            switch (command) {
                case 1 -> addProducts();
                case 2 -> viewProductsList();
                case 3 -> addAdmin();
                case 4 -> deleteAdmin();
                case 0 -> isExited = true;
            }
        }

    }

    private void deleteAdmin() {

    }

    private void addAdmin() {
        System.out.print("Admin Kiritmoqchi bulgan userni idisini kiriting: ");
        String adminId = scannerStr.nextLine();
        UUID uuid = UUID.fromString(adminId);
        Optional<User> optionalUser = userService.findById(uuid);

        if(optionalUser.isPresent()){

        }else{
            System.out.println("User Topilmadi!");
        }
    }

    private void viewProductsList() {
        boolean isExit = false;

        while (!isExit) {
            if (categoriesService.getAll().size() > 0) {
                int count = 0;
                for (Categories categories : categoriesService.getAll()) {
                    count++;
                    System.out.println(count + " - " + categories.getName() + " - " + productService.findByCategoryId(categories.getId()).size());
                }
                System.out.println("0. Chiqish");

                System.out.print(">> ");

                int key = scannerInt.nextInt();

                if (key == 0) {
                    isExit = true;
                } else if (key > 0 && categoriesService.getAll().size() >= key) {
                    if (productService.findByCategoryId(categoriesService.getAll().get(key - 1).getId()).size() > 0) {
                        for (Product product : productService.findByCategoryId(categoriesService.getAll().get(key - 1).getId())) {
                            System.out.println(product.getName() + product.getModel() + product.getPrice() + " so'm");
                        }
                    }else{
                        System.out.println("Mahsulotlar topilmadi!");
                    }
                } else {
                    System.out.println("Noto'g'ri buyrug' kiritingiz!");
                }

            } else {
                isExit = true;
                System.out.println("Catogorylar topilmadi!");
            }
        }
    }

    private void addProducts() {
        System.out.println("Iltimos, mahsulot kiritilishi kerak bo`lgan categoriyani tanlang: ");

        int count = 0;
        for (Categories categories : categoriesService.getAll()) {
            System.out.println(0 + 1 + ". " + categories.getName());
        }
        int command = scannerInt.nextInt();

        if (command == 0) {
            System.out.println("Noto`g`ri buyruq kiritdingiz!");
        }
        /**
         **private String model;
         *     private String name;
         *     private UUID categories_id;
         *     private double price;
         *     private String description;
         *     private Status status;
         */

        if (command > 0 && command < categoriesService.getAll().size()) {
            Product product = new Product();
            System.out.print("Mahsulot nomini kirirting: ");
            String productName = scannerStr.nextLine();
            product.setName(productName);

            System.out.println("Mahsulot narxini kiriting: ");
            Double productPrice = scannerInt.nextDouble();
            product.setPrice(productPrice);

            System.out.println("Mahsulotga izoh kiriting: ");
            String productDescription = scannerStr.nextLine();
            product.setDescription(productDescription);

            System.out.println("");
        }

    }
}
