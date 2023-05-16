package fastAmbulance;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;

public class TokenGenerator {

    private static final String SECRET = "java#";

    public static String generateToken(long id, String email) {
        Date now = new Date();
        long timestamp = now.getTime();

        String data = id + ":" + timestamp + ":" + email + ":" + SECRET;

        String hash = hashData(data);

        String token = id + ":" + timestamp + ":" + email + ":" + hash;

        return token;
    }

    public static boolean validateToken(String token) {
        String[] parts = token.split(":");
        if (parts.length != 4) {
            return false;
        }
        long id = Long.parseLong(parts[0]);
        long timestamp = Long.parseLong(parts[1]);
        String email = parts[2];
        String hash = parts[3];

        String expectedHash = hashData(id + ":" + timestamp + ":" + email + ":" + SECRET);

        return expectedHash.equals(hash);
    }

    public static Long getId(String token) {
        String[] parts = token.split(":");
        if (parts.length != 4) {
            throw new RuntimeException("The token is invalid");
        }
        return Long.parseLong(parts[0]);
    }

    private static String hashData(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException("SHA-256 algorithm not supported", ex);
        }
    }

}
