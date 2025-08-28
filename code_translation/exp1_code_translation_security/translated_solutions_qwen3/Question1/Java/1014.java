import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class AESCipher {

    private final byte[] key;
    private static final int BLOCK_SIZE = 32; // Same as in Python code

    public AESCipher(String keyString) throws Exception {
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        this.key = sha256.digest(keyString.getBytes(StandardCharsets.UTF_8));
    }

    public String encrypt(String raw) throws Exception {
        byte[] inputBytes = raw.getBytes(StandardCharsets.UTF_8);
        byte[] padded = pad(inputBytes);

        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
        byte[] encrypted = cipher.doFinal(padded);

        byte[] combined = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    public String decrypt(String enc) throws Exception {
        byte[] combined = Base64.getDecoder().decode(enc);
        byte[] iv = new byte[16];
        System.arraycopy(combined, 0, iv, 0, iv.length);
        byte[] ciphertext = Arrays.copyOfRange(combined, iv.length, combined.length);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
        byte[] decrypted = cipher.doFinal(ciphertext);

        byte[] unpadded = unpad(decrypted);
        return new String(unpadded, StandardCharsets.UTF_8);
    }

    private byte[] pad(byte[] input) {
        int paddingLength = BLOCK_SIZE - (input.length % BLOCK_SIZE);
        byte[] padding = new byte[paddingLength];
        Arrays.fill(padding, (byte) paddingLength);

        byte[] padded = new byte[input.length + paddingLength];
        System.arraycopy(input, 0, padded, 0, input.length);
        System.arraycopy(padding, 0, padded, input.length, paddingLength);

        return padded;
    }

    private byte[] unpad(byte[] input) {
        int paddingLength = input[input.length - 1] & 0xFF;
        if (paddingLength < 1 || paddingLength > BLOCK_SIZE) {
            throw new IllegalArgumentException("Invalid padding length");
        }
        return Arrays.copyOf(input, input.length - paddingLength);
    }

    public static void main(String[] args) throws Exception {
        String key = "mysecretpassword";
        AESCipher cipher = new AESCipher(key);

        String encrypted = cipher.encrypt("Secret Message A");
        String decrypted = cipher.decrypt(encrypted);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}