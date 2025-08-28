import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
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

    // Function to generate a random nonce
    public static byte[] generateNonce(int nonceSize) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] nonce = new byte[nonceSize];
        secureRandom.nextBytes(nonce);
        return nonce;
    }

    // Function to encode a string
    public static byte[][] encodeString(String message, SecretKey key, byte[] nonce) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(128, nonce);
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);
        byte[] mBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] ciphertext = cipher.doFinal(mBytes);
        return new byte[][]{ciphertext, cipher.getIV()};
    }

    // Function to decode a string
    public static String decodeString(byte[][] p, SecretKey key, byte[] nonce) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(128, nonce);
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);
        return new String(cipher.doFinal(p[0]), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        String originalMessage = "secret data";
        int keySize = 128; // Key size in bits
        int nonceSize = 12; // Nonce size in bytes

        // Generate a random key and nonce
        SecretKey key = generateKey(keySize);
        byte[] nonce = generateNonce(nonceSize);

        System.out.println("Original Message: " + originalMessage);

        // Encode the string
        byte[][] ciphertext = encodeString(originalMessage, key, nonce);

        // Decode the string
        String decodedString = decodeString(ciphertext, key, nonce);

        System.out.println("Decoded String: " + decodedString);
    }
}