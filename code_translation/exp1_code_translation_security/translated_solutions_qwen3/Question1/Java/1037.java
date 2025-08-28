import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class Fernet {

    public static void main(String[] args) throws Exception {
        byte[] key = generateKey();
        byte[] input = "super secret".getBytes(StandardCharsets.UTF_8);
        byte[] encryptedInput = encrypt(input, key);
        byte[] decryptedInput = decrypt(encryptedInput, key);

        System.out.println(new String(input, StandardCharsets.UTF_8));
        System.out.println(new String(decryptedInput, StandardCharsets.UTF_8));
    }

    public static byte[] generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32];
        random.nextBytes(key);
        return key;
    }

    public static byte[] encrypt(byte[] input, byte[] key) throws Exception {
        if (key.length != 32) {
            throw new IllegalArgumentException("Key must be 32 bytes");
        }

        byte[] encryptionKey = new byte[16];
        byte[] signingKey = new byte[16];
        System.arraycopy(key, 0, encryptionKey, 0, 16);
        System.arraycopy(key, 16, signingKey, 0, 16);

        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);

        long timestamp = System.currentTimeMillis() / 1000L;
        byte[] version = new byte[] { (byte) 0x80 };
        byte[] timestampBytes = longToBytes(timestamp);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] ciphertext = cipher.doFinal(input);

        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec signingKeySpec = new SecretKeySpec(signingKey, "HmacSHA256");
        hmac.init(signingKeySpec);
        hmac.update(version);
        hmac.update(timestampBytes);
        hmac.update(iv);
        hmac.update(ciphertext);
        byte[] signature = hmac.doFinal();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(version);
        outputStream.write(timestampBytes);
        outputStream.write(iv);
        outputStream.write(ciphertext);
        outputStream.write(signature);

        return Base64.getUrlEncoder().withoutPadding().encode(outputStream.toByteArray());
    }

    public static byte[] decrypt(byte[] encryptedData, byte[] key) throws Exception {
        byte[] data = Base64.getUrlDecoder().decode(encryptedData);

        if (data[0] != (byte) 0x80) {
            throw new IllegalArgumentException("Invalid version");
        }

        byte[] timestampBytes = new byte[8];
        System.arraycopy(data, 1, timestampBytes, 0, 8);
        long timestamp = bytesToLong(timestampBytes);

        byte[] iv = new byte[16];
        System.arraycopy(data, 9, iv, 0, 16);

        int ciphertextStart = 25;
        int ciphertextLength = data.length - ciphertextStart - 32;
        if (ciphertextLength < 0) {
            throw new IllegalArgumentException("Invalid data length");
        }

        byte[] ciphertext = new byte[ciphertextLength];
        System.arraycopy(data, ciphertextStart, ciphertext, 0, ciphertextLength);

        byte[] signature = new byte[32];
        System.arraycopy(data, data.length - 32, signature, 0, 32);

        byte[] encryptionKey = new byte[16];
        byte[] signingKey = new byte[16];
        System.arraycopy(key, 0, encryptionKey, 0, 16);
        System.arraycopy(key, 16, signingKey, 0, 16);

        Mac hmac = Mac.getInstance("HmacSHA256");
        SecretKeySpec signingKeySpec = new SecretKeySpec(signingKey, "HmacSHA256");
        hmac.init(signingKeySpec);
        hmac.update(data, 0, ciphertextStart + ciphertextLength);
        byte[] expectedSignature = hmac.doFinal();

        if (!MessageDigest.isEqual(expectedSignature, signature)) {
            throw new IllegalArgumentException("Invalid signature");
        }

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(ciphertext);
    }

    private static byte[] longToBytes(long value) {
        byte[] bytes = new byte[8];
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) (value >>> (56 - i * 8));
        }
        return bytes;
    }

    private static long bytesToLong(byte[] bytes) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) bytes[i] & 0xFF) << (56 - i * 8);
        }
        return value;
    }
}