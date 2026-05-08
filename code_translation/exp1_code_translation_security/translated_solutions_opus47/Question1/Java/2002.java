import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AESCTR {

    // Where k is 256 bit UTF-8 encoded.
    public static byte[] encrypt(byte[] k, String m) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(k, "AES");
        // pyaes's default Counter() has initial_value=1, which translates to
        // a 16-byte IV of 15 zero bytes followed by 0x01.
        byte[] iv = new byte[16];
        iv[15] = 1;
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(m.getBytes(StandardCharsets.UTF_8));
    }

    // Where k is UTF-8 encoded.
    public static String decrypt(byte[] k, byte[] ct) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(k, "AES");
        byte[] iv = new byte[16];
        iv[15] = 1;
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return new String(cipher.doFinal(ct), StandardCharsets.UTF_8);
    }

    public static void test() throws Exception {
        roundTripTest();
    }

    private static void roundTripTest() throws Exception {
        byte[] k = "This_key_for_demo_purposes_only!".getBytes(StandardCharsets.UTF_8);
        byte[] ct = encrypt(
            k,
            "Text may be any length you wish, no padding is required"
        );
        System.out.println(decrypt(k, ct));
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}