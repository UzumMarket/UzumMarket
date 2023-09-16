import ui.Controller;
import user.User;
import user.UserService;
import user.UserType;

import java.util.ArrayList;
import java.util.UUID;

public class Main {
    private static final UserService s = UserService.getInstance();

    public static void main(String[] args) {
        User user = new User(UUID.randomUUID(), "123", "123", "123", "123", "123",
                123, new ArrayList<>(), 4000, UserType.ADMIN, new ArrayList<>());
        if (!s.isExist(user.getEmail())) {
            s.add(user);
        }
        System.out.println(s.getAll());
        System.out.println("All user list⬆\uFE0F");
        Controller controller = new Controller();
        controller.start();
    }
}