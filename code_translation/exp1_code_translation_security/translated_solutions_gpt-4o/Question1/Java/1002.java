import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class AESCipher {

    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(data);
    }

    public static void test() throws Exception {
        byte[] data = "a secret message".getBytes();
        byte[] key = new byte[32];
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(key);
        random.nextBytes(iv);

        byte[] encryptedData = encrypt(data, key, iv);
        byte[] decryptedData = decrypt(encryptedData, key, iv);

        if (Arrays.equals(decryptedData, data)) {
            System.out.println("Test passed: Decrypted data matches original data.");
        } else {
            System.out.println("Test failed: Decrypted data does not match original data.");
        }
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}