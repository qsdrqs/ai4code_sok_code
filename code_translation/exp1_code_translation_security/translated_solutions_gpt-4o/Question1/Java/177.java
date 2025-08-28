import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESCipher {

    private static final String AES = "AES";
    private static final String AES_MODE = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 16; // 128 bits
    private static final int NONCE_LENGTH = 12; // 96 bits

    public static byte[][] encrypt(byte[] message, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_MODE);
        byte[] nonce = new byte[NONCE_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(nonce);

        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

        byte[] cipherText = cipher.doFinal(message);
        return new byte[][]{cipherText, nonce};
    }

    public static byte[] decrypt(byte[][] cipherTextAndNonce, SecretKey secretKey) throws Exception {
        byte[] cipherText = cipherTextAndNonce[0];
        byte[] nonce = cipherTextAndNonce[1];

        Cipher cipher = Cipher.getInstance(AES_MODE);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);

        return cipher.doFinal(cipherText);
    }

    public static void main(String[] args) {
        try {
            // Example usage
            KeyGenerator keyGen = KeyGenerator.getInstance(AES);
            keyGen.init(256); // AES-256
            SecretKey secretKey = keyGen.generateKey();

            String message = "Hello, World!";
            byte[] messageBytes = message.getBytes();

            byte[][] encrypted = encrypt(messageBytes, secretKey);
            byte[] decrypted = decrypt(encrypted, secretKey);

            System.out.println("Original: " + message);
            System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted[0]));
            System.out.println("Nonce: " + Base64.getEncoder().encodeToString(encrypted[1]));
            System.out.println("Decrypted: " + new String(decrypted));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}