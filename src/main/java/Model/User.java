package Model;

import java.util.concurrent.ThreadLocalRandom;

public class User {

    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static User random() {
        int suffix = ThreadLocalRandom.current().nextInt(100, 100_000);
        return new User(suffix + "@mail.ru", "P@ssw0rd123", "Jack" + suffix);
    }
}
