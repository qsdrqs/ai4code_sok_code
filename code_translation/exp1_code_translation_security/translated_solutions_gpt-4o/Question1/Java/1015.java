import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Base64;

public class EncryptionExample {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
        return new String(decryptedData);
    }

    public static void main(String[] args) {
        try {
            // Generate a key
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // AES key size
            SecretKey secretKey = keyGen.generateKey();

            // Encrypt data
            String dataToEncrypt = "/safedir/test.txt";
            String encryptedData = encrypt(dataToEncrypt, secretKey);
            System.out.println("Encrypted: " + encryptedData);

            // Decrypt data
            String decryptedData = decrypt(encryptedData, secretKey);
            System.out.println("Decrypted: " + decryptedData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}