package ui;

import categories.CategoriesService;
import product.ProductService;
import user.UserService;

public class AdminUI implements UI{

    private final UserService userService = UserService.getInstance();
    private final CategoriesService categoriesService = CategoriesService.getInstance();
    private final ProductService productService = ProductService.getInstance();
    @Override
    public void start() {

    }
}
