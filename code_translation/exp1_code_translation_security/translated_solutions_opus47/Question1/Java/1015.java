import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Main {

    private static final byte FERNET_VERSION = (byte) 0x80;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static byte[] generateKey() {
        byte[] key = new byte[32];
        SECURE_RANDOM.nextBytes(key);
        return Base64.getUrlEncoder().encode(key);
    }

    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        byte[] decodedKey = Base64.getUrlDecoder().decode(key);
        byte[] signingKey = Arrays.copyOfRange(decodedKey, 0, 16);
        byte[] encryptionKey = Arrays.copyOfRange(decodedKey, 16, 32);

        byte[] iv = new byte[16];
        SECURE_RANDOM.nextBytes(iv);

        long timestamp = Instant.now().getEpochSecond();

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(encryptionKey, "AES"),
                    new IvParameterSpec(iv));
        byte[] ciphertext = cipher.doFinal(data);

        ByteBuffer buffer = ByteBuffer.allocate(1 + 8 + 16 + ciphertext.length);
        buffer.put(FERNET_VERSION);
        buffer.putLong(timestamp);
        buffer.put(iv);
        buffer.put(ciphertext);
        byte[] tokenWithoutHmac = buffer.array();

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(signingKey, "HmacSHA256"));
        byte[] hmac = mac.doFinal(tokenWithoutHmac);

        byte[] token = new byte[tokenWithoutHmac.length + hmac.length];
        System.arraycopy(tokenWithoutHmac, 0, token, 0, tokenWithoutHmac.length);
        System.arraycopy(hmac, 0, token, tokenWithoutHmac.length, hmac.length);

        byte[] encrypted = Base64.getUrlEncoder().encode(token);
        return encrypted;
    }

    public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        byte[] decodedKey = Base64.getUrlDecoder().decode(key);
        byte[] signingKey = Arrays.copyOfRange(decodedKey, 0, 16);
        byte[] encryptionKey = Arrays.copyOfRange(decodedKey, 16, 32);

        byte[] decodedData = Base64.getUrlDecoder().decode(data);

        byte[] hmac = Arrays.copyOfRange(decodedData, decodedData.length - 32, decodedData.length);
        byte[] tokenWithoutHmac = Arrays.copyOfRange(decodedData, 0, decodedData.length - 32);

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(signingKey, "HmacSHA256"));
        byte[] expectedHmac = mac.doFinal(tokenWithoutHmac);

        if (!Arrays.equals(hmac, expectedHmac)) {
            throw new Exception("Invalid HMAC - possible tampering detected");
        }

        byte[] iv = Arrays.copyOfRange(decodedData, 9, 25);
        byte[] ciphertext = Arrays.copyOfRange(decodedData, 25, decodedData.length - 32);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,
                    new SecretKeySpec(encryptionKey, "AES"),
                    new IvParameterSpec(iv));
        byte[] decrypted = cipher.doFinal(ciphertext);

        return decrypted;
    }

    public static void main(String[] args) throws Exception {
        encrypt("/safedir/test.txt".getBytes(), generateKey());
    }
}