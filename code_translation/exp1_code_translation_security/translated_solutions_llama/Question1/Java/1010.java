import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    // Function to generate a symmetric key
    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        // Generate a random key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 128 bits (16 bytes) key size for AES
        return keyGen.generateKey();
    }

    // Function to encrypt a string using a symmetric key
    public static byte[][] encrypt(SecretKey key, String data) throws Exception {
        // Create a cipher object using the key
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        // Convert the data to bytes
        byte[] dataBytes = data.getBytes();

        // Encrypt the data
        byte[] ciphertext = cipher.doFinal(dataBytes);

        // Get the nonce (IV) and tag
        byte[] nonce = cipher.getIV();
        byte[] tag = cipher.getMac();

        // Combine nonce, tag, and ciphertext
        ByteBuffer byteBuffer = ByteBuffer.allocate(nonce.length + tag.length + ciphertext.length);
        byteBuffer.put(nonce);
        byteBuffer.put(tag);
        byteBuffer.put(ciphertext);

        // Return the combined byte array and the key is not needed as it is already provided
        return new byte[][]{byteBuffer.array(), null};
    }

    // Function to decrypt a string
    public static String decrypt(SecretKey key, byte[] encryptedData) throws Exception {
        // Separate nonce, tag, and ciphertext
        ByteBuffer byteBuffer = ByteBuffer.wrap(encryptedData);
        byte[] nonce = new byte[12]; // GCM mode uses 12-byte IV
        byte[] tag = new byte[16]; // GCM mode uses 128-bit (16-byte) tag
        byte[] ciphertext = new byte[encryptedData.length - nonce.length - tag.length];

        byteBuffer.get(nonce);
        byteBuffer.get(tag);
        byteBuffer.get(ciphertext);

        // Create a cipher object using the key
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(tag.length * 8, nonce);
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);

        // Decrypt the data
        byte[] data = cipher.doFinal(ciphertext);

        // Return the decrypted data
        return new String(data);
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        SecretKey key = generateKey();
        String testData = "Jim's test";
        byte[][] encryptedData = encrypt(key, testData);
        String restoredData = decrypt(key, encryptedData[0]);
        System.out.println(restoredData);
    }
}