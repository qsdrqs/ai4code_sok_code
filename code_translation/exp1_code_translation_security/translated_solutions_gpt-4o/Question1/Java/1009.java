import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

public class AESCipherExample {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] encodeString(String message, byte[] key, byte[] nonce) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, nonce);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, gcmSpec);

        byte[] mBytes = message.getBytes();
        byte[] ciphertext = cipher.doFinal(mBytes);
        byte[] tag = Arrays.copyOfRange(ciphertext, ciphertext.length - 16, ciphertext.length);
        byte[] encryptedData = Arrays.copyOfRange(ciphertext, 0, ciphertext.length - 16);

        return concatenate(encryptedData, tag);
    }

    public static String decodeString(byte[] p, byte[] key, byte[] nonce) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, nonce);
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);

        byte[] encryptedData = Arrays.copyOfRange(p, 0, p.length - 16);
        byte[] tag = Arrays.copyOfRange(p, p.length - 16, p.length);

        byte[] ciphertext = concatenate(encryptedData, tag);
        byte[] data = cipher.doFinal(ciphertext);

        return new String(data);
    }

    private static byte[] concatenate(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    public static void main(String[] args) throws Exception {
        String originalMessage = "secret data";

        SecureRandom random = new SecureRandom();
        byte[] nonce = new byte[16];
        random.nextBytes(nonce);

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        byte[] key = secretKey.getEncoded();

        System.out.println("Original Message: " + originalMessage);
        byte[] ciphertext = encodeString(originalMessage, key, nonce);
        String decodedString = decodeString(ciphertext, key, nonce);
        System.out.println("Decoded Message: " + decodedString);
    }
}