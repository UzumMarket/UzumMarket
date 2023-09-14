package ui;

import categories.Categories;
import categories.CategoriesService;
import product.Product;
import product.ProductService;
import user.User;
import user.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserUI {
    private final UserService userService = UserService.getInstance();
    private final CategoriesService categoriesService = CategoriesService.getInstance();
    private final ProductService productService = ProductService.getInstance();
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
                case 3 -> xaridQilganMahsulotlarimTarixi();
                case 4 -> mablag();
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
            System.out.println(count + 1 + ". " + categories.getName());
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
                default -> System.out.println("Notogri buruq kiritdingiz");
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
            System.out.println("Sizda birorta ham product yo'q" + "\n");
        }
    }

    private void xaridQilganMahsulotlarimTarixi() {
    }

    private void mablag() {

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
                case 1 -> samsung_A32();
                case 0 -> isExited = true;
                default -> System.out.println("NoTogri buruq kiritdingiz");
            }
        }
    }
}
