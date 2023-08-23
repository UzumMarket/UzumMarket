import logger.FileHandlers;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.ALL);
        logger.addHandler(new FileHandlers());
        logger.setUseParentHandlers(false);
    }

    public static void main(String[] args) {
        System.out.println("Barchasi Togirlandi iltimos extiyot bulib ishlating" +
                "agar ochirib yuborsangiz uziz qilib chiqasiz");
        logger.log(Level.INFO, "Hello world");
    }

    private void s() {
        System.out.println("s");
    }
}