package requirements;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHasher {

    public static String getHash(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger BIG = new BigInteger(1, messageDigest);
        StringBuilder hex = new StringBuilder(BIG.toString(16));
        while (hex.length() < 64) {
            hex.insert(0, "0");
        }
        return hex.toString();
    }

    public static boolean checkPassword(String checkedPassword,String hash) throws NoSuchAlgorithmException {
        return hash.equals(getHash(checkedPassword));
    }

}
