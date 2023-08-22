package mail;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mail.base.BaseEmail;

import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Gmail extends BaseEmail {

    private final static Gmail gmail = new Gmail();

    @Override
    public Gmail newSession(Properties properties) {
        return newSession(properties);
    }

    @Override
    public Gmail newMessage(String subject, String body, String to) {
        return newMessage(subject, body, to);
    }

    @Override
    public void execute() {
        super.execute();
    }

    public static Gmail getInstance() {
        return gmail;
    }
}