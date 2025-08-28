import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AesEncryption {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 16;
    private static final int GCM_NONCE_LENGTH = 12;

    public static byte[] encrypt(byte[] key, byte[] message) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        byte[] nonce = new byte[GCM_NONCE_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(nonce);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, spec);
        byte[] ciphertext = cipher.doFinal(message);
        byte[] result = new byte[nonce.length + ciphertext.length];
        System.arraycopy(nonce, 0, result, 0, nonce.length);
        System.arraycopy(ciphertext, 0, result, nonce.length, ciphertext.length);
        return result;
    }

    public static byte[] decrypt(byte[] key, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        byte[] nonce = new byte[GCM_NONCE_LENGTH];
        System.arraycopy(encrypted, 0, nonce, 0, nonce.length);
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, spec);
        return cipher.doFinal(encrypted, nonce.length, encrypted.length - nonce.length);
    }

    public static void main(String[] args) {
        try {
            byte[] key = "Sixteen byte key".getBytes(); // 16 bytes key
            byte[] message = "message".getBytes();

            byte[] encrypted = encrypt(key, message);
            System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));

            byte[] decrypted = decrypt(key, encrypted);
            System.out.println("Decrypted: " + new String(decrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}