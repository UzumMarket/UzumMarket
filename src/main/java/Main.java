import ui.Controller;
import user.UserService;

public class Main {
    private static final UserService s = UserService.getInstance();

    public static void main(String[] args) {
        System.out.println(s.getAll());
        System.out.println("All user list⬆\uFE0F");
        Controller controller = new Controller();
        controller.start();
    }
}