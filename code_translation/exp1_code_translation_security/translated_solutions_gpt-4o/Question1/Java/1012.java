import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class EncryptionExample {

    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 256;
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;
    private static SecretKey key;

    static {
        Security.addProvider(new BouncyCastleProvider());
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(KEY_SIZE);
            key = keyGen.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptText(String plainText) throws Exception {
        byte[] iv = new byte[IV_LENGTH_BYTE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec parameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

        byte[] encryptedText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
        byte[] encryptedTextWithIv = new byte[iv.length + encryptedText.length];
        System.arraycopy(iv, 0, encryptedTextWithIv, 0, iv.length);
        System.arraycopy(encryptedText, 0, encryptedTextWithIv, iv.length, encryptedText.length);

        return Base64.getEncoder().encodeToString(encryptedTextWithIv);
    }

    public static String decryptText(String encryptedText) throws Exception {
        byte[] encryptedTextWithIv = Base64.getDecoder().decode(encryptedText);
        byte[] iv = new byte[IV_LENGTH_BYTE];
        System.arraycopy(encryptedTextWithIv, 0, iv, 0, iv.length);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec parameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);

        byte[] originalText = cipher.doFinal(encryptedTextWithIv, iv.length, encryptedTextWithIv.length - iv.length);
        return new String(originalText, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            String plainText = "Hello, World!";
            String encryptedText = encryptText(plainText);
            System.out.println("Encrypted: " + encryptedText);

            String decryptedText = decryptText(encryptedText);
            System.out.println("Decrypted: " + decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}