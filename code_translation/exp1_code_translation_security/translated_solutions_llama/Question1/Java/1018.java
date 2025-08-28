import com.google.crypto.tink.AeadKeyTemplates;
import com.google.crypto.tink.Keyset;
import com.google.crypto.tink.Tink;
import com.google.crypto.tink.aead.Aead;
import com.google.crypto.tink.aead.AeadConfig;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public class Main {

    /**
     * Generates a key for encryption and decryption.
     * 
     * @return The generated key.
     * @throws GeneralSecurityException If an error occurs during key generation.
     */
    public static byte[] generateKey() throws GeneralSecurityException {
        AeadConfig.register();
        Keyset keyset = Keyset.generateNew(AeadKeyTemplates.AES256_GCM);
        return keyset.getEncryptedKeyset().getKeyset().toByteArray();
    }

    /**
     * Encrypts the given input using the provided key.
     * 
     * @param input The input to be encrypted.
     * @param key   The key used for encryption.
     * @return The encrypted input.
     * @throws GeneralSecurityException If an error occurs during encryption.
     */
    public static byte[] encrypt(String input, byte[] key) throws GeneralSecurityException {
        AeadConfig.register();
        Keyset keyset = Keyset.readNoSecret(key);
        Aead aead = keyset.getPrimitive(Aead.class);
        return aead.encrypt(input.getBytes(StandardCharsets.UTF_8), new byte[0]);
    }

    /**
     * Decrypts the given input using the provided key.
     * 
     * @param input The input to be decrypted.
     * @param key   The key used for decryption.
     * @return The decrypted input.
     * @throws GeneralSecurityException If an error occurs during decryption.
     */
    public static String decrypt(byte[] input, byte[] key) throws GeneralSecurityException {
        AeadConfig.register();
        Keyset keyset = Keyset.readNoSecret(key);
        Aead aead = keyset.getPrimitive(Aead.class);
        byte[] decrypted = aead.decrypt(input, new byte[0]);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws GeneralSecurityException {
        // Generate a key
        byte[] key = generateKey();

        // Input to be encrypted
        String input = "Hello, World!";

        // Encrypt the input
        byte[] encrypted = encrypt(input, key);
        System.out.println("Encrypted: " + java.util.Base64.getEncoder().encodeToString(encrypted));

        // Decrypt the input
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
    }
}