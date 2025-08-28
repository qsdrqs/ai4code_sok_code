import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.signers.ECDigestSigner;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.jce.ECNamedCurveTable;

import java.math.BigInteger;
import java.security.Security;

public class ECDSASignerExample {

    static {
        // Register Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Signs a message using ECDSA with the SECP256k1 curve.
     * The output signature is a 64-byte array: [r (32 bytes)][s (32 bytes)].
     *
     * @param message     The message to be signed (raw bytes).
     * @param privateKey  The private key as a 32-byte array.
     * @return            The signature as a 64-byte array.
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey) {
        // Get the SECP256k1 curve parameters
        ECDomainParameters domain = (ECDomainParameters) ECNamedCurveTable.getByName("secp256k1");

        // Convert the private key bytes to a BigInteger (d)
        BigInteger d = new BigInteger(1, privateKey);

        // Create the private key parameters
        ECPrivateKeyParameters key = new ECPrivateKeyParameters(d, domain);

        // Create the signer with SHA-1 digest
        ECDigestSigner signer = new ECDigestSigner(new ECDSASigner(), new SHA1Digest());

        // Initialize the signer with the private key
        signer.init(true, key);

        // Update the signer with the message
        signer.update(message, 0, message.length);

        // Generate the DER-encoded signature
        byte[] derSignature = signer.generateSignature();

        // Parse the DER signature into r and s
        BigInteger[] rs = decodeSignature(derSignature);
        BigInteger r = rs[0];
        BigInteger s = rs[1];

        // Encode r and s into 32-byte arrays
        byte[] rBytes = trimTo32Bytes(r);
        byte[] sBytes = trimTo32Bytes(s);

        // Concatenate r and s to form the final 64-byte signature
        byte[] signature = new byte[64];
        System.arraycopy(rBytes, 0, signature, 0, 32);
        System.arraycopy(sBytes, 0, signature, 32, 32);

        return signature;
    }

    /**
     * Parses a DER-encoded ECDSA signature into r and s.
     *
     * @param sigBytes DER-encoded signature bytes.
     * @return An array containing r and s as BigIntegers.
     */
    private static BigInteger[] decodeSignature(byte[] sigBytes) {
        int ptr = 0;

        if (sigBytes[ptr++] != 0x30)
            throw new RuntimeException("Signature not a DER sequence");

        int len = sigBytes[ptr++] & 0xff;
        if ((len + 2) != sigBytes.length)
            throw new RuntimeException("Invalid DER signature length");

        if (sigBytes[ptr++] != 0x02)
            throw new RuntimeException("First element not an integer");

        int rLength = sigBytes[ptr++] & 0xff;
        byte[] rBytes = new byte[rLength];
        System.arraycopy(sigBytes, ptr, rBytes, 0, rLength);
        ptr += rLength;

        if (sigBytes[ptr++] != 0x02)
            throw new RuntimeException("Second element not an integer");

        int sLength = sigBytes[ptr++] & 0xff;
        byte[] sBytes = new byte[sLength];
        System.arraycopy(sigBytes, ptr, sBytes, 0, sLength);

        // Interpret bytes as unsigned big-endian integers
        return new BigInteger[] {
            new BigInteger(1, rBytes),
            new BigInteger(1, sBytes)
        };
    }

    /**
     * Converts a BigInteger to a 32-byte big-endian unsigned representation.
     *
     * @param integer The integer to encode.
     * @return A 32-byte array.
     */
    private static byte[] trimTo32Bytes(BigInteger integer) {
        byte[] bytes = integer.toByteArray();
        int length = bytes.length;

        if (length > 32) {
            // Take the last 32 bytes
            byte[] result = new byte[32];
            System.arraycopy(bytes, length - 32, result, 0, 32);
            return result;
        } else if (length == 32) {
            return bytes;
        } else {
            // Pad with leading zeros
            byte[] result = new byte[32];
            System.arraycopy(bytes, 0, result, 32 - length, length);
            return result;
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Example message and private key (32 bytes)
        byte[] message = "Hello, world!".getBytes();
        byte[] privateKey = new byte[] {
            (byte) 0x2e, (byte) 0x0c, (byte) 0xfd, (byte) 0x35, (byte) 0x3c, (byte) 0x8d, (byte) 0x38, (byte) 0x32,
            (byte) 0x1a, (byte) 0x3b, (byte) 0x5a, (byte) 0x16, (byte) 0x00, (byte) 0x89, (byte) 0x12, (byte) 0x0c,
            (byte) 0x4a, (byte) 0x36, (byte) 0x61, (byte) 0x08, (byte) 0x67, (byte) 0xb1, (byte) 0x26, (byte) 0x6c,
            (byte) 0x23, (byte) 0x84, (byte) 0x6e, (byte) 0x63, (byte) 0x3b, (byte) 0x20, (byte) 0x14, (byte) 0x33
        };

        byte[] signature = signMessage(message, privateKey);

        // Print the signature in hex
        StringBuilder sb = new StringBuilder();
        for (byte b : signature) {
            sb.append(String.format("%02x", b & 0xff));
        }
        System.out.println("Signature: " + sb.toString());
    }
}