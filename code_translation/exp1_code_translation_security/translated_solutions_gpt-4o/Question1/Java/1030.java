import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESCipherExample {

    public static String generateSecretKeyForAESCipher() throws Exception {
        // AES key length must be either 16, 24, or 32 bytes long
        int AESKeyLength = 128; // 128 bits = 16 bytes
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(AESKeyLength);
        SecretKey secretKey = keyGen.generateKey();
        // encode this secret key for storing safely in database
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // Function 1
    public static String encryptMessage(String privateMsg, String encodedSecretKey, char paddingCharacter) throws Exception {
        // decode the encoded secret key
        byte[] decodedKey = Base64.getDecoder().decode(encodedSecretKey);
        SecretKeySpec secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        // use the decoded secret key to create an AES cipher
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // pad the private_msg
        int blockSize = 16;
        int paddingLength = blockSize - (privateMsg.length() % blockSize);
        StringBuilder paddedPrivateMsg = new StringBuilder(privateMsg);
        for (int i = 0; i < paddingLength; i++) {
            paddedPrivateMsg.append(paddingCharacter);
        }
        // use the cipher to encrypt the padded message
        byte[] encryptedMsg = cipher.doFinal(paddedPrivateMsg.toString().getBytes());
        // encode the encrypted msg for storing safely in the database
        return Base64.getEncoder().encodeToString(encryptedMsg);
    }

    // Function 2
    public static String decryptMessage(String encodedEncryptedMsg, String encodedSecretKey, char paddingCharacter) throws Exception {
        // decode the encoded encrypted message and encoded secret key
        byte[] decodedKey = Base64.getDecoder().decode(encodedSecretKey);
        SecretKeySpec secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        byte[] encryptedMsg = Base64.getDecoder().decode(encodedEncryptedMsg);
        // use the decoded secret key to create an AES cipher
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // use the cipher to decrypt the encrypted message
        byte[] decryptedMsgBytes = cipher.doFinal(encryptedMsg);
        String decryptedMsg = new String(decryptedMsgBytes);
        // unpad the encrypted message
        return decryptedMsg.replaceAll(Character.toString(paddingCharacter) + "+$", "");
    }

    public static void main(String[] args) {
        try {
            String privateMsg = " My test string";
            char paddingCharacter = '{';

            String secretKey = generateSecretKeyForAESCipher();
            String encryptedMsg = encryptMessage(privateMsg, secretKey, paddingCharacter);
            String decryptedMsg = decryptMessage(encryptedMsg, secretKey, paddingCharacter);

            System.out.println("   Secret Key: " + secretKey + " - (" + secretKey.length() + ")");
            System.out.println("Encrypted Msg: " + encryptedMsg + " - (" + encryptedMsg.length() + ")");
            System.out.println("Decrypted Msg: " + decryptedMsg + " - (" + decryptedMsg.length() + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}