import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

/**
 * This class provides functionality for generating ECDSA signatures.
 * It is a Java translation of the provided Python code.
 */
public class EcdsaSigner {

    /**
     * Signs a message using the private key from the given KeyPair and verifies it with the public key.
     * This mimics the behavior of the Python `sign` function.
     *
     * @param msg The message to be signed, as a byte array.
     * @param keyPair The ECDSA KeyPair (containing both private and public keys).
     * @return The digital signature as a byte array.
     * @throws NoSuchAlgorithmException If the specified algorithm is not available.
     * @throws InvalidKeyException If the key is invalid.
     * @throws SignatureException If a signature error occurs.
     */
    public static byte[] sign(byte[] msg, KeyPair keyPair)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // 1. Get a Signature instance for ECDSA with SHA256
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");

        // 2. Initialize the Signature object for signing with the private key
        ecdsaSign.initSign(privateKey);

        // 3. Supply the message data to be signed
        ecdsaSign.update(msg);

        // 4. Generate the signature
        byte[] signature = ecdsaSign.sign();

        // 5. Assert that the signature is valid (mimicking the Python assert)
        Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(msg);
        if (!ecdsaVerify.verify(signature)) {
            throw new AssertionError("Signature verification failed unexpectedly.");
        }

        return signature;
    }

    /**
     * A test method to demonstrate the signing process.
     * This mimics the behavior of the Python `test` function.
     */
    public static void test()
            throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, SignatureException {
        
        // Generate an ECDSA key pair. The python-ecdsa library defaults to the
        // secp256k1 curve, which is standard for Bitcoin and Ethereum.
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        keyGen.initialize(new ECGenParameterSpec("secp256k1"), new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();

        // The message to sign
        byte[] message = "message".getBytes(StandardCharsets.UTF_8);

        // Sign the message
        byte[] signatureBytes = sign(message, keyPair);

        // Print the signature. In Java, byte arrays don't have a nice default
        // string representation, so we encode it in Base64 for printing.
        System.out.println(Base64.getEncoder().encodeToString(signatureBytes));
    }

    /**
     * Main method to run the test.
     */
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}