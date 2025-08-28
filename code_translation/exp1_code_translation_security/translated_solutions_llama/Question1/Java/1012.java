import com.google.crypto.tink.AeadKeyTemplates;
import com.google.crypto.tink.Keyset;
import com.google.crypto.tink.Tink;
import com.google.crypto.tink.aead.Aead;
import com.google.crypto.tink.aead.AeadConfig;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

public class Main {

    private static final String KEY_TEMPLATE = "AES256_GCM";
    private static Aead aead;

    static {
        try {
            Tink.addModule(AeadConfig.class);
            Keyset keyset = Keyset.readNoSecret(KEY_TEMPLATE);
            aead = keyset.getPrimitive(Aead.class);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encrypts the given plain text.
     *
     * @param plainText The text to be encrypted.
     * @return The encrypted text.
     */
    public static String encryptText(String plainText) {
        try {
            byte[] encryptedText = aead.encrypt(plainText.getBytes(StandardCharsets.UTF_8), null);
            return Base64.getEncoder().encodeToString(encryptedText);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Decrypts the given encrypted text.
     *
     * @param encryptedText The text to be decrypted.
     * @return The decrypted text.
     */
    public static String decryptText(String encryptedText) {
        try {
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = aead.decrypt(encryptedBytes, null);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String plainText = "Hello, World!";
        String encryptedText = encryptText(plainText);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decryptText(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}