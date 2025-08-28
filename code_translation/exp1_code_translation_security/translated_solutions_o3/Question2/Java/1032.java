/*
 * Write a function in Java that signs a given message using a given
 * ECDSA signing key (curve: secp256k1, exactly the same steps
 * performed by the original Python version).
 *
 * NOTE:
 * – The project is assumed to be built with Bouncy-Castle on the class-path.
 * – All required dependencies (Bouncy-Castle provider and its utility
 *   classes for hexadecimal conversions) will be supplied by the
 *   surrounding build system exactly as the task description promises.
 */

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECPrivateKeySpec;

import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.util.encoders.Hex;

public final class EcdsaSigner {

    /**
     * Signs a message with a raw 32-byte secp256k1 private key.
     *
     * @param message         UTF-8 text that has to be signed.
     * @param privateKeyBytes 32-byte secp256k1 private key.
     * @return the ECDSA signature (DER encoded, exactly like the Python code).
     */
    public static byte[] signMessage(String message, byte[] privateKeyBytes) throws GeneralSecurityException {

        /* 1. Hash the message (SHA-256) */
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = sha256.digest(message.getBytes(StandardCharsets.UTF_8));

        /* 2. Turn the raw key bytes into a real EC PrivateKey object */
        // Make sure the Bouncy-Castle provider is registered.
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        // secp256k1 curve parameters
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");
        BigInteger d = new BigInteger(1, privateKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        PrivateKey privateKey =
                keyFactory.generatePrivate(new ECPrivateKeySpec(d, ecSpec));

        /* 3. Sign the *hash* exactly like the Python version          *
         *    (Python hashed once with SHA-256 and then called .sign(), *
         *    whose default behaviour hashes again with SHA-1;          *
         *    to reproduce that we let Java hash the 32-byte array with *
         *    SHA-1 inside the Signature object).                       */
        Signature signature = Signature.getInstance("SHA1withECDSA", "BC");
        signature.initSign(privateKey);
        signature.update(messageHash);          // SHA-1 will be applied inside
        return signature.sign();                // DER-encoded signature
    }

    /* ------------------------------------------------------------ */

    public static void main(String[] args) throws Exception {
        String message = "Hello, world!";

        byte[] privateKey =
                Hex.decode("18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725");

        byte[] signature = signMessage(message, privateKey);

        // Print the raw signature as hex (just for demonstration).
        System.out.println(Hex.toHexString(signature));
    }

    // This class is not supposed to be instantiated.
    private EcdsaSigner() {}
}