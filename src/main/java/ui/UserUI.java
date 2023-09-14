package ui;

import categories.Categories;
import categories.CategoriesService;
import mail.Gmail;
import product.Product;
import product.ProductRepository;
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
        while (true) {
            System.out.println("1 => Mahsulot Xarid Qilish");
            System.out.println("2 => Savatim");
            System.out.println("3 => Xarid Qilgan Mahsulotlarim Tarixi");
            System.out.println("4 => Mablag");
            System.out.println("5 => Sozlamalar");
            System.out.println("6 => Chiqish");

            int command = scannerInt.nextInt();
            switch (command) {
                case 1 -> mahsulotXaridQilish();
                case 2 -> savatim();
                case 3 -> xaridQilganMahsulotlarimTarixi();
                case 4 -> mablag();
                case 5 -> sozlamalar();
                case 6 -> System.exit(0);
            }
        }
    }

    private void mahsulotXaridQilish() {
        System.out.println("Mahsulotlar categoriyasini tanlang: ");

        int count = 0;
        for (Categories categories : categoriesService.getAll()) {
            System.out.println(count+1+" "+categories.getName());
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
