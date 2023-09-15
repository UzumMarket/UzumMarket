package ui;

import categories.Categories;
import categories.CategoriesService;
import product.Product;
import product.ProductService;
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
                case 1 -> mahsulotXaridQilish();
                case 2 -> savatim(user.getId());
                case 3 -> xaridQilganMahsulotlarimTarixi(user);
                case 4 -> mablag(user);
                case 5 -> sozlamalar();
                case 0 -> isExited = true;
                default -> System.out.println("NoTogri buruq kiritdingiz");
            }
        }
    }

    private void mahsulotXaridQilish() {
        System.out.println("Mahsulotlar categoriyasini tanlang: ");

        int count = 0;
        for (Categories categories : categoriesService.getAll()) {
            count++;
            System.out.println(count + ". " + categories.getName());
        }

        boolean isExited = false;
        while (!isExited) {
            System.out.print("""   
                    1 ⇨ Telefon
                    2 ⇨ Noutbook
                    3 ⇨ Televisor
                    4 ⇨ Kiyimlar
                    5 ⇨ Mashinalar
                                        
                    0 ⇨ Chiqish 
                    >>\s""");

            int command = scannerInt.nextInt();
            switch (command) {
                case 1 -> telefon();
                case 0 -> isExited = true;
                default -> System.out.println("Notog'ri buyrug' kiritdingiz");
            }
        }
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

    private void sozlamalar() {

    }

    private void telefon() {
        System.out.println("Maxsulot modellini tanglang");
        boolean isExited = false;
        while (!isExited) {
            System.out.print("""
                                        
                    1 ⇨ Samsung | A32 | 5 000 000 so’m
                    2 ⇨ Iphone | XS | 5 550 000 so’m
                    3 ⇨ Iphone | 12 | 11 000 000 so’
                    4 ⇨ LG | 11 | 2 000 000 so’m
                    5 ⇨ Huawei | 77 | 2 000 000 so’m
                    6. Samsung | S23 | 20 000 000 so’m
                                        
                    0 ⇨ Chiqish
                    >>\s""");
            int command = scannerInt.nextInt();
            switch (command) {
                case 1 -> samsung_A32();
                case 0 -> isExited = true;
                default -> System.out.println("Notogri buruq kiritdingiz");
            }
        }
    }

    private void samsung_A32() {
        boolean isExited = false;
        while (!isExited) {
            System.out.print("""                 
                    1 ⇨ Savatga Qo’shish
                    2 ⇨ Sotib Olish
                    0 ⇨ Chiqish

                    >>\s""");


            int command = scannerInt.nextInt();
            switch (command) {
                case 1 -> productService.add(new Product());
                case 0 -> isExited = true;
                default -> System.out.println("NoTogri buruq kiritdingiz");
            }
        }
    }
}
