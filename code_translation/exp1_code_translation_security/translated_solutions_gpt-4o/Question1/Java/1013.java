import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

public class EncryptionExample {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE = 256;
    private static final int ITERATIONS = 100000;
    private static final int SALT_SIZE = 16;
    private static final int IV_SIZE = 16;

    public static void main(String[] args) throws Exception {
        String message = "nice123";
        String password = "password123";

        String encrypted = encrypt(message, password);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, password);
        System.out.println("Decrypted: " + decrypted);
    }

    public static String encrypt(String message, String password) throws Exception {
        byte[] salt = generateSalt();
        SecretKey key = deriveKey(password, salt);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        byte[] iv = generateIv();
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());

        byte[] combined = new byte[salt.length + iv.length + encryptedMessage.length];
        System.arraycopy(salt, 0, combined, 0, salt.length);
        System.arraycopy(iv, 0, combined, salt.length, iv.length);
        System.arraycopy(encryptedMessage, 0, combined, salt.length + iv.length, encryptedMessage.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    public static String decrypt(String encrypted, String password) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(encrypted);
        byte[] salt = new byte[SALT_SIZE];
        byte[] iv = new byte[IV_SIZE];
        byte[] encryptedMessage = new byte[decoded.length - SALT_SIZE - IV_SIZE];

        System.arraycopy(decoded, 0, salt, 0, SALT_SIZE);
        System.arraycopy(decoded, SALT_SIZE, iv, 0, IV_SIZE);
        System.arraycopy(decoded, SALT_SIZE + IV_SIZE, encryptedMessage, 0, encryptedMessage.length);

        SecretKey key = deriveKey(password, salt);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        byte[] decryptedMessage = cipher.doFinal(encryptedMessage);

        return new String(decryptedMessage);
    }

    private static SecretKey deriveKey(String password, byte[] salt) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_SIZE);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), ALGORITHM);
    }

    private static byte[] generateSalt() {
        byte[] salt = new byte[SALT_SIZE];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    private static byte[] generateIv() {
        byte[] iv = new byte[IV_SIZE];
        new SecureRandom().nextBytes(iv);
        return iv;
    }
}