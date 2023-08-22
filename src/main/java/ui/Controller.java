package ui;

import categories.CategoriesService;
import mail.Gmail;
import product.ProductService;
import user.UserService;

public class Controller implements UI {

    private final UserService userService = UserService.getInstance();
    private final Gmail gmail = Gmail.getInstance();

    @Override
    public void start() {

    }
}
