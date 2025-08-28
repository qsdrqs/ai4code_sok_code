import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Base64;

public class SymmetricEncryption {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Encrypt using a symmetric key
     */
    public static String encrypt(String message, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encMessage = cipher.doFinal(message.getBytes());
        return Base64.getEncoder().encodeToString(encMessage);
    }

    /**
     * Decrypt using a symmetric key
     */
    public static String decrypt(String encMessage, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decMessage = cipher.doFinal(Base64.getDecoder().decode(encMessage));
        return new String(decMessage);
    }

    public static void main(String[] args) {
        try {
            String message = "the solutions are here somewhere";
            System.out.println("Secret Message: " + message);

            // Generate a symmetric key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // You can use 192 or 256 bits as well
            SecretKey key = keyGen.generateKey();

            // Encrypt the message
            String encMessage = encrypt(message, key);
            System.out.println("Encrypted Message: " + encMessage);

            // Decrypt the message
            String decMessage = decrypt(encMessage, key);
            System.out.println("Decrypted Message: " + decMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}