package log;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogHandler {

    private static final Logger logger = Logger.getGlobal();

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            logger.config("Properties not found");
        }
        String property = properties.getProperty("log.handler.fileHandler");
        boolean isFileHandler = Boolean.parseBoolean(property);
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
        try {
            if (isFileHandler) {
                FileHandler fileHandler = new FileHandler("logs.log", true);
                fileHandler.setLevel(Level.ALL);
                fileHandler.setFormatter(new MyLogFormatter());
                logger.addHandler(fileHandler);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
