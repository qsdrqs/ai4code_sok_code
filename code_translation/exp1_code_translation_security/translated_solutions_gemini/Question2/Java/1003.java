import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.*;
import java.util.Arrays;

/**
 * A Java implementation of ECDSA signing and verification using the NIST P-256 curve,
 * compatible with the Python ecdsa library's raw signature format.
 */
public class EcdsaNist256 {

    private static final String ALGORITHM = "ECDSA";
    private static final String CURVE_NAME = "secp256r1"; // NIST P-256
    private static final String SIGNATURE_ALGORITHM = "SHA256withECDSA";
    private static final int KEY_LENGTH_BYTES = 32; // 256 bits

    private static final ECParameterSpec EC_PARAMETER_SPEC;

    static {
        try {
            // Get the curve parameters for secp256r1
            AlgorithmParameters params = AlgorithmParameters.getInstance("EC");
            params.init(new ECGenParameterSpec(CURVE_NAME));
            EC_PARAMETER_SPEC = params.getParameterSpec(ECParameterSpec.class);
        } catch (NoSuchAlgorithmException | InvalidParameterSpecException e) {
            // This should not happen with standard JREs
            throw new RuntimeException("Cannot initialize EC Parameter Spec", e);
        }
    }

    /**
     * Signs a message using a raw private key.
     *
     * @param message The message to sign (the data will be hashed with SHA-256).
     * @param signingKeyRaw The 32-byte raw private key.
     * @return A 64-byte raw signature (r || s).
     * @throws Exception if signing fails.
     */
    public static byte[] signNist256(byte[] message, byte[] signingKeyRaw) throws Exception {
        // 1. Create PrivateKey object from raw bytes
        BigInteger s = new BigInteger(1, signingKeyRaw);
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(s, EC_PARAMETER_SPEC);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // 2. Initialize Signature object
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(message);

        // 3. Sign the message (produces a DER-encoded signature)
        byte[] derSignature = signature.sign();

        // 4. Convert DER signature to raw (r || s) format for compatibility
        return derToRawSignature(derSignature);
    }

    /**
     * Verifies a raw signature against a message and a raw public key.
     *
     * @param message The message that was signed.
     * @param signatureRaw The 64-byte raw signature (r || s).
     * @param verifyingKeyRaw The 64-byte raw public key (x || y).
     * @return true if the signature is valid, false otherwise.
     */
    public static boolean verifyNist256(byte[] message, byte[] signatureRaw, byte[] verifyingKeyRaw) {
        try {
            // 1. Create PublicKey object from raw bytes
            BigInteger x = new BigInteger(1, Arrays.copyOfRange(verifyingKeyRaw, 0, KEY_LENGTH_BYTES));
            BigInteger y = new BigInteger(1, Arrays.copyOfRange(verifyingKeyRaw, KEY_LENGTH_BYTES, 2 * KEY_LENGTH_BYTES));
            ECPoint w = new ECPoint(x, y);
            ECPublicKeySpec publicKeySpec = new ECPublicKeySpec(w, EC_PARAMETER_SPEC);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            // 2. Convert the raw signature (r || s) to DER format
            byte[] derSignature = rawToDerSignature(signatureRaw);

            // 3. Initialize Signature object for verification
            Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
            signature.initVerify(publicKey);
            signature.update(message);

            // 4. Verify the signature
            return signature.verify(derSignature);
        } catch (Exception e) {
            // Catches BadSignatureError, InvalidKeyException, etc.
            // In case of any error during verification, return false.
            System.err.println("Verification failed with exception: " + e.getMessage());
            return false;
        }
    }

    // --- Helper methods for signature format conversion ---

    /**
     * Converts an ASN.1 DER-encoded signature to a raw (r || s) format.
     * @param derSignature The DER-encoded signature.
     * @return The 64-byte raw signature.
     * @throws IllegalArgumentException if the DER signature is malformed.
     */
    private static byte[] derToRawSignature(byte[] derSignature) {
        // ASN.1 DER format: 0x30 b1 0x02 b2 (r) 0x02 b3 (s)
        // b1: length of remaining bytes
        // b2: length of r
        // b3: length of s
        int offset = 3; // Start of r's length byte
        int rLength = derSignature[offset++];
        
        // Handle leading zero for positive integers
        if (rLength > KEY_LENGTH_BYTES) {
            offset += (rLength - KEY_LENGTH_BYTES);
            rLength = KEY_LENGTH_BYTES;
        }
        
        byte[] r = new byte[KEY_LENGTH_BYTES];
        System.arraycopy(derSignature, offset, r, KEY_LENGTH_BYTES - rLength, rLength);
        
        offset += rLength + 1; // Move to s's length byte
        int sLength = derSignature[offset++];

        // Handle leading zero for positive integers
        if (sLength > KEY_LENGTH_BYTES) {
            offset += (sLength - KEY_LENGTH_BYTES);
            sLength = KEY_LENGTH_BYTES;
        }

        byte[] s = new byte[KEY_LENGTH_BYTES];
        System.arraycopy(derSignature, offset, s, KEY_LENGTH_BYTES - sLength, sLength);

        byte[] rawSignature = new byte[2 * KEY_LENGTH_BYTES];
        System.arraycopy(r, 0, rawSignature, 0, KEY_LENGTH_BYTES);
        System.arraycopy(s, 0, rawSignature, KEY_LENGTH_BYTES, KEY_LENGTH_BYTES);

        return rawSignature;
    }

    /**
     * Converts a raw (r || s) signature to the ASN.1 DER format.
     * @param rawSignature The 64-byte raw signature.
     * @return The DER-encoded signature.
     */
    private static byte[] rawToDerSignature(byte[] rawSignature) {
        BigInteger r = new BigInteger(1, Arrays.copyOfRange(rawSignature, 0, KEY_LENGTH_BYTES));
        BigInteger s = new BigInteger(1, Arrays.copyOfRange(rawSignature, KEY_LENGTH_BYTES, 2 * KEY_LENGTH_BYTES));

        byte[] rBytes = r.toByteArray();
        byte[] sBytes = s.toByteArray();

        int rLen = rBytes.length;
        int sLen = sBytes.length;
        int totalLen = 6 + rLen + sLen; // 0x30 + len + 0x02 + r_len + r + 0x02 + s_len + s

        byte[] derSignature = new byte[totalLen];
        derSignature[0] = 0x30; // SEQUENCE
        derSignature[1] = (byte) (totalLen - 2);
        derSignature[2] = 0x02; // INTEGER
        derSignature[3] = (byte) rLen;
        System.arraycopy(rBytes, 0, derSignature, 4, rLen);
        derSignature[4 + rLen] = 0x02; // INTEGER
        derSignature[5 + rLen] = (byte) sLen;
        System.arraycopy(sBytes, 0, derSignature, 6 + rLen, sLen);

        return derSignature;
    }

    // --- Main method for demonstration ---

    public static void main(String[] args) throws Exception {
        // 1. Generate a new key pair for the demonstration
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
        kpg.initialize(EC_PARAMETER_SPEC, new SecureRandom());
        KeyPair keyPair = kpg.generateKeyPair();

        // 2. Extract raw private and public keys
        ECPrivateKey ecPrivateKey = (ECPrivateKey) keyPair.getPrivate();
        ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();

        // Private key as 32-byte array
        byte[] privateKeyRaw = toFixedSizeBytes(ecPrivateKey.getS(), KEY_LENGTH_BYTES);

        // Public key as 64-byte array (x || y)
        byte[] xBytes = toFixedSizeBytes(ecPublicKey.getW().getAffineX(), KEY_LENGTH_BYTES);
        byte[] yBytes = toFixedSizeBytes(ecPublicKey.getW().getAffineY(), KEY_LENGTH_BYTES);
        byte[] publicKeyRaw = new byte[2 * KEY_LENGTH_BYTES];
        System.arraycopy(xBytes, 0, publicKeyRaw, 0, KEY_LENGTH_BYTES);
        System.arraycopy(yBytes, 0, publicKeyRaw, KEY_LENGTH_BYTES, KEY_LENGTH_BYTES);

        System.out.println("Generated Raw Private Key (" + privateKeyRaw.length + " bytes): " + bytesToHex(privateKeyRaw));
        System.out.println("Generated Raw Public Key (" + publicKeyRaw.length + " bytes): " + bytesToHex(publicKeyRaw));
        System.out.println();

        // 3. Define a message to sign
        String originalMessage = "This is a test message for ECDSA NIST P-256.";
        byte[] messageBytes = originalMessage.getBytes();

        // 4. Sign the message
        System.out.println("Signing message: \"" + originalMessage + "\"");
        byte[] signature = signNist256(messageBytes, privateKeyRaw);
        System.out.println("Generated Raw Signature (" + signature.length + " bytes): " + bytesToHex(signature));
        System.out.println();

        // 5. Verify the signature (should be successful)
        System.out.println("--- Verification ---");
        boolean isSignatureValid = verifyNist256(messageBytes, signature, publicKeyRaw);
        System.out.println("Signature verification with correct key and message: " + isSignatureValid);

        // 6. Tamper with the message and verify again (should fail)
        byte[] tamperedMessageBytes = (originalMessage + " ").getBytes();
        boolean isTamperedMessageValid = verifyNist256(tamperedMessageBytes, signature, publicKeyRaw);
        System.out.println("Signature verification with tampered message: " + isTamperedMessageValid);

        // 7. Tamper with the signature and verify again (should fail)
        byte[] tamperedSignature = signature.clone();
        tamperedSignature[0]++; // Flip a bit
        boolean isTamperedSignatureValid = verifyNist256(messageBytes, tamperedSignature, publicKeyRaw);
        System.out.println("Signature verification with tampered signature: " + isTamperedSignatureValid);
    }

    // --- Utility methods for demonstration ---

    private static byte[] toFixedSizeBytes(BigInteger value, int byteLength) {
        byte[] bytes = value.toByteArray();
        if (bytes.length == byteLength) {
            return bytes;
        }
        byte[] fixedBytes = new byte[byteLength];
        if (bytes.length > byteLength) { // Usually due to leading sign bit
            System.arraycopy(bytes, bytes.length - byteLength, fixedBytes, 0, byteLength);
        } else { // Pad with leading zeros
            System.arraycopy(bytes, 0, fixedBytes, byteLength - bytes.length, bytes.length);
        }
        return fixedBytes;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}