package ui;

import categories.Categories;
import categories.CategoriesService;
import product.Product;
import product.ProductService;
import user.User;
import user.UserService;

import java.util.List;
import java.util.Optional;
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
                default -> System.out.println("Notog'ri buruq kiritdingiz");
            }
        }
    }

    private void mahsulotXaridQilish() {
        System.out.println("Mahsulotlar categoriyasini tanlang: ");

        int count = 0;
        for (Categories categories : categoriesService.getAll()) {
            System.out.println(count + 1 + ". " + categories.getName());
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
}
