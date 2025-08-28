import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKeyFactory;
import java.security.SecureRandom;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.Arrays;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class FernetEncryption {
    
    private static byte[] deriveKey(byte[] password, byte[] salt, int iterations) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(
            new String(password, StandardCharsets.UTF_8).toCharArray(), 
            salt, 
            iterations, 
            256
        );
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] key = factory.generateSecret(spec).getEncoded();
        return Base64.getUrlEncoder().encode(key);
    }
    
    public static byte[] encrypt(String message, String password, int iterations) throws Exception {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        
        // Generate random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        
        byte[] key = deriveKey(password.getBytes(StandardCharsets.UTF_8), salt, iterations);
        byte[] encryptedMessage = fernetEncrypt(messageBytes, key);
        
        // Convert iterations to 4-byte big-endian
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(iterations);
        byte[] iterBytes = buffer.array();
        
        // Combine salt + iterations + encrypted message
        ByteBuffer combined = ByteBuffer.allocate(salt.length + iterBytes.length + encryptedMessage.length);
        combined.put(salt);
        combined.put(iterBytes);
        combined.put(encryptedMessage);
        
        return Base64.getUrlEncoder().encode(combined.array());
    }
    
    public static byte[] decrypt(byte[] token, String password) throws Exception {
        byte[] decoded = Base64.getUrlDecoder().decode(token);
        
        // Extract components
        byte[] salt = Arrays.copyOfRange(decoded, 0, 16);
        byte[] iterBytes = Arrays.copyOfRange(decoded, 16, 20);
        byte[] encryptedData = Arrays.copyOfRange(decoded, 20, decoded.length);
        
        // Convert iterations from big-endian bytes
        ByteBuffer buffer = ByteBuffer.wrap(iterBytes);
        int iterations = buffer.getInt();
        
        byte[] key = deriveKey(password.getBytes(StandardCharsets.UTF_8), salt, iterations);
        byte[] tokenForDecrypt = Base64.getUrlEncoder().encode(encryptedData);
        
        return fernetDecrypt(tokenForDecrypt, key);
    }
    
    private static byte[] fernetEncrypt(byte[] data, byte[] key) throws Exception {
        // Decode the base64 key
        byte[] decodedKey = Base64.getUrlDecoder().decode(key);
        
        // Split key into encryption key (first 16 bytes) and signing key (last 16 bytes)
        byte[] encryptionKey = Arrays.copyOfRange(decodedKey, 0, 16);
        byte[] signingKey = Arrays.copyOfRange(decodedKey, 16, 32);
        
        // Generate random IV
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        
        // Encrypt data using AES-128-CBC
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(data);
        
        // Get current timestamp
        long timestamp = System.currentTimeMillis() / 1000;
        ByteBuffer timestampBuffer = ByteBuffer.allocate(8);
        timestampBuffer.putLong(timestamp);
        byte[] timestampBytes = timestampBuffer.array();
        
        // Create token: version (1 byte) + timestamp (8 bytes) + iv (16 bytes) + encrypted data
        ByteBuffer tokenBuffer = ByteBuffer.allocate(1 + 8 + 16 + encrypted.length);
        tokenBuffer.put((byte) 0x80); // Fernet version
        tokenBuffer.put(timestampBytes);
        tokenBuffer.put(iv);
        tokenBuffer.put(encrypted);
        byte[] tokenWithoutHmac = tokenBuffer.array();
        
        // Calculate HMAC
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec signingKeySpec = new SecretKeySpec(signingKey, "HmacSHA256");
        mac.init(signingKeySpec);
        byte[] hmac = mac.doFinal(tokenWithoutHmac);
        
        // Final token: tokenWithoutHmac + hmac
        ByteBuffer finalToken = ByteBuffer.allocate(tokenWithoutHmac.length + hmac.length);
        finalToken.put(tokenWithoutHmac);
        finalToken.put(hmac);
        
        return finalToken.array();
    }
    
    private static byte[] fernetDecrypt(byte[] token, byte[] key) throws Exception {
        // Decode the base64 key and token
        byte[] decodedKey = Base64.getUrlDecoder().decode(key);
        byte[] decodedToken = Base64.getUrlDecoder().decode(token);
        
        // Split key into encryption key and signing key
        byte[] encryptionKey = Arrays.copyOfRange(decodedKey, 0, 16);
        byte[] signingKey = Arrays.copyOfRange(decodedKey, 16, 32);
        
        // Extract HMAC (last 32 bytes)
        byte[] hmac = Arrays.copyOfRange(decodedToken, decodedToken.length - 32, decodedToken.length);
        byte[] tokenWithoutHmac = Arrays.copyOfRange(decodedToken, 0, decodedToken.length - 32);
        
        // Verify HMAC
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec signingKeySpec = new SecretKeySpec(signingKey, "HmacSHA256");
        mac.init(signingKeySpec);
        byte[] calculatedHmac = mac.doFinal(tokenWithoutHmac);
        
        if (!Arrays.equals(hmac, calculatedHmac)) {
            throw new SecurityException("Invalid token");
        }
        
        // Extract components
        byte version = tokenWithoutHmac[0];
        if (version != (byte) 0x80) {
            throw new IllegalArgumentException("Invalid Fernet version");
        }
        
        byte[] timestampBytes = Arrays.copyOfRange(tokenWithoutHmac, 1, 9);
        byte[] iv = Arrays.copyOfRange(tokenWithoutHmac, 9, 25);
        byte[] encrypted = Arrays.copyOfRange(tokenWithoutHmac, 25, tokenWithoutHmac.length);
        
        // Decrypt data
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        
        return cipher.doFinal(encrypted);
    }
    
    public static void main(String[] args) throws Exception {
        byte[] token = encrypt("nice123", "password123", 100000);
        System.out.println(new String(token, StandardCharsets.UTF_8));
        
        byte[] decrypted = decrypt(token, "password123");
        System.out.println(new String(decrypted, StandardCharsets.UTF_8));
    }
}