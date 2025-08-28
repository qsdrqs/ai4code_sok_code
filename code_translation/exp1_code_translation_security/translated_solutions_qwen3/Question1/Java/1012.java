import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class FernetEncryption {

    // Generate a 32-byte key and encode it in URL-safe Base64
    private static final String keyString = generateKey();
    private static final byte[] keyBytes = Base64.getUrlDecoder().decode(keyString);
    private static final byte[] aesKeyBytes = Arrays.copyOfRange(keyBytes, 0, 16);
    private static final byte[] hmacKeyBytes = Arrays.copyOfRange(keyBytes, 16, 32);

    public static String encryptText(String plainText) throws Exception {
        // Timestamp in seconds since epoch, 8 bytes, big-endian
        long timestamp = System.currentTimeMillis() / 1000;
        byte[] timestampBytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            timestampBytes[i] = (byte) (timestamp >> (56 - i * 8));
        }

        // Generate IV
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);

        // Encrypt plaintext
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] ciphertext = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // Create signing input
        byte[] version = new byte[]{(byte) 0x80};
        ByteArrayOutputStream signInput = new ByteArrayOutputStream();
        signInput.write(version);
        signInput.write(timestampBytes);
        signInput.write(iv);
        signInput.write(ciphertext);
        byte[] signingInput = signInput.toByteArray();

        // Compute HMAC
        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec hmacKeySpec = new SecretKeySpec(hmacKeyBytes, "HmacSHA256");
        hmac.init(hmacKeySpec);
        byte[] hmacSignature = hmac.doFinal(signingInput);

        // Combine into token
        ByteArrayOutputStream tokenStream = new ByteArrayOutputStream();
        tokenStream.write(signingInput);
        tokenStream.write(hmacSignature);
        byte[] tokenBytes = tokenStream.toByteArray();

        // Base64 URL-safe encode
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    public static String decryptText(String encryptedText) throws Exception {
        // Decode token
        byte[] tokenBytes = Base64.getUrlDecoder().decode(encryptedText);

        // Check token length
        if (tokenBytes.length < 73) {
            throw new IllegalArgumentException("Invalid token length");
        }

        // Parse version
        byte version = tokenBytes[0];
        if (version != (byte) 0x80) {
            throw new IllegalArgumentException("Unsupported version");
        }

        // Extract timestamp (8 bytes)
        byte[] timestampBytes = new byte[8];
        System.arraycopy(tokenBytes, 1, timestampBytes, 0, 8);

        // Extract IV (16 bytes)
        byte[] iv = new byte[16];
        System.arraycopy(tokenBytes, 9, iv, 0, 16);

        // Extract ciphertext: from 25 to (length - 32)
        int ciphertextStart = 25;
        int ciphertextLength = tokenBytes.length - ciphertextStart - 32;
        if (ciphertextLength < 16) {
            throw new IllegalArgumentException("Invalid ciphertext length");
        }
        byte[] ciphertext = new byte[ciphertextLength];
        System.arraycopy(tokenBytes, ciphertextStart, ciphertext, 0, ciphertextLength);

        // Extract HMAC (last 32 bytes)
        byte[] hmacSignature = new byte[32];
        System.arraycopy(tokenBytes, tokenBytes.length - 32, hmacSignature, 0, 32);

        // Recompute HMAC
        ByteArrayOutputStream signInput = new ByteArrayOutputStream();
        signInput.write(version);
        signInput.write(timestampBytes);
        signInput.write(iv);
        signInput.write(ciphertext);
        byte[] signingInput = signInput.toByteArray();

        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec hmacKeySpec = new SecretKeySpec(hmacKeyBytes, "HmacSHA256");
        hmac.init(hmacKeySpec);
        byte[] newHmacSignature = hmac.doFinal(signingInput);

        // Compare HMACs
        if (!Arrays.equals(hmacSignature, newHmacSignature)) {
            throw new IllegalArgumentException("HMAC verification failed");
        }

        // Decrypt ciphertext
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(aesKeyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(ciphertext);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private static String generateKey() {
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(key);
    }

    public static void main(String[] args) throws Exception {
        String original = "Hello, world!";
        String encrypted = encryptText(original);
        String decrypted = decryptText(encrypted);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}