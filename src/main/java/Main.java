import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {
        String token = "NzYxMDcyNjMyOTU4NjgxMDk5.X3VSHw.uQHU2ZNRi52wYC6IqdbP5o2JkTo";
        Bot bot = new Bot(token);
        bot.build();
    }
}
