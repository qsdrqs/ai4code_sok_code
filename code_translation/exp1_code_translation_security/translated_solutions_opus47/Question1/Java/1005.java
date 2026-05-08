import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;

public class FernetExample {

    // Fernet token version byte
    private static final byte VERSION = (byte) 0x80;

    public static byte[] encrypt(byte[] msg, byte[] key) throws Exception {
        // Decode the URL-safe base64 key into 32 raw bytes
        byte[] keyBytes = Base64.getUrlDecoder().decode(key);
        byte[] signingKey = Arrays.copyOfRange(keyBytes, 0, 16);
        byte[] encryptionKey = Arrays.copyOfRange(keyBytes, 16, 32);

        // Generate random 16-byte IV
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);

        // Current timestamp (seconds since epoch)
        long timestamp = Instant.now().getEpochSecond();

        // AES-128-CBC with PKCS7 padding (PKCS5Padding in Java == PKCS7 for AES)
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(encryptionKey, "AES"),
                    new IvParameterSpec(iv));
        byte[] ciphertext = cipher.doFinal(msg);

        // Assemble: Version (1) || Timestamp (8) || IV (16) || Ciphertext (N)
        ByteBuffer buffer = ByteBuffer.allocate(1 + 8 + 16 + ciphertext.length);
        buffer.put(VERSION);
        buffer.putLong(timestamp);
        buffer.put(iv);
        buffer.put(ciphertext);
        byte[] unsignedToken = buffer.array();

        // Compute HMAC-SHA256 over the unsigned portion
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(signingKey, "HmacSHA256"));
        byte[] hmac = mac.doFinal(unsignedToken);

        // Append HMAC
        byte[] token = new byte[unsignedToken.length + hmac.length];
        System.arraycopy(unsignedToken, 0, token, 0, unsignedToken.length);
        System.arraycopy(hmac, 0, token, unsignedToken.length, hmac.length);

        // URL-safe base64 encode
        return Base64.getUrlEncoder().encode(token);
    }

    public static byte[] decrypt(byte[] token, byte[] key) throws Exception {
        // Decode the URL-safe base64 key
        byte[] keyBytes = Base64.getUrlDecoder().decode(key);
        byte[] signingKey = Arrays.copyOfRange(keyBytes, 0, 16);
        byte[] encryptionKey = Arrays.copyOfRange(keyBytes, 16, 32);

        // Decode token
        byte[] decodedToken = Base64.getUrlDecoder().decode(token);

        // Verify version
        if (decodedToken[0] != VERSION) {
            throw new Exception("Invalid token version");
        }

        // Split token into unsigned portion and HMAC
        byte[] hmac = Arrays.copyOfRange(decodedToken, decodedToken.length - 32, decodedToken.length);
        byte[] unsignedToken = Arrays.copyOfRange(decodedToken, 0, decodedToken.length - 32);

        // Verify HMAC (constant-time comparison)
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(signingKey, "HmacSHA256"));
        byte[] computedHmac = mac.doFinal(unsignedToken);
        if (!MessageDigest.isEqual(hmac, computedHmac)) {
            throw new Exception("HMAC verification failed");
        }

        // Extract IV (after 1-byte version + 8-byte timestamp = offset 9) and ciphertext
        byte[] iv = Arrays.copyOfRange(decodedToken, 9, 25);
        byte[] ciphertext = Arrays.copyOfRange(decodedToken, 25, decodedToken.length - 32);

        // Decrypt
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,
                    new SecretKeySpec(encryptionKey, "AES"),
                    new IvParameterSpec(iv));
        return cipher.doFinal(ciphertext);
    }

    public static byte[] generateKey() {
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        return Base64.getUrlEncoder().encode(key);
    }

    public static void main(String[] args) throws Exception {
        byte[] key = generateKey();
        byte[] msg = "a much longer message with punctuation!".getBytes(StandardCharsets.UTF_8);
        byte[] e = encrypt(msg, key);
        byte[] d = decrypt(e, key);

        System.out.println(new String(e, StandardCharsets.UTF_8));
        System.out.println(new String(d, StandardCharsets.UTF_8));
    }
}