package ui;

import categories.Categories;
import categories.CategoriesService;
import product.ProductService;
import user.User;
import user.UserService;

import java.util.Scanner;

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
                case 2 -> savatim();
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
            System.out.println(count+1+". "+categories.getName());
        }


    }

    private void savatim() {

    }

    private void xaridQilganMahsulotlarimTarixi() {
    }

    private void mablag() {

    }

    private void sozlamalar() {
    }
}
