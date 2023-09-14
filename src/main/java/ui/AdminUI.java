package ui;

import categories.Categories;
import categories.CategoriesService;
import jdk.jfr.Category;
import product.Product;
import product.ProductService;
import product.Status;
import user.User;
import user.UserRepository;
import user.UserService;
import user.UserType;

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
                    1. Mahsulot categoriyasini qo`shish
                    2. Mahsulotlar kiritish 
                    3. Mahsulotlar ro`yxatini ko`rish 
                    4. Mahsulotlarga o`zgartirish kiritish 
                    5. Admin qo`shish
                    6. Admin o`chirish
                                        
                    0. Chiqish
                    >>\s""");
            int command = scannerInt.nextInt();

            switch (command) {
                case 1 -> addCategories();
                case 2 -> addProducts();
                case 3 -> viewProductsList();
                case 4 -> updateProducts();
                case 5 -> addAdmin();
                case 6 -> deleteAdmin();
                case 0 -> isExited = true;
                default -> System.out.println("Noto`g`ri buyruq kiritdingiz!");
            }
        }
    }

    private void updateProducts() {
        if (categoriesService.getAll().size() > 0) {
            System.out.println("Iltimos, mahsulot categoriyalaridan birini tanlang: ");
            int count1 = 0;
            for (Categories categories : categoriesService.getAll()) {
                System.out.println(count1 + 1 + " " + categories.getName());
            }
            int command1 = scannerInt.nextInt();

            if (command1 > 0 && command1 <= categoriesService.getAll().size()) {

                System.out.println("Iltimos, o`zgartirish kiritmoqchi bo`lgan mahsulotizni tanlang: ");
                int count2 = 0;
                for (Product product : productService.getAll()) {
                    System.out.println(count2 + 1 + " " + product.getName());
                    count2++;
                }
                int command2 = scannerInt.nextInt();
                if (command2 > 0 && command2 < productService.getAll().size()) {
                    System.out.println("1. Modelini o`zgartirish: ");

                    System.out.println("""
                            1. Nomini o`zgartirish
                            2. Modelini o`zgartirish
                            3. Categoriyasini o`zgartirish
                            4. Narxini o`zgartirish
                            5. Izohini""");
                }
            } else {
                System.out.println("Noto`g`ri buyruq kiritdingiz!");
            }
        } else {
            System.out.println("categorylar topilmadi!");
        }
    }

    private void addCategories() {
        Categories categories = new Categories();
        System.out.println("Iltimos, categoriya nomini kiriting: ");
        String categoryName = scannerStr.nextLine();

        categories.setName(categoryName);
        categories.setId(UUID.randomUUID());
        categoriesService.add(categories);
    }

    private void deleteAdmin() {
        System.out.print("Admin olmoqchi bulgan user idisi kiriting: ");
        String id = scannerStr.nextLine();
        UUID uuid = UUID.fromString(id);
        Optional<User> optionalUser = userService.findById(uuid);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getUserType() != UserType.USER) {
                user.setUserType(UserType.USER);
                UserRepository.getInstance().update(user);
            } else {
                System.out.println("Ushbu user adminlar royhatida mavjud emas!");
            }
        } else {
            System.out.println("Bu user admin emas!");
        }
    }

    private void addAdmin() {
        System.out.print("Admin qilmoqchi bulgan user idisi kiriting: ");
        String id = scannerStr.nextLine();
        UUID uuid = UUID.fromString(id);
        Optional<User> optionalUser = userService.findById(uuid);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getUserType() != UserType.ADMIN) {
                user.setUserType(UserType.ADMIN);
                UserRepository.getInstance().update(user);
            } else {
                System.out.println("Ushbu user uje admin bulgan!");
            }
        } else {
            System.out.println("Bu user topilmadi!");
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
                            System.out.println(product.getName() + " - " + product.getModel() + " - " + product.getPrice() + " so'm");
                        }
                    } else {
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

        if (categoriesService.getAll().size() > 0) {
            int count = 0;

            System.out.println("Iltimos, mahsulot kiritilishi kerak bo`lgan categoriyani tanlang: ");

            for (Categories categories : categoriesService.getAll()) {
                System.out.println(count + 1 + ". " + categories.getName());
                count++;
            }
            int command = scannerInt.nextInt();


            if (command > 0 && command <= categoriesService.getAll().size()) {
                Product product = new Product();

                System.out.print("Mahsulot nomini kirirting: ");
                String productName = scannerStr.nextLine();
                product.setName(productName);

                System.out.println("Mahsulot modelini kirirting: ");
                String productModel = scannerStr.nextLine();
                product.setModel(productModel);

                System.out.println("Mahsulot narxini kiriting: ");
                Double productPrice = scannerInt.nextDouble();
                product.setPrice(productPrice);

                System.out.println("Mahsulotga izoh kiriting: ");
                String productDescription = scannerStr.nextLine();
                product.setDescription(productDescription);

                product.setStatus(Status.START);
                product.setCategories_id(categoriesService.getAll().get(command - 1).getId());

                product.setId(UUID.randomUUID());
                productService.add(product);
                System.out.println("Mahsulot muvaffaqqiyatli kiritildi!");
            } else {
                System.out.println("Noto`g`ri buyruq kiritdingiz!");
            }
        } else {
            System.out.println("Catagory Topilmadi!");
        }
    }
}