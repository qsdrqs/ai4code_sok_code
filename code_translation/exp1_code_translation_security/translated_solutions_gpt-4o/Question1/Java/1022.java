import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class EncryptionExample {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final int KEY_SIZE = 256;
    private static final int T_LEN = 128;

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "BC");
        keyGenerator.init(KEY_SIZE);
        return keyGenerator.generateKey();
    }

    public static String encrypt(String input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");
        byte[] iv = new byte[12];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encrypted = cipher.doFinal(input.getBytes());
        byte[] encryptedWithIv = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, encryptedWithIv, 0, iv.length);
        System.arraycopy(encrypted, 0, encryptedWithIv, iv.length, encrypted.length);
        return Base64.getEncoder().encodeToString(encryptedWithIv);
    }

    public static String decrypt(String encrypted, SecretKey key) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(encrypted);
        byte[] iv = new byte[12];
        System.arraycopy(decoded, 0, iv, 0, iv.length);
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN, iv);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] original = cipher.doFinal(decoded, iv.length, decoded.length - iv.length);
        return new String(original);
    }

    public static void main(String[] args) {
        try {
            SecretKey key = generateKey();
            String encrypted = encrypt("Hello World!", key);
            System.out.println("Encrypted: " + encrypted);
            String decrypted = decrypt(encrypted, key);
            System.out.println("Decrypted: " + decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}