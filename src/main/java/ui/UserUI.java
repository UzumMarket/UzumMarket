package ui;

import categories.Categories;
import categories.CategoriesService;
import mail.Gmail;
import product.ProductService;
import user.User;
import user.UserService;

public class UserUI {

    private final UserService userService = UserService.getInstance();
    private final CategoriesService categoriesService = CategoriesService.getInstance();
    private final ProductService productService = ProductService.getInstance();

    public void start(User user) {

    }
}
