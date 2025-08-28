import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    // Function to generate a random key
    public static SecretKey generateKey(int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keySize);
        return keyGen.generateKey();
    }

    // Function to generate a random initialization vector (IV)
    public static byte[] generateIV(int ivSize) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[ivSize];
        secureRandom.nextBytes(iv);
        return iv;
    }

    // Function to encrypt data using AES in CBC mode
    public static byte[] encrypt(byte[] data, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        return cipher.doFinal(data);
    }

    // Function to decrypt data using AES in CBC mode
    public static byte[] decrypt(byte[] encryptedData, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        return cipher.doFinal(encryptedData);
    }

    // Test function
    public static void test() throws Exception {
        String data = "a secret message";
        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

        // Generate a random 256-bit key (32 bytes)
        SecretKey key = generateKey(256);

        // Generate a random 128-bit IV (16 bytes)
        byte[] iv = generateIV(16);

        // Encrypt the data
        byte[] encryptedData = encrypt(dataBytes, key, iv);

        // Decrypt the data
        byte[] decryptedData = decrypt(encryptedData, key, iv);

        // Assert that the decrypted data matches the original data
        assert new String(decryptedData, StandardCharsets.UTF_8).equals(data);
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}