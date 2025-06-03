package utills;

public class TestContext {

    private static final ThreadLocal<String> email = new ThreadLocal<>();
    private static final ThreadLocal<String> password = new ThreadLocal<>();

   

    public static void clear() {
        email.remove();
        password.remove();
    }


    public static void setUsername(String user) {
        email.set(user);
    }
 
    public static String getUsername() {
        return email.get();
    }
 
    public static void setPassword(String pass) {
        password.set(pass);
    }
 
    public static String getPassword() {
        return password.get();
    }
 
}


