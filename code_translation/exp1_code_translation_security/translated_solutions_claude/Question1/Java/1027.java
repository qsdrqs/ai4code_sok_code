import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

public class AESEncryption {
    
    private static final int BLOCK_SIZE = 16; // AES block size is 16 bytes
    
    public static byte[] encrypt(String message, byte[] key) throws Exception {
        // convert message to bytes
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        
        // pad message
        byte[] paddedMessage = pad(messageBytes, BLOCK_SIZE);
        
        // create cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        
        // generate random IV
        byte[] iv = new byte[BLOCK_SIZE];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        
        // encrypt message
        byte[] ciphertext = cipher.encrypt(paddedMessage);
        
        // prepend IV to ciphertext (common practice)
        byte[] result = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(ciphertext, 0, result, iv.length, ciphertext.length);
        
        return result;
    }
    
    public static String decrypt(byte[] ciphertextWithIv, byte[] key) throws Exception {
        // extract IV from the beginning of ciphertext
        byte[] iv = Arrays.copyOfRange(ciphertextWithIv, 0, BLOCK_SIZE);
        byte[] ciphertext = Arrays.copyOfRange(ciphertextWithIv, BLOCK_SIZE, ciphertextWithIv.length);
        
        // create cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        
        // decrypt message
        byte[] decryptedBytes = cipher.decrypt(ciphertext);
        
        // unpad message
        byte[] unpaddedMessage = unpad(decryptedBytes, BLOCK_SIZE);
        
        // convert message to string
        String message = new String(unpaddedMessage, StandardCharsets.UTF_8);
        
        return message;
    }
    
    // PKCS7 padding implementation
    private static byte[] pad(byte[] data, int blockSize) {
        int paddingLength = blockSize - (data.length % blockSize);
        byte[] paddedData = new byte[data.length + paddingLength];
        System.arraycopy(data, 0, paddedData, 0, data.length);
        
        // Fill padding bytes with the padding length value
        for (int i = data.length; i < paddedData.length; i++) {
            paddedData[i] = (byte) paddingLength;
        }
        
        return paddedData;
    }
    
    // PKCS7 unpadding implementation
    private static byte[] unpad(byte[] paddedData, int blockSize) {
        int paddingLength = paddedData[paddedData.length - 1] & 0xFF;
        
        // Validate padding
        if (paddingLength < 1 || paddingLength > blockSize) {
            throw new IllegalArgumentException("Invalid padding");
        }
        
        // Check if all padding bytes are correct
        for (int i = paddedData.length - paddingLength; i < paddedData.length; i++) {
            if ((paddedData[i] & 0xFF) != paddingLength) {
                throw new IllegalArgumentException("Invalid padding");
            }
        }
        
        return Arrays.copyOfRange(paddedData, 0, paddedData.length - paddingLength);
    }
}