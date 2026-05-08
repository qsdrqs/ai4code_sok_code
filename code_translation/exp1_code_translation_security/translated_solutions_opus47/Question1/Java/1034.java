import org.bouncycastle.crypto.generators.SCrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class Main {
    private static final int SALT_LENGTH = 16;
    private static final int NONCE_LENGTH = 16;   // PyCryptodome default GCM nonce size
    private static final int GCM_TAG_LENGTH = 16; // 128 bits
    private static final int KEY_LENGTH = 32;     // 256-bit key
    private static final int SCRYPT_N = 16384;    // 2^14
    private static final int SCRYPT_R = 8;
    private static final int SCRYPT_P = 1;

    public static String encodeStr(String plainText, String key) throws Exception {
        return encrypt(plainText, key);
    }

    public static String decodeStr(String strEncoded, String key) throws Exception {
        return decrypt(strEncoded, key);
    }

    private static String encrypt(String message, String password) throws Exception {
        // Generate random salt
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        secureRandom.nextBytes(salt);

        // Derive private key via Scrypt
        byte[] privateKey = SCrypt.generate(
                password.getBytes(StandardCharsets.UTF_8),
                salt, SCRYPT_N, SCRYPT_R, SCRYPT_P, KEY_LENGTH
        );

        // Generate random nonce
        byte[] nonce = new byte[NONCE_LENGTH];
        secureRandom.nextBytes(nonce);

        // Set up AES-GCM cipher
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(privateKey, "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);

        byte[] encrypted = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));

        // Java appends the tag at the end of the ciphertext; split them apart
        int cipherTextLength = encrypted.length - GCM_TAG_LENGTH;
        byte[] cipherText = new byte[cipherTextLength];
        byte[] tag = new byte[GCM_TAG_LENGTH];
        System.arraycopy(encrypted, 0, cipherText, 0, cipherTextLength);
        System.arraycopy(encrypted, cipherTextLength, tag, 0, GCM_TAG_LENGTH);

        // Encode each value in Base64, join with '*' (same format as cryptocode)
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(cipherText) + "*"
             + encoder.encodeToString(salt) + "*"
             + encoder.encodeToString(nonce) + "*"
             + encoder.encodeToString(tag);
    }

    private static String decrypt(String encData, String password) throws Exception {
        // Parse the joined string
        String[] parts = encData.split("\\*");
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] cipherText = decoder.decode(parts[0]);
        byte[] salt       = decoder.decode(parts[1]);
        byte[] nonce      = decoder.decode(parts[2]);
        byte[] tag        = decoder.decode(parts[3]);

        // Re-derive the key from the password + salt
        byte[] privateKey = SCrypt.generate(
                password.getBytes(StandardCharsets.UTF_8),
                salt, SCRYPT_N, SCRYPT_R, SCRYPT_P, KEY_LENGTH
        );

        // Java expects ciphertext + tag concatenated
        byte[] cipherTextWithTag = new byte[cipherText.length + tag.length];
        System.arraycopy(cipherText, 0, cipherTextWithTag, 0, cipherText.length);
        System.arraycopy(tag, 0, cipherTextWithTag, cipherText.length, tag.length);

        // Decrypt
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(privateKey, "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, nonce);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);

        byte[] decrypted = cipher.doFinal(cipherTextWithTag);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        String strEncoded1 = encodeStr("I am okay", "wow");
        System.out.println(decodeStr(strEncoded1, "wow"));
    }
}