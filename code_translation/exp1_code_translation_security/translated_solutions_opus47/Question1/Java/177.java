import java.security.SecureRandom;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;

public class AESEncryption {

    /** Represents the (ciphertext, nonce) tuple returned by encrypt. */
    public static class CipherNonce {
        public final byte[] c;
        public final byte[] nonce;

        public CipherNonce(byte[] c, byte[] nonce) {
            this.c = c;
            this.nonce = nonce;
        }
    }

    public static CipherNonce encrypt(byte[] m, byte[] sk) throws InvalidCipherTextException {
        // PyCryptodome uses a 16-byte random nonce by default for EAX mode
        byte[] nonce = new byte[16];
        new SecureRandom().nextBytes(nonce);

        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        // 128-bit authentication tag (PyCryptodome default)
        AEADParameters params = new AEADParameters(new KeyParameter(sk), 128, nonce);
        cipher.init(true, params);

        byte[] output = new byte[cipher.getOutputSize(m.length)];
        int len = cipher.processBytes(m, 0, m.length, output, 0);
        cipher.doFinal(output, len);

        return new CipherNonce(output, nonce);
    }

    public static byte[] decrypt(CipherNonce cn, byte[] sk) throws InvalidCipherTextException {
        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        AEADParameters params = new AEADParameters(new KeyParameter(sk), 128, cn.nonce);
        cipher.init(false, params);

        byte[] output = new byte[cipher.getOutputSize(cn.c.length)];
        int len = cipher.processBytes(cn.c, 0, cn.c.length, output, 0);
        cipher.doFinal(output, len);

        return output;
    }
}