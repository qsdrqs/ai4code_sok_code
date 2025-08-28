import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPrivateKeySpec;

// Bouncy Castle is a third-party dependency required for this code.
// It provides support for the SECP256k1 curve and the specific signature format.
//
// Maven Dependency:
// <dependency>
//     <groupId>org.bouncycastle</groupId>
//     <artifactId>bcprov-jdk15on</artifactId>
//     <version>1.70</version>
// </dependency>
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * A class in Java that signs a given message using a given ECDSA signing key.
 * This is a translation of the provided Python function.
 */
public class EcdsaSigner {

    // Statically register the Bouncy Castle provider.
    // This is necessary to use its cryptographic algorithms via the Java Cryptography Architecture (JCA).
    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * Signs a message using a given ECDSA signing key.
     *
     * @param message The message to be signed, as a byte array.
     * @param privateKeyBytes The raw 32-byte private key.
     * @return The 64-byte compact signature (r and s values concatenated).
     * @throws Exception if any cryptographic error occurs during the signing process.
     */
    public static byte[] sign(byte[] message, byte[] privateKeyBytes) throws Exception {
        // The python-ecdsa library uses the SECP256k1 curve.
        // We get its parameters from Bouncy Castle's named curve table.
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");

        // The private key is a number. We create a BigInteger from the raw bytes.
        // The '1' signum argument ensures the number is treated as positive.
        BigInteger privateKeyInt = new BigInteger(1, privateKeyBytes);

        // Create the private key specification using the key integer and curve parameters.
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, ecSpec);

        // Get the KeyFactory instance for ECDSA and specify Bouncy Castle as the provider.
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", BouncyCastleProvider.PROVIDER_NAME);

        // Generate the PrivateKey object from the specification.
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // The python-ecdsa `sign` method returns a "compact" signature (raw r and s values).
        // To achieve this in Java, we use the "PLAIN-ECDSA" format provided by Bouncy Castle.
        // The standard "SHA256withECDSA" would return a DER-encoded signature.
        // The python library also hashes the message with SHA-256 by default.
        Signature signature = Signature.getInstance("SHA256withPLAIN-ECDSA", BouncyCastleProvider.PROVIDER_NAME);

        // Initialize the signature object for signing with our private key.
        signature.initSign(privateKey);

        // Add the message data to be signed.
        signature.update(message);

        // Perform the signing operation.
        byte[] signedMessage = signature.sign();

        // Return the signed message (the compact signature).
        return signedMessage;
    }
}