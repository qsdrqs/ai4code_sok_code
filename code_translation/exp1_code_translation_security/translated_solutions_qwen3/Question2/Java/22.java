import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProvider;
import java.security.Security;
import java.security.interfaces.ECPrivateKey;

import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECNamedCurveGenParameterSpec;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;

public class ECDSAExample {

    static {
        // Register Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Generates an ECDSA key pair using the SECP256k1 curve and signs the given message.
     * Returns the signature in the same format as the Python ecdsa library: 64-byte array (r || s).
     */
    public static byte[] myfunction(byte[] message) throws Exception {
        // Generate EC key pair using SECP256k1
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "BC");
        kpg.initialize(new ECGenParameterSpec("secp256k1"), new java.security.SecureRandom());
        KeyPair keyPair = kpg.generateKeyPair();
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();

        // Get curve parameters for SECP256k1
        org.bouncycastle.jce.spec.ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(ecSpec.getCurve(), ecSpec.getG(), ecSpec.getN(), ecSpec.getH());

        // Create private key parameters for Bouncy Castle
        ECPrivateKeyParameters privKeyParams = new ECPrivateKeyParameters(privateKey.getS(), domainParams);

        // Create and initialize the ECDSA signer
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privKeyParams);

        // Generate the signature (r, s)
        BigInteger[] rs = signer.generateSignature(message);

        // Convert r and s to 32-byte arrays
        byte[] rBytes = to32ByteArray(rs[0]);
        byte[] sBytes = to32ByteArray(rs[1]);

        // Concatenate r and s into a 64-byte signature
        byte[] signature = new byte[64];
        System.arraycopy(rBytes, 0, signature, 0, 32);
        System.arraycopy(sBytes, 0, signature, 32, 32);

        return signature;
    }

    /**
     * Converts a BigInteger to a 32-byte array in big-endian format.
     * Pads with leading zeros if necessary.
     */
    private static byte[] to32ByteArray(BigInteger value) {
        byte[] bytes = value.toByteArray();
        if (bytes.length == 32) {
            return bytes;
        } else if (bytes.length < 32) {
            byte[] padded = new byte[32];
            System.arraycopy(bytes, 0, padded, 32 - bytes.length, bytes.length);
            return padded;
        } else {
            // Handle case where length > 32 (e.g., leading zero byte)
            if (bytes.length == 33 && bytes[0] == 0) {
                byte[] trimmed = new byte[32];
                System.arraycopy(bytes, 1, trimmed, 0, 32);
                return trimmed;
            } else {
                throw new IllegalArgumentException("Value exceeds 32 bytes");
            }
        }
    }

    // Example usage
    public static void main(String[] args) throws Exception {
        String messageStr = "Hello, world!";
        byte[] message = messageStr.getBytes();
        byte[] signature = myfunction(message);

        // Print signature in hex format
        System.out.println("Signature: " + bytesToHex(signature));
    }

    // Utility method to convert byte array to hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}