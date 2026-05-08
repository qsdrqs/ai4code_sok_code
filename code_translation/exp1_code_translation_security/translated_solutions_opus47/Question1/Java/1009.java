import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;

import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AESEaxExample {

    public static byte[][] encodeString(String message, byte[] key, byte[] nonce) throws Exception {
        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        AEADParameters params = new AEADParameters(new KeyParameter(key), 128, nonce);
        cipher.init(true, params);

        // Encode string as bytes
        byte[] mBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] output = new byte[cipher.getOutputSize(mBytes.length)];
        int len = cipher.processBytes(mBytes, 0, mBytes.length, output, 0);
        cipher.doFinal(output, len);

        // Split output into ciphertext and tag (tag is 16 bytes = 128 bits)
        int tagLength = 16;
        byte[] ciphertext = Arrays.copyOfRange(output, 0, output.length - tagLength);
        byte[] tag = Arrays.copyOfRange(output, output.length - tagLength, output.length);

        return new byte[][] { ciphertext, tag };
    }

    public static String decodeString(byte[][] p, byte[] key, byte[] nonce) throws Exception {
        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        AEADParameters params = new AEADParameters(new KeyParameter(key), 128, nonce);
        cipher.init(false, params);

        // Combine ciphertext and tag for decryption input
        byte[] combined = new byte[p[0].length + p[1].length];
        System.arraycopy(p[0], 0, combined, 0, p[0].length);
        System.arraycopy(p[1], 0, combined, p[0].length, p[1].length);

        byte[] output = new byte[cipher.getOutputSize(combined.length)];
        int len = cipher.processBytes(combined, 0, combined.length, output, 0);
        len += cipher.doFinal(output, len);

        // Decode from bytes to string
        return new String(output, 0, len, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        String originalMessage = "secret data";

        SecureRandom random = new SecureRandom();
        byte[] nonce = new byte[16];
        random.nextBytes(nonce);
        byte[] key = new byte[16];
        random.nextBytes(key);

        System.out.println(originalMessage);
        byte[][] ciphertext = encodeString(originalMessage, key, nonce);
        String decodedString = decodeString(ciphertext, key, nonce);
        System.out.println(decodedString);
    }
}