import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Security;

import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.BigIntegers;

public class ECDSATest {

    static {
        // Register Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        // Generate EC key pair for NIST192p (secp192r1)
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "BC");
        kpg.initialize(192);
        PrivateKey privateKey = kpg.generateKeyPair().getPrivate();

        // Message to sign
        byte[] message = "i am a message".getBytes();

        // Sign the message
        byte[] signature = sign(message, privateKey);

        // Print signature in hex format
        System.out.println(bytesToHex(signature));
    }

    public static byte[] sign(byte[] message, PrivateKey key) throws Exception {
        // Compute SHA-1 hash of the message
        SHA1Digest digest = new SHA1Digest();
        byte[] hash = new byte[digest.getDigestSize()];
        digest.update(message, 0, message.length);
        digest.doFinal(hash, 0);

        // Create ECDSA signer and initialize with private key
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, new ParametersWithRandom((ECPrivateKeyParameters) PrivateKeyFactory.createKey(key.getEncoded())));

        // Generate signature (r, s)
        BigInteger[] rs = signer.generateSignature(hash);

        // Convert r and s to 24-byte unsigned big-endian format
        byte[] rBytes = BigIntegers.asUnsignedByteArray(rs[0]);
        byte[] sBytes = BigIntegers.asUnsignedByteArray(rs[1]);

        byte[] r = new byte[24];
        byte[] s = new byte[24];
        System.arraycopy(rBytes, 0, r, r.length - rBytes.length, rBytes.length);
        System.arraycopy(sBytes, 0, s, s.length - sBytes.length, sBytes.length);

        // Concatenate r and s into the final signature
        byte[] signatureBytes = new byte[r.length + s.length];
        System.arraycopy(r, 0, signatureBytes, 0, r.length);
        System.arraycopy(s, 0, signatureBytes, r.length, s.length);

        return signatureBytes;
    }

    // Utility method to convert byte array to hex string
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}