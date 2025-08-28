import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Main {

    // Function to derive a secret key from a given password and salt
    private static byte[] deriveKey(byte[] password, byte[] salt, int iterations) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password, salt, iterations, 256);
        SecretKey secretKey = factory.generateSecret(spec);
        return secretKey.getEncoded();
    }

    // Function to encrypt a message with a given password
    public static byte[] encrypt(String message, String password, int iterations) throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        byte[] key = deriveKey(password.getBytes(), salt, iterations);

        // Create a Fernet-like encryption using AES and HMAC
        SecretKey secretKey = javax.crypto.KeyGenerator.getInstance("AES").generateKey();
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());

        // Create HMAC
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] hmac = mac.doFinal(encryptedMessage);

        // Combine salt, iterations, encrypted message, and HMAC
        byte[] combined = new byte[16 + 4 + encryptedMessage.length + hmac.length];
        System.arraycopy(salt, 0, combined, 0, 16);
        System.arraycopy(intToBytes(iterations), 0, combined, 16, 4);
        System.arraycopy(encryptedMessage, 0, combined, 20, encryptedMessage.length);
        System.arraycopy(hmac, 0, combined, 20 + encryptedMessage.length, hmac.length);

        return Base64.getUrlEncoder().encode(combined);
    }

    // Function to decrypt a token with a given password
    public static byte[] decrypt(byte[] token, String password) throws Exception {
        byte[] decoded = Base64.getUrlDecoder().decode(token);
        byte[] salt = new byte[16];
        System.arraycopy(decoded, 0, salt, 0, 16);
        int iterations = bytesToInt(decoded, 16, 20);
        byte[] encryptedMessageAndHmac = new byte[decoded.length - 20];
        System.arraycopy(decoded, 20, encryptedMessageAndHmac, 0, encryptedMessageAndHmac.length);

        byte[] key = deriveKey(password.getBytes(), salt, iterations);

        // Create a Fernet-like encryption using AES and HMAC
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
        SecretKey secretKey = new javax.crypto.spec.SecretKeySpec(key, "AES");
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedMessage = new byte[encryptedMessageAndHmac.length - 32];
        System.arraycopy(encryptedMessageAndHmac, 0, encryptedMessage, 0, encryptedMessage.length);
        byte[] hmac = new byte[32];
        System.arraycopy(encryptedMessageAndHmac, encryptedMessage.length, hmac, 0, hmac.length);

        // Verify HMAC
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] expectedHmac = mac.doFinal(encryptedMessage);
        if (!java.util.Arrays.equals(hmac, expectedHmac)) {
            throw new Exception("HMAC verification failed");
        }

        return cipher.doFinal(encryptedMessage);
    }

    // Helper function to convert int to bytes
    private static byte[] intToBytes(int value) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) ((value >> 24) & 0xFF);
        bytes[1] = (byte) ((value >> 16) & 0xFF);
        bytes[2] = (byte) ((value >> 8) & 0xFF);
        bytes[3] = (byte) (value & 0xFF);
        return bytes;
    }

    // Helper function to convert bytes to int
    private static int bytesToInt(byte[] bytes, int offset, int length) {
        int value = 0;
        for (int i = 0; i < length; i++) {
            value = (value << 8) | (bytes[offset + i] & 0xFF);
        }
        return value;
    }

    public static void main(String[] args) throws Exception {
        byte[] token = encrypt("nice123", "password123", 100000);
        System.out.println(Base64.getEncoder().encodeToString(token));
        System.out.println(new String(decrypt(token, "password123")));
    }
}