package requirements;

public class Validator {

    public static boolean validEmail(String email) {
        if(email == null) {
            return false;
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }

    public static boolean validUserName(String userName) {
        if(userName == null || userName.length() < 3){
            return false;
        }
        String regex = "^[A-Za-z0-9_-]{3,}";
        return userName.matches(regex);
    }

    public static boolean validPassword(String password) {
        return password != null && password.length() >= 8;
    }
}
