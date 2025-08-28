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

    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 256;
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;

    public static byte[] encrypt(byte[] msg, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
        byte[] iv = new byte[IV_LENGTH_BYTE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encrypted = cipher.doFinal(msg);
        byte[] encryptedWithIv = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, encryptedWithIv, 0, iv.length);
        System.arraycopy(encrypted, 0, encryptedWithIv, iv.length, encrypted.length);
        return encryptedWithIv;
    }

    public static byte[] decrypt(byte[] encryptedMsg, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
        byte[] iv = new byte[IV_LENGTH_BYTE];
        System.arraycopy(encryptedMsg, 0, iv, 0, iv.length);
        GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        return cipher.doFinal(encryptedMsg, iv.length, encryptedMsg.length - iv.length);
    }

    public static void main(String[] args) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(KEY_SIZE);
            SecretKey key = keyGen.generateKey();

            String message = "a much longer message with punctuation!";
            byte[] msgBytes = message.getBytes();

            byte[] encrypted = encrypt(msgBytes, key);
            byte[] decrypted = decrypt(encrypted, key);

            System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
            System.out.println("Decrypted: " + new String(decrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}