import ui.Controller;
import user.User;
import user.UserService;
import user.UserType;

import java.util.ArrayList;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        UserService.getInstance().add(new User(UUID.randomUUID(), "ss", "ss", "123", "123", "999", 99, new ArrayList<>(), 99999, UserType.ADMIN, new ArrayList<>()));
        Controller controller = new Controller();
        controller.start();
        // hello coders
    }

}