import com.google.crypto.tink.AeadKeyTemplates;
import com.google.crypto.tink.Keyset;
import com.google.crypto.tink.Tink;
import com.google.crypto.tink.aead.AeadSivKey;
import com.google.crypto.tink.aead.AeadSivKeyManager;
import com.google.crypto.tink.aead.PrecomputedAead;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

public class Main {

    /**
     * Encrypts the given file using the provided key.
     *
     * @param inputFile  the file to be encrypted
     * @param outputFile the file where the encrypted data will be written
     * @param key        the key used for encryption
     * @throws GeneralSecurityException if there's an issue with the key or encryption
     * @throws IOException              if there's an issue reading or writing files
     */
    public static void encrypt(String inputFile, String outputFile, byte[] key) throws GeneralSecurityException, IOException {
        // Initialize Tink
        Tink.init();

        // Create a PrecomputedAead instance
        PrecomputedAead aead = AeadSivKeyManager.createAeadSivKey(key).getAead();

        // Read the input file
        byte[] data = Files.readAllBytes(Paths.get(inputFile));

        // Encrypt the data
        byte[] encrypted = aead.encrypt(data, new byte[0]);

        // Write the encrypted data to the output file
        Files.write(Paths.get(outputFile), encrypted);
    }

    /**
     * Decrypts the given file using the provided key.
     *
     * @param inputFile  the file to be decrypted
     * @param outputFile the file where the decrypted data will be written
     * @param key        the key used for decryption
     * @throws GeneralSecurityException if there's an issue with the key or decryption
     * @throws IOException              if there's an issue reading or writing files
     */
    public static void decrypt(String inputFile, String outputFile, byte[] key) throws GeneralSecurityException, IOException {
        // Initialize Tink
        Tink.init();

        // Create a PrecomputedAead instance
        PrecomputedAead aead = AeadSivKeyManager.createAeadSivKey(key).getAead();

        // Read the input file
        byte[] data = Files.readAllBytes(Paths.get(inputFile));

        // Decrypt the data
        byte[] decrypted = aead.decrypt(data, new byte[0]);

        // Write the decrypted data to the output file
        Files.write(Paths.get(outputFile), decrypted);
    }

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        // Generate a new key
        Keyset keyset = Keyset.generate(AeadKeyTemplates.AES256_SIV);
        byte[] key = keyset.getKeysetBytes();

        // Encrypt a file
        String inputFile = "/safedir/test.txt";
        String encryptedFile = "/safedir/test.txt.enc";
        encrypt(inputFile, encryptedFile, key);

        // Decrypt the file
        String decryptedFile = "/safedir/test.txt.dec";
        decrypt(encryptedFile, decryptedFile, key);
    }
}