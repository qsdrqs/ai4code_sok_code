import com.macasaet.fernet.Key;
import com.macasaet.fernet.Token;
import com.macasaet.fernet.Validator;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;

/**
 * This class provides methods for Fernet-compatible symmetric encryption and decryption.
 * It is a Java equivalent of the provided Python script.
 */
public class FernetEncryption {

    /**
     * Encrypts a byte array using a Fernet key.
     *
     * @param data The raw data (byte array) to encrypt.
     * @param key  The Fernet key.
     * @return The encrypted data as a byte array (UTF-8 encoded token).
     */
    public static byte[] encrypt(byte[] data, Key key) {
        // The Token class handles the encryption process.
        Token token = Token.generate(key, data);
        // A token is serialized into a URL-safe Base64 string.
        // We return it as bytes to match the Python function's return type.
        return token.serialise().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Decrypts a Fernet token using the corresponding key.
     *
     * @param encryptedData The encrypted token as a byte array.
     * @param key           The Fernet key used for encryption.
     * @return The original decrypted data as a byte array.
     */
    public static byte[] decrypt(byte[] encryptedData, Key key) {
        // Convert the byte array token back to a String.
        String tokenString = new String(encryptedData, StandardCharsets.UTF_8);
        Token token = Token.fromString(tokenString);

        // A Validator is used to decrypt and verify the token.
        // It ensures the token is not expired and the signature is valid.
        Validator<byte[]> validator = (payload) -> payload; // Simple validator that returns the payload as is.

        // Decrypt the token. This will throw an exception if the token is invalid,
        // expired, or the key is incorrect.
        byte[] decrypted = token.validateAndDecrypt(key, validator);
        return decrypted;
    }

    /**
     * Main method to demonstrate the encryption and decryption process.
     */
    public static void main(String[] args) {
        try {
            // --- Setup a dummy file for demonstration ---
            String originalContent = "This is a secret message from the test file.";
            Path filePath = Paths.get("test.txt"); // Using a local file for this example
            Files.write(filePath, originalContent.getBytes(StandardCharsets.UTF_8));
            System.out.println("Created a test file: " + filePath.toAbsolutePath());
            System.out.println("Original content: " + originalContent);
            System.out.println("-------------------------------------------------");

            // 1. Generate a new Fernet key (similar to Fernet.generate_key())
            Key key = Key.generateKey();
            System.out.println("Generated Fernet Key: " + key.serialise());

            // 2. Read the data to be encrypted from the file
            byte[] fileBytes = Files.readAllBytes(filePath);

            // 3. Encrypt the data
            byte[] encryptedData = encrypt(fileBytes, key);
            System.out.println("Encrypted Token (as String): " + new String(encryptedData, StandardCharsets.UTF_8));

            // 4. Decrypt the data to verify
            byte[] decryptedData = decrypt(encryptedData, key);
            String decryptedContent = new String(decryptedData, StandardCharsets.UTF_8);
            System.out.println("Decrypted Content: " + decryptedContent);

            // 5. Verify that the decrypted content matches the original
            if (originalContent.equals(decryptedContent)) {
                System.out.println("\nSuccess! Decrypted content matches the original.");
            } else {
                System.out.println("\nFailure! Decrypted content does not match the original.");
            }

            // --- Clean up the dummy file ---
            Files.delete(filePath);

        } catch (IOException e) {
            System.err.println("Error handling file: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Catches potential crypto-related exceptions from the Fernet library
            System.err.println("An encryption/decryption error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}