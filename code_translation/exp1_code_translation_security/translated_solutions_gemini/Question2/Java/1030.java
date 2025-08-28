import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.security.Security;
import java.nio.charset.StandardCharsets;

/**
 * This class demonstrates ECDSA signing and verification using the secp256k1 curve
 * and SHA3-256 hashing, equivalent to the provided Python script.
 *
 * A verification step has been added to demonstrate a complete sign/verify cycle.
 *
 * Dependency: Bouncy Castle
 * To compile and run this code, you need to have the Bouncy Castle library
 * (e.g., bcprov-jdk18on.jar) in your classpath.
 *
 * Maven Dependency:
 * <dependency>
 *     <groupId>org.bouncycastle</groupId>
 *     <artifactId>bcprov-jdk18on</artifactId>
 *     <version>1.78.1</version> <!-- Or the latest version -->
 * </dependency>
 */
public class EcdsaSecp256k1Sha3 {

    // Static curve parameters for secp256k1, initialized in a static block.
    private static final ECDomainParameters CURVE;
    private static final BigInteger CURVE_ORDER;
    private static final ECPoint GENERATOR_POINT;

    static {
        // Add Bouncy Castle as a security provider if it's not already present.
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        // Get curve parameters for "secp256k1".
        ECNamedCurveParameterSpec spec = ECNamedCurveTable.getByName("secp256k1");
        CURVE = new ECDomainParameters(spec.getCurve(), spec.getG(), spec.getN(), spec.getH());
        CURVE_ORDER = CURVE.getN();
        GENERATOR_POINT = CURVE.getG();
    }

    /**
     * Hashes a message using SHA3-256.
     * @param msg The input string message.
     * @return The byte array of the hash.
     */
    public static byte[] sha3_256Hash(String msg) {
        SHA3Digest digest = new SHA3Digest(256);
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
        digest.update(msgBytes, 0, msgBytes.length);
        byte[] hashBytes = new byte[digest.getDigestSize()];
        digest.doFinal(hashBytes, 0);
        return hashBytes;
    }

    /**
     * Signs a message using ECDSA with secp256k1.
     * @param msg The message to sign.
     * @param privKey The private key as a BigInteger.
     * @return An array of two BigIntegers representing the signature: [r, s].
     */
    public static BigInteger[] signECDSAsecp256k1(String msg, BigInteger privKey) {
        byte[] msgHash = sha3_256Hash(msg);

        ECDSASigner signer = new ECDSASigner();
        ECPrivateKeyParameters privateKeyParams = new ECPrivateKeyParameters(privKey, CURVE);
        signer.init(true, privateKeyParams); // 'true' for signing

        return signer.generateSignature(msgHash);
    }

    /**
     * Verifies an ECDSA secp256k1 signature.
     * @param msg The original message.
     * @param signature The signature to verify, as an array [r, s].
     * @param pubKey The public key as an ECPoint on the curve.
     * @return true if the signature is valid, false otherwise.
     */
    public static boolean verifyECDSAsecp256k1(String msg, BigInteger[] signature, ECPoint pubKey) {
        byte[] msgHash = sha3_256Hash(msg);

        ECDSASigner verifier = new ECDSASigner();
        ECPublicKeyParameters publicKeyParams = new ECPublicKeyParameters(pubKey, CURVE);
        verifier.init(false, publicKeyParams); // 'false' for verifying

        BigInteger r = signature[0];
        BigInteger s = signature[1];

        return verifier.verifySignature(msgHash, r, s);
    }

    public static void main(String[] args) {
        // ECDSA sign message (using the curve secp256k1 + SHA3-256)
        String msg = "Message for ECDSA signing";

        // Generate a cryptographically secure random private key.
        // The private key must be an integer in the range [1, n-1], where n is the curve order.
        SecureRandom secureRandom = new SecureRandom();
        BigInteger privKey;
        do {
            privKey = new BigInteger(CURVE_ORDER.bitLength(), secureRandom);
        } while (privKey.compareTo(BigInteger.ONE) < 0 || privKey.compareTo(CURVE_ORDER) >= 0);

        // Sign the message
        BigInteger[] signature = signECDSAsecp256k1(msg, privKey);

        System.out.println("Message: " + msg);
        System.out.println("Private key: 0x" + privKey.toString(16));
        System.out.println("Signature: r=0x" + signature[0].toString(16) + ", s=0x" + signature[1].toString(16));

        // --- Verification (added for a complete example) ---
        // 1. Derive the public key from the private key: pubKey = privKey * G
        ECPoint pubKey = GENERATOR_POINT.multiply(privKey);

        // 2. Verify the signature using the derived public key
        boolean isValid = verifyECDSAsecp256k1(msg, signature, pubKey);

        System.out.println("\n--- Verification ---");
        System.out.println("Signature valid? " + isValid);

        // 3. Test verification with a wrong message to ensure it fails
        boolean isInvalid = verifyECDSAsecp256k1("A wrong message", signature, pubKey);
        System.out.println("Signature valid for a wrong message? " + isInvalid);
    }
}