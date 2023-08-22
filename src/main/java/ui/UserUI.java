package ui;

import categories.Categories;
import categories.CategoriesService;
import mail.Gmail;
import product.ProductService;
import user.UserService;

public class UserUI implements UI {

    private final UserService userService = UserService.getInstance();
    private final CategoriesService categoriesService = CategoriesService.getInstance();
    private final ProductService productService = ProductService.getInstance();

    @Override
    public void start() {

    }
}
