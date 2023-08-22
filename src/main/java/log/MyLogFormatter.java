package log;

import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class MyLogFormatter extends Formatter {

    @Override
    public String format(LogRecord record) {
        Level level = record.getLevel();
        String sourceClassName = record.getSourceClassName();
        String sourceMethodName = record.getSourceMethodName();

        return level.toString() + " " + sourceClassName + " " + sourceMethodName;
    }
}
