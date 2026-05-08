import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Functions that one encrypts and the other decrypts a given string using a given symmetric key
 */
public class AESCipher {
    private static final int BS = 32;
    private static final int AES_BLOCK_SIZE = 16;
    private final byte[] key;

    public AESCipher(String key) throws NoSuchAlgorithmException {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        this.key = sha.digest(key.getBytes(StandardCharsets.UTF_8));
    }

    public String encrypt(String raw) throws GeneralSecurityException {
        byte[] padded = pad(raw.getBytes(StandardCharsets.UTF_8));
        byte[] iv = new byte[AES_BLOCK_SIZE];
        new SecureRandom().nextBytes(iv);

        SecretKeySpec keySpec = new SecretKeySpec(this.key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(padded);

        byte[] combined = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    public String decrypt(String enc) throws GeneralSecurityException {
        byte[] encBytes = Base64.getDecoder().decode(enc);
        byte[] iv = Arrays.copyOfRange(encBytes, 0, AES_BLOCK_SIZE);
        byte[] encryptedData = Arrays.copyOfRange(encBytes, AES_BLOCK_SIZE, encBytes.length);

        SecretKeySpec keySpec = new SecretKeySpec(this.key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decrypted = cipher.doFinal(encryptedData);

        return new String(unpad(decrypted), StandardCharsets.UTF_8);
    }

    private byte[] pad(byte[] data) {
        int padLength = BS - data.length % BS;
        byte[] padded = new byte[data.length + padLength];
        System.arraycopy(data, 0, padded, 0, data.length);
        Arrays.fill(padded, data.length, padded.length, (byte) padLength);
        return padded;
    }

    private static byte[] unpad(byte[] data) {
        int padLength = data[data.length - 1] & 0xFF;
        return Arrays.copyOfRange(data, 0, data.length - padLength);
    }

    // public static void main(String[] args) throws GeneralSecurityException {
    //     String key = "mysecretpassword";
    //     AESCipher cipher = new AESCipher(key);
    //     String encrypted = cipher.encrypt("Secret Message A");
    //     String decrypted = cipher.decrypt(encrypted);
    //
    //     System.out.println("Encrypted: " + encrypted);
    //     System.out.println("Decrypted: " + decrypted);
    // }
}