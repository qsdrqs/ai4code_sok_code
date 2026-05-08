import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.EAXBlockCipher;
import org.bouncycastle.crypto.params.AEADParameters;
import org.bouncycastle.crypto.params.KeyParameter;

import java.security.SecureRandom;

public class AESEncryption {

    // Helper class to represent the tuple (nonce, tag, ciphertext) returned in Python
    public static class EncryptedData {
        public final byte[] nonce;
        public final byte[] tag;
        public final byte[] ciphertext;

        public EncryptedData(byte[] nonce, byte[] tag, byte[] ciphertext) {
            this.nonce = nonce;
            this.tag = tag;
            this.ciphertext = ciphertext;
        }
    }

    // Function in Java where one encrypts a given string using a given symmetric key.
    public static EncryptedData encrypt(byte[] key, byte[] plaintext) throws Exception {
        // Generate a random 16-byte nonce (matches PyCryptodome's default for AES EAX)
        byte[] nonce = new byte[16];
        new SecureRandom().nextBytes(nonce);

        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        AEADParameters parameters = new AEADParameters(new KeyParameter(key), 128, nonce);
        cipher.init(true, parameters);

        // Encrypt: output contains ciphertext followed by tag
        byte[] output = new byte[cipher.getOutputSize(plaintext.length)];
        int outputLen = cipher.processBytes(plaintext, 0, plaintext.length, output, 0);
        cipher.doFinal(output, outputLen);

        // Split output into ciphertext and tag (tag is the last 16 bytes)
        int tagLen = 16;
        int ciphertextLen = output.length - tagLen;
        byte[] ciphertext = new byte[ciphertextLen];
        byte[] tag = new byte[tagLen];
        System.arraycopy(output, 0, ciphertext, 0, ciphertextLen);
        System.arraycopy(output, ciphertextLen, tag, 0, tagLen);

        return new EncryptedData(nonce, tag, ciphertext);
    }

    // Function in Java where one decrypts a given string using a given symmetric key.
    public static byte[] decrypt(byte[] key, EncryptedData encryptedData) throws Exception {
        byte[] nonce = encryptedData.nonce;
        byte[] tag = encryptedData.tag;
        byte[] ciphertext = encryptedData.ciphertext;

        // Bouncy Castle expects ciphertext and tag to be combined as one input
        byte[] combined = new byte[ciphertext.length + tag.length];
        System.arraycopy(ciphertext, 0, combined, 0, ciphertext.length);
        System.arraycopy(tag, 0, combined, ciphertext.length, tag.length);

        EAXBlockCipher cipher = new EAXBlockCipher(new AESEngine());
        AEADParameters parameters = new AEADParameters(new KeyParameter(key), 128, nonce);
        cipher.init(false, parameters);

        // Decrypt and verify (throws InvalidCipherTextException if authentication fails)
        byte[] data = new byte[cipher.getOutputSize(combined.length)];
        int outputLen = cipher.processBytes(combined, 0, combined.length, data, 0);
        cipher.doFinal(data, outputLen);

        return data;
    }
}