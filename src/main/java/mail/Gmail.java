package mail;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mail.base.BaseEmail;

import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Gmail extends BaseEmail {

    private final static Gmail gmail = new Gmail();

    @Override
    protected BaseEmail newSession(Properties properties) {
        return super.newSession(properties);
    }

    @Override
    protected BaseEmail newMessage(String subject, String body, String to) {
        return super.newMessage(subject, body, to);
    }

    @Override
    protected void execute() {
        super.execute();
    }

    public static Gmail getInstance() {
        return gmail;
    }
}