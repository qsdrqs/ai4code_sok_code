import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.util.Arrays;

/**
 * A utility class for signing messages using ECDSA with the secp256k1 curve.
 */
public class EcdsaSigner {

    // Statically add the Bouncy Castle provider to the Java Security framework.
    // This is necessary to use Bouncy Castle's cryptographic algorithms.
    static {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * Signs a given message using an ECDSA private key.
     *
     * @param message The message to be signed, as a byte array.
     * @param privateKeyBytes The raw private key, as a 32-byte array.
     * @return The signature of the message in DER format.
     * @throws Exception if any cryptographic error occurs (e.g., invalid key).
     */
    public static byte[] signMessage(byte[] message, byte[] privateKeyBytes) throws Exception {
        // 1. Create a PrivateKey object from the raw private key bytes.
        // The Python code `ecdsa.SigningKey.from_string(private_key, curve=ecdsa.SECP256k1)`
        // is equivalent to the following steps in Java with Bouncy Castle.

        // Get the curve parameters for secp256k1.
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");

        // Convert the raw byte array into a BigInteger.
        BigInteger privateKeyInt = new BigInteger(1, privateKeyBytes);

        // Create the private key specification.
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, ecSpec);

        // Get a KeyFactory instance for ECDSA from the Bouncy Castle provider.
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");

        // Generate the PrivateKey object.
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // 2. Sign the message.
        // The Python code `signer.sign(message)` is equivalent to the following.

        // Get a Signature instance. The `python-ecdsa` library's `sign()` method
        // signs the raw message without hashing it first. The equivalent JCA algorithm
        // is "NONEwithECDSA". If you intended to sign a hash of the message (which is common),
        // you would use "SHA256withECDSA".
        Signature ecdsaSigner = Signature.getInstance("NONEwithECDSA", "BC");

        // Initialize the signer with the private key.
        ecdsaSigner.initSign(privateKey);

        // Pass the message to be signed.
        ecdsaSigner.update(message);

        // Generate the signature.
        byte[] signature = ecdsaSigner.sign();

        // 3. Return the signature.
        return signature;
    }

    /**
     * Main method for demonstration purposes.
     */
    public static void main(String[] args) {
        try {
            // Example Usage:
            // A sample 32-byte private key (for demonstration only, DO NOT USE in production).
            byte[] privateKey = new BigInteger("18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725", 16).toByteArray();
            // Ensure the private key is exactly 32 bytes, padding if necessary.
            if (privateKey.length > 32) {
                privateKey = Arrays.copyOfRange(privateKey, privateKey.length - 32, privateKey.length);
            } else if (privateKey.length < 32) {
                byte[] paddedKey = new byte[32];
                System.arraycopy(privateKey, 0, paddedKey, 32 - privateKey.length, privateKey.length);
                privateKey = paddedKey;
            }


            // The message to be signed.
            byte[] message = "This is a test message".getBytes("UTF-8");

            // Sign the message.
            byte[] signature = signMessage(message, privateKey);

            System.out.println("Original Message: " + new String(message, "UTF-8"));
            System.out.println("Private Key (Hex): " + new BigInteger(1, privateKey).toString(16));
            System.out.println("Signature (Hex): " + new BigInteger(1, signature).toString(16));
            System.out.println("Signature Length: " + signature.length + " bytes");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}