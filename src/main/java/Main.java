import ui.Controller;
import user.UserService;

public class Main {
    private static final UserService s = UserService.getInstance();
    public static void main(String[] args) {
        System.out.println(s.getAll());
        Controller controller = new Controller();
        controller.start();
    }

}