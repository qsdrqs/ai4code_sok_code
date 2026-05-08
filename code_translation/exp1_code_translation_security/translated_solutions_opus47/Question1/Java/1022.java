import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class FernetExample {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateKey() {
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        return Base64.getUrlEncoder().encodeToString(keyBytes);
    }

    public static String encrypt(String inp, String key) throws Exception {
        byte[] keyBytes = Base64.getUrlDecoder().decode(key);
        byte[] signingKey = Arrays.copyOfRange(keyBytes, 0, 16);
        byte[] encryptionKey = Arrays.copyOfRange(keyBytes, 16, 32);

        // Generate random 16-byte IV
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);

        // AES-128 CBC with PKCS7 padding
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptionKey, "AES"), new IvParameterSpec(iv));
        byte[] ciphertext = cipher.doFinal(inp.getBytes(StandardCharsets.UTF_8));

        // Build token: version (1) | timestamp (8) | iv (16) | ciphertext
        long timestamp = System.currentTimeMillis() / 1000L;
        ByteBuffer buffer = ByteBuffer.allocate(1 + 8 + 16 + ciphertext.length);
        buffer.put((byte) 0x80);
        buffer.putLong(timestamp);
        buffer.put(iv);
        buffer.put(ciphertext);
        byte[] unsignedToken = buffer.array();

        // Compute HMAC-SHA256 and append
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(signingKey, "HmacSHA256"));
        byte[] hmac = mac.doFinal(unsignedToken);

        byte[] fullToken = new byte[unsignedToken.length + hmac.length];
        System.arraycopy(unsignedToken, 0, fullToken, 0, unsignedToken.length);
        System.arraycopy(hmac, 0, fullToken, unsignedToken.length, hmac.length);

        return Base64.getUrlEncoder().encodeToString(fullToken);
    }

    public static String decrypt(String inp, String key) throws Exception {
        byte[] keyBytes = Base64.getUrlDecoder().decode(key);
        byte[] signingKey = Arrays.copyOfRange(keyBytes, 0, 16);
        byte[] encryptionKey = Arrays.copyOfRange(keyBytes, 16, 32);

        byte[] tokenBytes = Base64.getUrlDecoder().decode(inp);

        // Verify version
        if (tokenBytes[0] != (byte) 0x80) {
            throw new Exception("Invalid version");
        }

        // Verify HMAC
        byte[] hmac = Arrays.copyOfRange(tokenBytes, tokenBytes.length - 32, tokenBytes.length);
        byte[] unsignedToken = Arrays.copyOfRange(tokenBytes, 0, tokenBytes.length - 32);

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(signingKey, "HmacSHA256"));
        byte[] expectedHmac = mac.doFinal(unsignedToken);

        if (!MessageDigest.isEqual(hmac, expectedHmac)) {
            throw new Exception("Invalid HMAC");
        }

        // Extract IV and ciphertext
        byte[] iv = Arrays.copyOfRange(tokenBytes, 9, 25);
        byte[] ciphertext = Arrays.copyOfRange(tokenBytes, 25, tokenBytes.length - 32);

        // Decrypt
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(encryptionKey, "AES"), new IvParameterSpec(iv));
        byte[] plaintext = cipher.doFinal(ciphertext);

        return new String(plaintext, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        String exKey = generateKey();
        String encrypted = encrypt("Hello World!", exKey);
        System.out.println(encrypted);
        String decrypted = decrypt(encrypted, exKey);
        System.out.println(decrypted);
    }
}