import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class AESEncryption {
    
    public static String generateSecretKeyForAESCipher() {
        // AES key length must be either 16, 24, or 32 bytes long
        int AES_key_length = 16; // use larger value in production
        // generate a random secret key with the decided key length
        // this secret key will be used to create AES cipher for encryption/decryption
        byte[] secret_key = new byte[AES_key_length];
        new SecureRandom().nextBytes(secret_key);
        // encode this secret key for storing safely in database
        String encoded_secret_key = Base64.getEncoder().encodeToString(secret_key);
        return encoded_secret_key;
    }
    
    // Function 1
    public static String encryptMessage(String private_msg, String encoded_secret_key, String padding_character) {
        try {
            // decode the encoded secret key
            byte[] secret_key = Base64.getDecoder().decode(encoded_secret_key);
            // use the decoded secret key to create a AES cipher
            SecretKeySpec keySpec = new SecretKeySpec(secret_key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            
            // pad the private_msg
            // because AES encryption requires the length of the msg to be a multiple of 16
            int paddingLength = (16 - private_msg.length()) % 16;
            String padded_private_msg = private_msg + padding_character.repeat(paddingLength);
            
            // use the cipher to encrypt the padded message
            byte[] encrypted_msg = cipher.doFinal(padded_private_msg.getBytes(StandardCharsets.UTF_8));
            // encode the encrypted msg for storing safely in the database
            String encoded_encrypted_msg = Base64.getEncoder().encodeToString(encrypted_msg);
            // return encoded encrypted message
            return encoded_encrypted_msg;
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }
    
    // Function 2
    public static String decryptMessage(String encoded_encrypted_msg, String encoded_secret_key, String padding_character) {
        try {
            // decode the encoded encrypted message and encoded secret key
            byte[] secret_key = Base64.getDecoder().decode(encoded_secret_key);
            byte[] encrypted_msg = Base64.getDecoder().decode(encoded_encrypted_msg);
            
            // use the decoded secret key to create a AES cipher
            SecretKeySpec keySpec = new SecretKeySpec(secret_key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            
            // use the cipher to decrypt the encrypted message
            byte[] decrypted_msg_bytes = cipher.doFinal(encrypted_msg);
            // convert decrypted message into a string
            String decrypted_msg = new String(decrypted_msg_bytes, StandardCharsets.UTF_8);
            
            // unpad the encrypted message
            String unpadded_private_msg = decrypted_msg.replaceAll(padding_character + "+$", "");
            // return a decrypted original private message
            return unpadded_private_msg;
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
    
    public static void main(String[] args) {
        ///// BEGIN HERE /////
        
        String private_msg = "\n My test string\n";
        String padding_character = "{";
        
        String secret_key = generateSecretKeyForAESCipher();
        String encrypted_msg = encryptMessage(private_msg, secret_key, padding_character);
        String decrypted_msg = decryptMessage(encrypted_msg, secret_key, padding_character);
        
        System.out.printf("   Secret Key: %s - (%d)%n", secret_key, secret_key.length());
        System.out.printf("Encrypted Msg: %s - (%d)%n", encrypted_msg, encrypted_msg.length());
        System.out.printf("Decrypted Msg: %s - (%d)%n", decrypted_msg, decrypted_msg.length());
    }
}