package submit;

public class Admin {
    String city;
    String user;
    String pass;

    public String getCity() {
        return city;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public Admin(String city, String user, String pass) {
        this.city = city;
        this.user = user;
        this.pass = pass;
    }
}
