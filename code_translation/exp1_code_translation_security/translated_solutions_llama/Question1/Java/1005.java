import com.google.crypto.tink.AeadKeyTemplates;
import com.google.crypto.tink.Keyset;
import com.google.crypto.tink.Tink;
import com.google.crypto.tink.aead.Aead;
import com.google.crypto.tink.aead.AeadConfig;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public class Main {

    public static void main(String[] args) throws GeneralSecurityException {
        // Initialize Tink
        Tink.init(AeadConfig.create());

        // Generate a key
        Keyset keyset = Keyset.generateNew(AeadKeyTemplates.AES256_GCM);

        // Get the keyset handle
        byte[] key = keyset.getEncryptedKeyset().getKeyset().toByteArray();

        // Message to encrypt
        String msg = "a much longer message with punctuation!";
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);

        // Encrypt the message
        byte[] encrypted = encrypt(msgBytes, key);

        // Decrypt the message
        byte[] decrypted = decrypt(encrypted, key);

        // Print the results
        System.out.println(java.util.Base64.getEncoder().encodeToString(encrypted));
        System.out.println(new String(decrypted, StandardCharsets.UTF_8));
    }

    /**
     * Encrypts a message using the provided key.
     *
     * @param msg  the message to encrypt
     * @param key  the key to use for encryption
     * @return     the encrypted message
     * @throws GeneralSecurityException if an error occurs during encryption
     */
    public static byte[] encrypt(byte[] msg, byte[] key) throws GeneralSecurityException {
        // Create a keyset handle from the provided key
        Keyset keyset = Keyset.readNoSecret(key);

        // Get the aead instance
        Aead aead = keyset.getPrimitive(Aead.class);

        // Encrypt the message
        return aead.encrypt(msg, new byte[0]);
    }

    /**
     * Decrypts a message using the provided key.
     *
     * @param msg  the message to decrypt
     * @param key  the key to use for decryption
     * @return     the decrypted message
     * @throws GeneralSecurityException if an error occurs during decryption
     */
    public static byte[] decrypt(byte[] msg, byte[] key) throws GeneralSecurityException {
        // Create a keyset handle from the provided key
        Keyset keyset = Keyset.readNoSecret(key);

        // Get the aead instance
        Aead aead = keyset.getPrimitive(Aead.class);

        // Decrypt the message
        return aead.decrypt(msg, new byte[0]);
    }
}