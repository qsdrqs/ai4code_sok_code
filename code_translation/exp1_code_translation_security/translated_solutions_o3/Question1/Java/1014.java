import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/**
 * Functions that one encrypts and the other decrypts a given string
 * using a given symmetric key – Java version of the referenced Python code.
 */
public class AESCipher {

    /* identical to the Python member ‘self.bs = 32’ */
    private static final int BS = 32;

    /* SHA-256 hash of the textual password → 256-bit AES key */
    private final byte[] key;

    public AESCipher(String password) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            this.key = sha.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Unable to initialize cipher", e);
        }
    }

    /* ------------------------------------------------------------------ */
    /* ---------------------------- ENCRYPT ------------------------------ */
    /* ------------------------------------------------------------------ */
    public String encrypt(String raw) {
        try {
            byte[] padded = pad(raw.getBytes(StandardCharsets.UTF_8));

            byte[] iv = new byte[16];                    // AES block size = 16
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE,
                        new SecretKeySpec(key, "AES"),
                        new IvParameterSpec(iv));

            byte[] ciphertext = cipher.doFinal(padded);

            /* prepend IV exactly like Python version */
            byte[] ivPlusCipher = new byte[iv.length + ciphertext.length];
            System.arraycopy(iv, 0, ivPlusCipher, 0, iv.length);
            System.arraycopy(ciphertext, 0, ivPlusCipher, iv.length, ciphertext.length);

            return Base64.getEncoder().encodeToString(ivPlusCipher);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    /* ------------------------------------------------------------------ */
    /* ---------------------------- DECRYPT ------------------------------ */
    /* ------------------------------------------------------------------ */
    public String decrypt(String enc) {
        try {
            byte[] ivPlusCipher = Base64.getDecoder().decode(enc);

            byte[] iv         = Arrays.copyOfRange(ivPlusCipher, 0, 16);
            byte[] ciphertext = Arrays.copyOfRange(ivPlusCipher, 16, ivPlusCipher.length);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE,
                        new SecretKeySpec(key, "AES"),
                        new IvParameterSpec(iv));

            byte[] paddedPlain = cipher.doFinal(ciphertext);
            byte[] plain       = unpad(paddedPlain);

            return new String(plain, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }

    /* ------------------------------------------------------------------ */
    /* --------------------------- PADDING ------------------------------- */
    /* ------------------------------------------------------------------ */
    private static byte[] pad(byte[] bytes) {
        int padding = BS - (bytes.length % BS);
        byte padByte = (byte) padding;

        byte[] padded = Arrays.copyOf(bytes, bytes.length + padding);
        Arrays.fill(padded, bytes.length, padded.length, padByte);
        return padded;
    }

    private static byte[] unpad(byte[] bytes) {
        int padLen = bytes[bytes.length - 1] & 0xFF;     // unsigned
        return Arrays.copyOfRange(bytes, 0, bytes.length - padLen);
    }

    /* ------------------------------------------------------------------ */
    /* --------------------------- TEST MAIN ----------------------------- */
    /* ------------------------------------------------------------------ */
    public static void main(String[] args) {
        String key = "mysecretpassword";
        AESCipher cipher = new AESCipher(key);

        String encrypted = cipher.encrypt("Secret Message A");
        String decrypted = cipher.decrypt(encrypted);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}