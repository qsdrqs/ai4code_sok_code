import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class SymmetricEncryption {

    private SecretKey secretKey;

    /**
     * Generate a secret key for symmetric encryption.
     *
     * @throws NoSuchAlgorithmException if the algorithm is not available.
     */
    public void generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 128-bit key
        secretKey = keyGen.generateKey();
    }

    /**
     * Encrypt a message using a symmetric key.
     *
     * @param message the message to be encrypted.
     * @return the encrypted message.
     * @throws Exception if an error occurs during encryption.
     */
    public byte[] encrypt(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decrypt an encrypted message using a symmetric key.
     *
     * @param encryptedMessage the encrypted message.
     * @return the decrypted message.
     * @throws Exception if an error occurs during decryption.
     */
    public String decrypt(byte[] encryptedMessage) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        SymmetricEncryption symmetricEncryption = new SymmetricEncryption();
        symmetricEncryption.generateKey();

        String message = "the solutions are here somewhere";
        System.out.println("Secret Message: " + message);

        byte[] encryptedMessage = symmetricEncryption.encrypt(message);
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encryptedMessage));

        String decryptedMessage = symmetricEncryption.decrypt(encryptedMessage);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}