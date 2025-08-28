import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main {

    // Function to generate a secret key for AES cipher
    public static String generateSecretKeyForAESCipher() throws NoSuchAlgorithmException {
        // AES key length must be either 16, 24, or 32 bytes long
        int aesKeyLength = 128; // use larger value in production (192 or 256)

        // Generate a random secret key with the decided key length
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(aesKeyLength);
        SecretKey secretKey = keyGen.generateKey();

        // Encode this secret key for storing safely in database
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // Function 1: Encrypt a message
    public static String encryptMessage(String privateMsg, String encodedSecretKey, char paddingCharacter) throws Exception {
        // Decode the encoded secret key
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), "AES");

        // Pad the private_msg
        // because AES encryption requires the length of the msg to be a multiple of 16
        int paddingLength = 16 - (privateMsg.length() % 16);
        StringBuilder paddedPrivateMsg = new StringBuilder(privateMsg);
        for (int i = 0; i < paddingLength; i++) {
            paddedPrivateMsg.append(paddingCharacter);
        }

        // Use the cipher to encrypt the padded message
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMsg = cipher.doFinal(paddedPrivateMsg.toString().getBytes(StandardCharsets.UTF_8));

        // Encode the encrypted msg for storing safely in the database
        return Base64.getEncoder().encodeToString(encryptedMsg);
    }

    // Function 2: Decrypt a message
    public static String decryptMessage(String encodedEncryptedMsg, String encodedSecretKey, char paddingCharacter) throws Exception {
        // Decode the encoded encrypted message and encoded secret key
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(encodedSecretKey), "AES");
        byte[] encryptedMsg = Base64.getDecoder().decode(encodedEncryptedMsg);

        // Use the cipher to decrypt the encrypted message
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedMsg = cipher.doFinal(encryptedMsg);

        // Convert decrypted message into a string
        String decryptedMsgStr = new String(decryptedMsg, StandardCharsets.UTF_8);

        // Unpad the encrypted message
        return decryptedMsgStr.replaceAll("[" + paddingCharacter + "]$", "");
    }

    public static void main(String[] args) throws Exception {
        String privateMsg = """
 My test string
""";
        char paddingCharacter = '{';

        String secretKey = generateSecretKeyForAESCipher();
        String encryptedMsg = encryptMessage(privateMsg, secretKey, paddingCharacter);
        String decryptedMsg = decryptMessage(encryptedMsg, secretKey, paddingCharacter);

        System.out.println("   Secret Key: " + secretKey + " - (" + secretKey.length() + ")");
        System.out.println("Encrypted Msg: " + encryptedMsg + " - (" + encryptedMsg.length() + ")");
        System.out.println("Decrypted Msg: " + decryptedMsg + " - (" + decryptedMsg.length() + ")");
    }
}