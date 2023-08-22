package mail;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mail.base.BaseEmail;

import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Gmail extends BaseEmail {

    private final static Gmail gmail = new Gmail();

    public Gmail session(Properties properties) {
        super.newSession(properties);
        return gmail;
    }

    public Gmail message(String subject, String body, String to) {
        super.newMessage(subject, body, to);
        return gmail;
    }

    public void executes() {
        super.execute();
    }

    public static Gmail getInstance() {
        return gmail;
    }
}