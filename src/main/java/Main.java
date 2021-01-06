import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {
        String token = "TOKEN";
        Bot bot = new Bot(token);
        bot.build();
    }
}
