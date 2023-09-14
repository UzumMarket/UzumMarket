package ui;

import categories.Categories;
import categories.CategoriesService;
import jdk.jfr.Category;
import product.Product;
import product.ProductService;
import user.User;
import user.UserService;

import java.util.Locale;
import java.util.Scanner;

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
    }

    private void viewProductsList() {
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
