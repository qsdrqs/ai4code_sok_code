import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class FernetEncryption {

    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 100_000;

    public static void main(String[] args) throws Exception {
        byte[] token = encrypt("nice123", "password123");
        System.out.println(new String(token));
        String decrypted = decrypt(token, "password123");
        System.out.println(decrypted);
    }

    private static String deriveKey(String password, byte[] salt, int iterations) throws Exception {
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        char[] passwordChars = new char[passwordBytes.length];
        for (int i = 0; i < passwordBytes.length; i++) {
            passwordChars[i] = (char) (passwordBytes[i] & 0xFF);
        }

        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec keySpec = new PBEKeySpec(passwordChars, salt, iterations, 256);
        SecretKey secretKey = skf.generateSecret(keySpec);
        byte[] derivedKey = secretKey.getEncoded();
        return Base64.getUrlEncoder().encodeToString(derivedKey);
    }

    public static byte[] encrypt(String message, String password) throws Exception {
        byte[] data = message.getBytes(StandardCharsets.UTF_8);
        byte[] salt = new byte[SALT_LENGTH];
        new SecureRandom().nextBytes(salt);

        String key = deriveKey(password, salt, ITERATIONS);

        byte[] encryptedData = fernetEncrypt(data, key);

        byte[] iterationsBytes = new byte[] {
            (byte) ((ITERATIONS >> 24) & 0xFF),
            (byte) ((ITERATIONS >> 16) & 0xFF),
            (byte) ((ITERATIONS >> 8) & 0xFF),
            (byte) (ITERATIONS & 0xFF)
        };

        byte[] combined = new byte[salt.length + iterationsBytes.length + encryptedData.length];
        System.arraycopy(salt, 0, combined, 0, salt.length);
        System.arraycopy(iterationsBytes, 0, combined, salt.length, iterationsBytes.length);
        System.arraycopy(encryptedData, 0, combined, salt.length + iterationsBytes.length, encryptedData.length);

        return Base64.getUrlEncoder().encode(combined);
    }

    private static byte[] fernetEncrypt(byte[] data, String keyStr) throws Exception {
        byte[] key = Base64.getUrlDecoder().decode(keyStr);
        byte[] encKey = Arrays.copyOfRange(key, 0, 16);
        byte[] sigKey = Arrays.copyOfRange(key, 16, 32);

        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);

        int blockSize = 16;
        int padLength = blockSize - (data.length % blockSize);
        byte[] paddedData = new byte[data.length + padLength];
        System.arraycopy(data, 0, paddedData, 0, data.length);
        Arrays.fill(paddedData, data.length, paddedData.length, (byte) padLength);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encKey, "AES"), new IvParameterSpec(iv));
        byte[] cipherText = cipher.doFinal(paddedData);

        Mac hmac = Mac.getInstance("HmacSHA256");
        hmac.init(new SecretKeySpec(sigKey, "HmacSHA256"));
        hmac.update((byte) 0x00); // version
        hmac.update(iv);
        hmac.update(cipherText);
        byte[] hmacSig = hmac.digest();

        byte[] encrypted = new byte[1 + iv.length + cipherText.length + hmacSig.length];
        encrypted[0] = 0x00;
        int offset = 1;
        System.arraycopy(iv, 0, encrypted, offset, iv.length);
        offset += iv.length;
        System.arraycopy(cipherText, 0, encrypted, offset, cipherText.length);
        offset += cipherText.length;
        System.arraycopy(hmacSig, 0, encrypted, offset, hmacSig.length);

        return Base64.getUrlEncoder().encode(encrypted);
    }

    public static String decrypt(byte[] token64, String password) throws Exception {
        byte[] decoded = Base64.getUrlDecoder().decode(token64);

        byte[] salt = new byte[SALT_LENGTH];
        System.arraycopy(decoded, 0, salt, 0, SALT_LENGTH);

        int iterations = ((decoded[16] & 0xFF) << 24) |
                         ((decoded[17] & 0xFF) << 16) |
                         ((decoded[18] & 0xFF) << 8) |
                         (decoded[19] & 0xFF);

        byte[] encryptedData = new byte[decoded.length - 20];
        System.arraycopy(decoded, 20, encryptedData, 0, encryptedData.length);

        String key = deriveKey(password, salt, iterations);

        String encryptedDataBase64 = Base64.getUrlEncoder().encodeToString(encryptedData);
        byte[] decrypted = fernetDecrypt(encryptedDataBase64.getBytes(StandardCharsets.UTF_8), key);

        return new String(decrypted, StandardCharsets.UTF_8);
    }

    private static byte[] fernetDecrypt(byte[] token, String keyStr) throws Exception {
        byte[] decoded = Base64.getUrlDecoder().decode(token);
        if (decoded.length < 1) {
            throw new IllegalArgumentException("Invalid token");
        }

        byte version = decoded[0];
        if (version != 0x00) {
            throw new IllegalArgumentException("Unsupported version");
        }

        byte[] key = Base64.getUrlDecoder().decode(keyStr);
        byte[] encKey = Arrays.copyOfRange(key, 0, 16);
        byte[] sigKey = Arrays.copyOfRange(key, 16, 32);

        byte[] hmacInput = new byte[decoded.length - 32];
        System.arraycopy(decoded, 0, hmacInput, 0, hmacInput.length);
        byte[] expectedSig = new byte[32];
        System.arraycopy(decoded, decoded.length - 32, expectedSig, 0, 32);

        Mac hmac = Mac.getInstance("HmacSHA256");
        hmac.init(new SecretKeySpec(sigKey, "HmacSHA256"));
        hmac.update(hmacInput);
        byte[] actualSig = hmac.doFinal();

        if (!MessageDigest.isEqual(expectedSig, actualSig)) {
            throw new IllegalArgumentException("Invalid signature");
        }

        byte[] iv = new byte[16];
        System.arraycopy(decoded, 1, iv, 0, 16);
        byte[] cipherText = new byte[decoded.length - 17 - 32];
        System.arraycopy(decoded, 1 + 16, cipherText, 0, cipherText.length);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(encKey, "AES"), new IvParameterSpec(iv));
        byte[] paddedData = cipher.doFinal(cipherText);

        int padLength = paddedData[paddedData.length - 1];
        byte[] result = new byte[paddedData.length - padLength];
        System.arraycopy(paddedData, 0, result, 0, result.length);

        return result;
    }
}