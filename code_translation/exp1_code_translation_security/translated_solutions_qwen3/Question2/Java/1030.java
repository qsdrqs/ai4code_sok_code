import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.util.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.Security;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

public class ECDSAExample {

    // Initialize Bouncy Castle provider
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // Domain parameters for secp256k1 curve
    private static final ECDomainParameters secp256k1DomainParams = (ECDomainParameters) ECNamedCurveTable.getByName("secp256k1");

    /**
     * Computes the SHA3-256 hash of a given message.
     *
     * @param msg The input message as a String.
     * @return The SHA3-256 hash as a byte array.
     */
    public static byte[] sha3_256Hash(String msg) {
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
        SHA3Digest digest = new SHA3Digest();
        digest.update(msgBytes, 0, msgBytes.length);
        byte[] hashBytes = new byte[digest.getDigestSize()];
        digest.doFinal(hashBytes, 0);
        return hashBytes;
    }

    /**
     * Signs a message using ECDSA with the secp256k1 curve and SHA3-256.
     *
     * @param msg     The message to sign.
     * @param privKey The private key as a BigInteger.
     * @return An array containing the signature components [r, s].
     */
    public static BigInteger[] signECDSAsecp256k1(String msg, BigInteger privKey) {
        ECPrivateKeyParameters key = new ECPrivateKeyParameters(privKey, secp256k1DomainParams);
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, new org.bouncycastle.crypto.params.ParametersWithRandom(key));
        byte[] hashBytes = sha3_256Hash(msg);
        return signer.generateSignature(hashBytes);
    }

    /**
     * Verifies an ECDSA signature using the secp256k1 curve and SHA3-256.
     *
     * @param msg       The original message.
     * @param signature The signature as an array [r, s].
     * @param pubKey    The public key used for verification.
     * @return true if the signature is valid, false otherwise.
     */
    public static boolean verifyECDSAsecp256k1(String msg, BigInteger[] signature, ECPublicKeyParameters pubKey) {
        ECDSASigner signer = new ECDSASigner();
        signer.init(false, pubKey);
        byte[] hashBytes = sha3_256Hash(msg);
        return signer.verifySignature(hashBytes, signature[0], signature[1]);
    }

    /**
     * Main method to demonstrate ECDSA signing with secp256k1 and SHA3-256.
     */
    public static void main(String[] args) {
        String msg = "Message for ECDSA signing";

        // Generate a secure random private key within [1, order - 1]
        SecureRandom random = new SecureRandom();
        BigInteger order = secp256k1DomainParams.getOrder();
        BigInteger privKey = new BigInteger(order.bitLength(), random);
        privKey = privKey.mod(order.subtract(BigInteger.ONE)).add(BigInteger.ONE);

        // Derive the public key from the private key
        ECPoint q = secp256k1DomainParams.getG().multiply(privKey);
        ECPublicKeyParameters pubKey = new ECPublicKeyParameters(q, secp256k1DomainParams);

        // Sign the message
        BigInteger[] signature = signECDSAsecp256k1(msg, privKey);

        // Print results
        System.out.println("Message: " + msg);
        System.out.println("Private key: " + privKey.toString(16));
        System.out.println("Signature: r=" + signature[0].toString(16) + ", s=" + signature[1].toString(16));

        // Optional: Verify the signature
        boolean isValid = verifyECDSAsecp256k1(msg, signature, pubKey);
        System.out.println("Signature is valid: " + isValid);
    }
}