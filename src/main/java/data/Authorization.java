package data;

public class Authorization {
    private String email;
    private String password;

    public Authorization(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static Authorization from(User user) {
        return new Authorization(user.getEmail(), user.getPassword());
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
}