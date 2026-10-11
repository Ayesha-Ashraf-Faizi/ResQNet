package resqnet.model;

public class Admin extends Person {

    private String username;
    private String password;

    public Admin(String name, String phone, String location,
                 String username, String password) {

        super(name, phone, location);

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean login(String user, String pass) {
        return username.equals(user) && password.equals(pass);
    }

    @Override
    public String getRole() {
        return "Administrator";
    }
}