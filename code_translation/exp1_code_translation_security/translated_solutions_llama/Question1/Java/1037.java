import com.google.crypto.tink.AeadKeyTemplates;
import com.google.crypto.tink.Keyset;
import com.google.crypto.tink.Tink;
import com.google.crypto.tink.aead.AeadSivKeyManager;
import com.google.crypto.tink.aead.PrecomputedAead;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public class Main {

    public static void main(String[] args) throws GeneralSecurityException {
        // Initialize Tink
        Tink.init();

        // Generate a key
        Keyset keyset = Keyset.generateNew(AeadKeyTemplates.AES256_GCM);

        // Get the keyset handle
        byte[] key = keyset.getEncryptedKeyset().getKeyset().toByteArray();

        // Input to be encrypted
        byte[] input = "super secret".getBytes(StandardCharsets.UTF_8);

        // Encrypt the input
        byte[] encryptedInput = encrypt(input, key);

        // Decrypt the input
        byte[] decryptedInput = decrypt(encryptedInput, key);

        // Print the results
        System.out.println(new String(input, StandardCharsets.UTF_8));
        System.out.println(new String(decryptedInput, StandardCharsets.UTF_8));
    }

    /**
     * Encrypts the given input using the provided key.
     *
     * @param input The input to be encrypted.
     * @param key   The key used for encryption.
     * @return The encrypted input.
     * @throws GeneralSecurityException If an error occurs during encryption.
     */
    public static byte[] encrypt(byte[] input, byte[] key) throws GeneralSecurityException {
        // Create a keyset handle from the key
        Keyset keyset = Keyset.readNoSecret(key);

        // Get the aead primitive
        PrecomputedAead aead = keyset.getPrimitive(PrecomputedAead.class);

        // Encrypt the input
        return aead.encrypt(input, new byte[0]);
    }

    /**
     * Decrypts the given input using the provided key.
     *
     * @param input The input to be decrypted.
     * @param key   The key used for decryption.
     * @return The decrypted input.
     * @throws GeneralSecurityException If an error occurs during decryption.
     */
    public static byte[] decrypt(byte[] input, byte[] key) throws GeneralSecurityException {
        // Create a keyset handle from the key
        Keyset keyset = Keyset.readNoSecret(key);

        // Get the aead primitive
        PrecomputedAead aead = keyset.getPrimitive(PrecomputedAead.class);

        // Decrypt the input
        return aead.decrypt(input, new byte[0]);
    }
}