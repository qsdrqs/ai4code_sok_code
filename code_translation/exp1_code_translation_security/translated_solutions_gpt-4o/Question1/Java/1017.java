import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class AESExample {

    private static final int AES_KEY_SIZE = 128; // AES key size in bits
    private static final int GCM_NONCE_LENGTH = 12; // GCM nonce length in bytes
    private static final int GCM_TAG_LENGTH = 16; // GCM tag length in bytes

    public static byte[][] encrypt(byte[] key, byte[] plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        byte[] nonce = new byte[GCM_NONCE_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(nonce);

        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);

        byte[] ciphertext = cipher.doFinal(plaintext);
        byte[] tag = Arrays.copyOfRange(ciphertext, ciphertext.length - GCM_TAG_LENGTH, ciphertext.length);
        byte[] encryptedData = Arrays.copyOfRange(ciphertext, 0, ciphertext.length - GCM_TAG_LENGTH);

        return new byte[][]{nonce, tag, encryptedData};
    }

    public static byte[] decrypt(byte[] key, byte[][] ciphertext) throws Exception {
        byte[] nonce = ciphertext[0];
        byte[] tag = ciphertext[1];
        byte[] encryptedData = ciphertext[2];

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);

        byte[] ciphertextWithTag = new byte[encryptedData.length + tag.length];
        System.arraycopy(encryptedData, 0, ciphertextWithTag, 0, encryptedData.length);
        System.arraycopy(tag, 0, ciphertextWithTag, encryptedData.length, tag.length);

        return cipher.doFinal(ciphertextWithTag);
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AES_KEY_SIZE);
        SecretKey key = keyGen.generateKey();

        String plaintext = "Hello, World!";
        byte[] plaintextBytes = plaintext.getBytes();

        byte[][] encrypted = encrypt(key.getEncoded(), plaintextBytes);
        byte[] decrypted = decrypt(key.getEncoded(), encrypted);

        System.out.println("Original: " + plaintext);
        System.out.println("Decrypted: " + new String(decrypted));
    }
}