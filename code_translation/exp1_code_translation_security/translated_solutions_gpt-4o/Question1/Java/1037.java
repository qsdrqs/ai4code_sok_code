import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class FernetExample {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int KEY_SIZE = 256;
    private static final int T_LEN = 128;

    public static byte[] encrypt(byte[] input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
        byte[] iv = new byte[12];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] encrypted = cipher.doFinal(input);
        byte[] encryptedWithIv = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, encryptedWithIv, 0, iv.length);
        System.arraycopy(encrypted, 0, encryptedWithIv, iv.length, encrypted.length);
        return encryptedWithIv;
    }

    public static byte[] decrypt(byte[] input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
        byte[] iv = new byte[12];
        System.arraycopy(input, 0, iv, 0, iv.length);
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        return cipher.doFinal(input, iv.length, input.length - iv.length);
    }

    public static void main(String[] args) {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(KEY_SIZE);
            SecretKey key = keyGen.generateKey();

            byte[] input = "super secret".getBytes();
            byte[] encryptedInput = encrypt(input, key);
            byte[] decryptedInput = decrypt(encryptedInput, key);

            System.out.println("Original: " + new String(input));
            System.out.println("Decrypted: " + new String(decryptedInput));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}