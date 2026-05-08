import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

public class Main {

    private static final byte[] key = "Sixteen byte key".getBytes(StandardCharsets.UTF_8);
    private static final int MAC_SIZE_BITS = 128;      // PyCryptodome EAX default
    private static final int NONCE_SIZE_BYTES = 16;    // PyCryptodome EAX default

    public static class EncryptResult {
        public final byte[] ciphertext;
        public final byte[] tag;
        public final byte[] nonce;

        public EncryptResult(byte[] ciphertext, byte[] tag, byte[] nonce) {
            this.ciphertext = ciphertext;
            this.tag = tag;
            this.nonce = nonce;
        }
    }

    public static EncryptResult encrypt(byte[] key, byte[] msg) throws Exception {
        // Generate a random nonce (PyCryptodome does this automatically)
        byte[] nonce = new byte[NONCE_SIZE_BYTES];
        new SecureRandom().nextBytes(nonce);

        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        cipher.init(true, new AEADParameters(new KeyParameter(key), MAC_SIZE_BITS, nonce));

        // BouncyCastle outputs ciphertext || tag together
        byte[] output = new byte[cipher.getOutputSize(msg.length)];
        int len = cipher.processBytes(msg, 0, msg.length, output, 0);
        len += cipher.doFinal(output, len);

        // Split the output into ciphertext and tag
        int tagLen = MAC_SIZE_BITS / 8;
        byte[] ciphertext = Arrays.copyOfRange(output, 0, len - tagLen);
        byte[] tag = Arrays.copyOfRange(output, len - tagLen, len);

        return new EncryptResult(ciphertext, tag, nonce);
    }

    public static byte[] decrypt(byte[] key, byte[] ciphertext, byte[] nonce, byte[] tag) throws Exception {
        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        cipher.init(false, new AEADParameters(new KeyParameter(key), MAC_SIZE_BITS, nonce));

        // BouncyCastle expects ciphertext || tag concatenated for decryption
        byte[] input;
        if (tag != null) {
            input = new byte[ciphertext.length + tag.length];
            System.arraycopy(ciphertext, 0, input, 0, ciphertext.length);
            System.arraycopy(tag, 0, input, ciphertext.length, tag.length);
        } else {
            input = ciphertext;
        }

        byte[] plaintext = new byte[cipher.getOutputSize(input.length)];
        int len = cipher.processBytes(input, 0, input.length, plaintext, 0);
        len += cipher.doFinal(plaintext, len);  // Also verifies the tag

        return Arrays.copyOfRange(plaintext, 0, len);
    }

    public static void main(String[] args) throws Exception {
        EncryptResult result = encrypt(key, "message".getBytes(StandardCharsets.UTF_8));
        byte[] decrypted = decrypt(key, result.ciphertext, result.nonce, result.tag);
        System.out.println(new String(decrypted, StandardCharsets.UTF_8));
    }
}