import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.DLSequence;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;

/**
 * A class to demonstrate ECDSA message signing, equivalent to the Python example.
 *
 * This code requires the Bouncy Castle library.
 * For Maven, add this to your pom.xml:
 * <dependency>
 *     <groupId>org.bouncycastle</groupId>
 *     <artifactId>bcprov-jdk18on</artifactId>
 *     <version>1.77</version>
 * </dependency>
 */
public class EcdsaSigner {

    /**
     * Signs a given message using a given ECDSA signing key.
     *
     * @param message The message string to sign.
     * @param privateKeyBytes The raw private key as a byte array.
     * @return The raw signature as a 64-byte array (r || s).
     * @throws Exception if any error occurs during the signing process.
     */
    public static byte[] signMessage(String message, byte[] privateKeyBytes) throws Exception {
        // 1. Hash the message using SHA-256
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = digest.digest(message.getBytes(StandardCharsets.UTF_8));

        // 2. Create a PrivateKey object from the raw private key bytes
        ECNamedCurveParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        BigInteger privateKeyInt = new BigInteger(1, privateKeyBytes);
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, ecSpec);

        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // 3. Sign the message hash
        // We use "NONEwithECDSA" because we are signing a pre-computed hash,
        // which mirrors the behavior of the Python ecdsa library.
        Signature ecdsaSign = Signature.getInstance("NONEwithECDSA", "BC");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(messageHash);
        byte[] derSignature = ecdsaSign.sign();

        // 4. Convert the ASN.1 DER signature to the raw (r || s) format
        // The Java Signature.sign() method returns an ASN.1 DER encoded signature.
        // The Python ecdsa library returns a raw concatenation of r and s.
        // This conversion makes the Java output match the Python output.
        return convertDERSignatureToRaw(derSignature);
    }

    /**
     * Converts an ASN.1 DER-encoded ECDSA signature to the raw (r || s) format.
     *
     * @param derSignature The DER-encoded signature.
     * @return The raw signature (32-byte r concatenated with 32-byte s).
     * @throws IOException if the DER signature is malformed.
     */
    private static byte[] convertDERSignatureToRaw(byte[] derSignature) throws IOException {
        try (ASN1InputStream asn1 = new ASN1InputStream(derSignature)) {
            DLSequence seq = (DLSequence) asn1.readObject();
            if (seq == null || seq.size() != 2) {
                throw new IOException("Invalid DER signature format.");
            }

            BigInteger r = ((ASN1Integer) seq.getObjectAt(0)).getValue();
            BigInteger s = ((ASN1Integer) seq.getObjectAt(1)).getValue();

            // Canonicalize s. Some libraries require s to be in the lower half of the curve's order.
            ECNamedCurveParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
            BigInteger curveN = ecSpec.getN();
            BigInteger halfCurveN = curveN.divide(BigInteger.valueOf(2));
            if (s.compareTo(halfCurveN) > 0) {
                s = curveN.subtract(s);
            }

            byte[] rBytes = r.toByteArray();
            byte[] sBytes = s.toByteArray();

            // The result should be 64 bytes (32 for r, 32 for s)
            byte[] rawSignature = new byte[64];

            // Copy r, padding with leading zeros if necessary to make it 32 bytes
            int rOffset = (rBytes.length > 32) ? rBytes.length - 32 : 0;
            int rLength = Math.min(rBytes.length, 32);
            System.arraycopy(rBytes, rOffset, rawSignature, 32 - rLength, rLength);

            // Copy s, padding with leading zeros if necessary to make it 32 bytes
            int sOffset = (sBytes.length > 32) ? sBytes.length - 32 : 0;
            int sLength = Math.min(sBytes.length, 32);
            System.arraycopy(sBytes, sOffset, rawSignature, 64 - sLength, sLength);

            return rawSignature;
        }
    }

    /**
     * Test function.
     */
    public static void main(String[] args) {
        // Add Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());

        try {
            String message = "Hello, world!";
            // The private key from the Python example, as a hex string
            String privateKeyHex = "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725";
            byte[] privateKeyBytes = hexStringToByteArray(privateKeyHex);

            byte[] signature = signMessage(message, privateKeyBytes);

            // The Python code prints the raw bytes. For better readability in Java,
            // we'll print the hex representation of the signature.
            // The output will match the hexlified output of the Python script.
            System.out.println("Signature (Hex): " + bytesToHexString(signature));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // --- Helper Methods ---

    /**
     * Converts a hexadecimal string to a byte array.
     */
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        if (len % 2 != 0) {
            throw new IllegalArgumentException("Hex string must have an even number of characters");
        }
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Converts a byte array to its hexadecimal string representation.
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}