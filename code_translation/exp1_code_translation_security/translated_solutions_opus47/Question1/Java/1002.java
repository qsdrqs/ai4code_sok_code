import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AesCbcExample {

    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(data);
    }

    public static void test() throws Exception {
        byte[] data = "a secret message".getBytes("UTF-8");
        SecureRandom random = new SecureRandom();

        byte[] key = new byte[32];
        random.nextBytes(key);

        byte[] iv = new byte[16];
        random.nextBytes(iv);

        byte[] e = encrypt(data, key, iv);
        byte[] d = decrypt(e, key, iv);

        assert Arrays.equals(d, data);
    }

    public static void main(String[] args) throws Exception {
        test();
        System.out.println("Test passed!");
    }
}