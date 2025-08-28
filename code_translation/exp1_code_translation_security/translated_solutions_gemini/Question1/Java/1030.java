import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * This is a Java translation of a Python AES encryption/decryption script.
 * It uses standard Java libraries for cryptography and encoding.
 *
 * Inspired from: https://pythonprogramming.net/encryption-and-decryption-in-python-code-example-with-explanation/
 */
public class AesEncryptionExample {

    // AES uses a block size of 16 bytes.
    private static final int AES_BLOCK_SIZE = 16;
    // The encryption algorithm and mode.
    // NOTE: ECB mode is not secure and should not be used in production.
    // This is chosen to be a faithful translation of the original Python code,
    // which uses AES.new() with its default ECB mode.
    // "NoPadding" is used because we implement manual padding.
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/NoPadding";

    /**
     * Generates a random secret key for AES encryption.
     * @return A Base64 encoded secret key.
     */
    public static String generateSecretKeyForAESCipher() {
        // AES key length must be either 16, 24, or 32 bytes long.
        int aesKeyLength = 16; // Use a larger value like 32 in production for AES-256.
        
        // Generate a random secret key with the decided key length.
        SecureRandom secureRandom = new SecureRandom();
        byte[] secretKey = new byte[aesKeyLength];
        secureRandom.nextBytes(secretKey);

        // Encode this secret key using Base64 for safe storage.
        return Base64.getEncoder().encodeToString(secretKey);
    }

    /**
     * Encrypts a message using AES/ECB.
     * @param privateMsg The message to encrypt.
     * @param encodedSecretKey The Base64 encoded secret key.
     * @param paddingCharacter The character to use for padding.
     * @return A Base64 encoded encrypted message.
     * @throws Exception if encryption fails.
     */
    public static String encryptMessage(String privateMsg, String encodedSecretKey, char paddingCharacter) throws Exception {
        // Decode the Base64 encoded secret key.
        byte[] secretKeyBytes = Base64.getDecoder().decode(encodedSecretKey);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, ALGORITHM);

        // Create a Cipher instance for encryption.
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        // Pad the private message.
        // AES encryption requires the length of the message to be a multiple of 16.
        int remainder = privateMsg.length() % AES_BLOCK_SIZE;
        int paddingNeeded = (remainder == 0) ? 0 : AES_BLOCK_SIZE - remainder;

        StringBuilder paddedMsgBuilder = new StringBuilder(privateMsg);
        for (int i = 0; i < paddingNeeded; i++) {
            paddedMsgBuilder.append(paddingCharacter);
        }
        String paddedPrivateMsg = paddedMsgBuilder.toString();

        // Encrypt the padded message.
        byte[] encryptedMsgBytes = cipher.doFinal(paddedPrivateMsg.getBytes(StandardCharsets.UTF_8));

        // Encode the encrypted message in Base64 for safe storage.
        return Base64.getEncoder().encodeToString(encryptedMsgBytes);
    }

    /**
     * Decrypts a message using AES/ECB.
     * @param encodedEncryptedMsg The Base64 encoded encrypted message.
     * @param encodedSecretKey The Base64 encoded secret key.
     * @param paddingCharacter The character used for padding.
     * @return The original, decrypted private message.
     * @throws Exception if decryption fails.
     */
    public static String decryptMessage(String encodedEncryptedMsg, String encodedSecretKey, char paddingCharacter) throws Exception {
        // Decode the Base64 encoded secret key and encrypted message.
        byte[] secretKeyBytes = Base64.getDecoder().decode(encodedSecretKey);
        byte[] encryptedMsgBytes = Base64.getDecoder().decode(encodedEncryptedMsg);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyBytes, ALGORITHM);

        // Create a Cipher instance for decryption.
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        // Decrypt the message.
        byte[] decryptedMsgBytes = cipher.doFinal(encryptedMsgBytes);

        // Convert the decrypted byte array to a string.
        String decryptedMsg = new String(decryptedMsgBytes, StandardCharsets.UTF_8);

        // Unpad the decrypted message by removing trailing padding characters.
        // This replicates Python's rstrip().
        int i = decryptedMsg.length() - 1;
        while (i >= 0 && decryptedMsg.charAt(i) == paddingCharacter) {
            i--;
        }
        return decryptedMsg.substring(0, i + 1);
    }


    public static void main(String[] args) {
        try {
            String privateMsg = "\n My test string\n";
            char paddingCharacter = '{';

            // Generate a secret key.
            String secretKey = generateSecretKeyForAESCipher();

            // Encrypt the message.
            String encryptedMsg = encryptMessage(privateMsg, secretKey, paddingCharacter);

            // Decrypt the message.
            String decryptedMsg = decryptMessage(encryptedMsg, secretKey, paddingCharacter);

            // Print the results.
            System.out.printf("   Secret Key: %s - (%d)%n", secretKey, secretKey.length());
            System.out.printf("Encrypted Msg: %s - (%d)%n", encryptedMsg, encryptedMsg.length());
            System.out.printf("Decrypted Msg: %s - (%d)%n", decryptedMsg, decryptedMsg.length());

        } catch (Exception e) {
            System.err.println("An error occurred during the encryption/decryption process.");
            e.printStackTrace();
        }
    }
}