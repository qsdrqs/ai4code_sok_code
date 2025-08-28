import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {

    // Encrypt plaintext using AES with CBC mode
    public static String encrypt(byte[] key, String plaintext) throws Exception {
        // Generate a random IV (16 bytes)
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        // Create AES cipher in CBC mode with the given key and IV
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);

        // Encrypt the plaintext
        byte[] paddedPlaintext = pad(plaintext.getBytes());
        byte[] cipherText = cipher.doFinal(paddedPlaintext);

        // Concatenate IV and ciphertext, then encode in Base64
        byte[] ivAndCipherText = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, ivAndCipherText, 0, iv.length);
        System.arraycopy(cipherText, 0, ivAndCipherText, iv.length, cipherText.length);

        return Base64.getEncoder().encodeToString(ivAndCipherText);
    }

    // Decrypt ciphertext using AES with CBC mode
    public static String decrypt(byte[] key, String ciphertext) throws Exception {
        // Decode Base64-encoded ciphertext
        byte[] ivAndCipherText = Base64.getDecoder().decode(ciphertext);

        // Extract IV and ciphertext
        byte[] iv = new byte[16];
        byte[] cipherText = new byte[ivAndCipherText.length - 16];
        System.arraycopy(ivAndCipherText, 0, iv, 0, 16);
        System.arraycopy(ivAndCipherText, 16, cipherText, 0, cipherText.length);

        // Create AES cipher in CBC mode with the given key and IV
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);

        // Decrypt the ciphertext
        byte[] decryptedBytes = cipher.doFinal(cipherText);

        // Unpad the plaintext and return as a string
        return new String(unpad(decryptedBytes));
    }

    // Pad the plaintext to a multiple of 16 bytes
    private static byte[] pad(byte[] data) {
        int blockSize = 16;
        int paddingLength = blockSize - (data.length % blockSize);
        byte[] paddedData = new byte[data.length + paddingLength];
        System.arraycopy(data, 0, paddedData, 0, data.length);
        for (int i = data.length; i < paddedData.length; i++) {
            paddedData[i] = (byte) paddingLength;
        }
        return paddedData;
    }

    // Unpad the plaintext by removing padding bytes
    private static byte[] unpad(byte[] data) {
        int paddingLength = data[data.length - 1];
        byte[] unpaddedData = new byte[data.length - paddingLength];
        System.arraycopy(data, 0, unpaddedData, 0, unpaddedData.length);
        return unpaddedData;
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        String plaintext = "Hello, World!";
        byte[] key = new byte[16]; // 128-bit key
        SecureRandom random = new SecureRandom();
        random.nextBytes(key);

        // Encrypt
        String encrypted = encrypt(key, plaintext);
        System.out.println("Encrypted: " + encrypted);

        // Decrypt
        String decrypted = decrypt(key, encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}