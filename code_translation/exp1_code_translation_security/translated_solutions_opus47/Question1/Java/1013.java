import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class FernetTranslation {

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Derive a secret key from a given password and salt
     */
    private static byte[] deriveKey(char[] password, byte[] salt, int iterations) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, 256); // 256 bits = 32 bytes
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] key = factory.generateSecret(spec).getEncoded();
        return Base64.getUrlEncoder().encode(key);
    }

    public static byte[] encrypt(String message, String password) throws Exception {
        return encrypt(message, password, 100_000);
    }

    public static byte[] encrypt(String message, String password, int iterations) throws Exception {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        // Note: matching the original Python code which hardcodes 100_000 here
        // (instead of using the iterations parameter)
        byte[] key = deriveKey(password.toCharArray(), salt, 100_000);

        byte[] fernetTokenEncoded = fernetEncrypt(messageBytes, key);
        byte[] fernetTokenRaw = Base64.getUrlDecoder().decode(fernetTokenEncoded);

        byte[] iterationsBytes = ByteBuffer.allocate(4).putInt(iterations).array(); // big-endian

        byte[] combined = new byte[16 + 4 + fernetTokenRaw.length];
        System.arraycopy(salt, 0, combined, 0, 16);
        System.arraycopy(iterationsBytes, 0, combined, 16, 4);
        System.arraycopy(fernetTokenRaw, 0, combined, 20, fernetTokenRaw.length);

        return Base64.getUrlEncoder().encode(combined);
    }

    public static byte[] decrypt(byte[] token, String password) throws Exception {
        byte[] decoded = Base64.getUrlDecoder().decode(token);
        byte[] salt = new byte[16];
        byte[] iterBytes = new byte[4];
        byte[] tokenBody = new byte[decoded.length - 20];

        System.arraycopy(decoded, 0, salt, 0, 16);
        System.arraycopy(decoded, 16, iterBytes, 0, 4);
        System.arraycopy(decoded, 20, tokenBody, 0, tokenBody.length);

        int iterations = ByteBuffer.wrap(iterBytes).getInt();
        byte[] fernetToken = Base64.getUrlEncoder().encode(tokenBody);

        byte[] key = deriveKey(password.toCharArray(), salt, iterations);
        return fernetDecrypt(fernetToken, key);
    }

    /**
     * Fernet-compatible encryption.
     * Token structure: version(1) | timestamp(8) | IV(16) | ciphertext | HMAC(32)
     * All URL-safe base64 encoded.
     */
    private static byte[] fernetEncrypt(byte[] message, byte[] base64Key) throws Exception {
        byte[] key = Base64.getUrlDecoder().decode(base64Key);
        byte[] signingKey = new byte[16];
        byte[] encryptionKey = new byte[16];
        System.arraycopy(key, 0, signingKey, 0, 16);
        System.arraycopy(key, 16, encryptionKey, 0, 16);

        byte[] iv = new byte[16];
        RANDOM.nextBytes(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); // PKCS5 == PKCS7 for AES
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptionKey, "AES"), new IvParameterSpec(iv));
        byte[] ciphertext = cipher.doFinal(message);

        long timestamp = System.currentTimeMillis() / 1000;
        ByteBuffer buffer = ByteBuffer.allocate(1 + 8 + 16 + ciphertext.length);
        buffer.put((byte) 0x80);
        buffer.putLong(timestamp);
        buffer.put(iv);
        buffer.put(ciphertext);
        byte[] basicParts = buffer.array();

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(signingKey, "HmacSHA256"));
        byte[] hmac = mac.doFinal(basicParts);

        byte[] result = new byte[basicParts.length + hmac.length];
        System.arraycopy(basicParts, 0, result, 0, basicParts.length);
        System.arraycopy(hmac, 0, result, basicParts.length, hmac.length);

        return Base64.getUrlEncoder().encode(result);
    }

    private static byte[] fernetDecrypt(byte[] token, byte[] base64Key) throws Exception {
        byte[] key = Base64.getUrlDecoder().decode(base64Key);
        byte[] signingKey = new byte[16];
        byte[] encryptionKey = new byte[16];
        System.arraycopy(key, 0, signingKey, 0, 16);
        System.arraycopy(key, 16, encryptionKey, 0, 16);

        byte[] decoded = Base64.getUrlDecoder().decode(token);

        if (decoded[0] != (byte) 0x80) {
            throw new Exception("Invalid Fernet version");
        }

        int hmacStart = decoded.length - 32;
        byte[] basicParts = new byte[hmacStart];
        byte[] hmac = new byte[32];
        System.arraycopy(decoded, 0, basicParts, 0, hmacStart);
        System.arraycopy(decoded, hmacStart, hmac, 0, 32);

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(signingKey, "HmacSHA256"));
        byte[] calculatedHmac = mac.doFinal(basicParts);

        if (!MessageDigest.isEqual(hmac, calculatedHmac)) {
            throw new Exception("Invalid HMAC");
        }

        byte[] iv = new byte[16];
        byte[] ciphertext = new byte[basicParts.length - 1 - 8 - 16];
        System.arraycopy(basicParts, 1 + 8, iv, 0, 16);
        System.arraycopy(basicParts, 1 + 8 + 16, ciphertext, 0, ciphertext.length);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(encryptionKey, "AES"), new IvParameterSpec(iv));
        return cipher.doFinal(ciphertext);
    }

    public static void main(String[] args) throws Exception {
        byte[] token = encrypt("nice123", "password123");
        System.out.println(new String(token, StandardCharsets.UTF_8));
        System.out.println(new String(decrypt(token, "password123"), StandardCharsets.UTF_8));
    }
}