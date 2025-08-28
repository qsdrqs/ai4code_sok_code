import com.google.crypto.tink.AeadKeyTemplates;
import com.google.crypto.tink.Keyset;
import com.google.crypto.tink.Tink;
import com.google.crypto.tink.aead.AeadSivKey;
import com.google.crypto.tink.aead.AeadSivPrimitive;
import com.google.crypto.tink.subtle.Base64;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public class Main {

    /**
     * Encrypts a message using a provided key.
     *
     * @param message The message to be encrypted.
     * @param key     The key used for encryption.
     * @return The encrypted message.
     * @throws GeneralSecurityException If an error occurs during encryption.
     */
    public static byte[] encrypt(String message, byte[] key) throws GeneralSecurityException {
        // Initialize Tink
        Tink.init();

        // Create a keyset from the provided key
        Keyset keyset = Keyset.fromProtoKeysetBytes(key);

        // Get the AeadSiv primitive from the keyset
        AeadSivPrimitive primitive = keyset.getPrimitive(AeadSivPrimitive.class);

        // Encrypt the message
        byte[] encryptedMessage = primitive.encrypt(message.getBytes(StandardCharsets.UTF_8), new byte[0]);

        return encryptedMessage;
    }

    /**
     * Decrypts a message using a provided key.
     *
     * @param message The message to be decrypted.
     * @param key     The key used for decryption.
     * @return The decrypted message.
     * @throws GeneralSecurityException If an error occurs during decryption.
     */
    public static String decrypt(byte[] message, byte[] key) throws GeneralSecurityException {
        // Initialize Tink
        Tink.init();

        // Create a keyset from the provided key
        Keyset keyset = Keyset.fromProtoKeysetBytes(key);

        // Get the AeadSiv primitive from the keyset
        AeadSivPrimitive primitive = keyset.getPrimitive(AeadSivPrimitive.class);

        // Decrypt the message
        byte[] decryptedMessage = primitive.decrypt(message, new byte[0]);

        return new String(decryptedMessage, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws GeneralSecurityException {
        // Generate a key (for demonstration purposes only)
        AeadSivKey key = AeadKeyTemplates.AES256_SIV.generateKey();
        byte[] keyBytes = key.toProtoKeysetBytes();

        String message = "Hello, World!";
        byte[] encryptedMessage = encrypt(message, keyBytes);
        System.out.println("Encrypted Message: " + Base64.encode(encryptedMessage));

        String decryptedMessage = decrypt(encryptedMessage, keyBytes);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}