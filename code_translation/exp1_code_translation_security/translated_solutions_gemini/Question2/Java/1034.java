import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

/**
 * Main class to demonstrate ECDSA signing and verification.
 * The structure is designed to mirror the original Python script.
 */
public class EcdsaExample {

    public static void main(String[] args) throws Exception {
        // Create a new private key. This also generates the corresponding public key.
        PrivateKey privateKey = new PrivateKey();
        PublicKey publicKey = privateKey.publicKey();

        // The message to be signed.
        String message = "Test message";

        // Sign the message using the private key.
        Signature signature = Ecdsa.sign(message, privateKey);

        // Verify the signature using the public key and the original message.
        // This will print `true` if the signature is valid.
        System.out.println(Ecdsa.verify(message, signature, publicKey));
    }
}

/**
 * A helper class that wraps the raw signature bytes.
 * In the Python library, the signature is an object.
 */
class Signature {
    private final byte[] data;

    public Signature(byte[] data) {
        this.data = data;
    }

    public byte[] getBytes() {
        return this.data;
    }
}

/**
 * A wrapper for the JCA PublicKey object to match the Python library's API.
 */
class PublicKey {
    private final java.security.PublicKey jcaPublicKey;

    public PublicKey(java.security.PublicKey jcaPublicKey) {
        this.jcaPublicKey = jcaPublicKey;
    }

    public java.security.PublicKey getJcaKey() {
        return this.jcaPublicKey;
    }
}

/**
 * A wrapper for the JCA PrivateKey object.
 * Upon instantiation, it generates a new EC key pair.
 */
class PrivateKey {
    private final java.security.PrivateKey jcaPrivateKey;
    private final PublicKey publicKey;

    public PrivateKey() throws Exception {
        // The Python library uses the secp256k1 curve by default.
        // We specify it here to be compatible.
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        KeyPairGenerator g = KeyPairGenerator.getInstance("EC");
        g.initialize(ecSpec, new SecureRandom());
        KeyPair keypair = g.generateKeyPair();

        this.jcaPrivateKey = keypair.getPrivate();
        this.publicKey = new PublicKey(keypair.getPublic());
    }

    public PublicKey publicKey() {
        return this.publicKey;
    }

    public java.security.PrivateKey getJcaKey() {
        return this.jcaPrivateKey;
    }
}

/**
 * A utility class that provides static methods for ECDSA signing and verification,
 * mimicking the Python `Ecdsa` class.
 */
class Ecdsa {

    // The standard algorithm name for ECDSA with SHA256 in Java.
    private static final String SIGNATURE_ALGORITHM = "SHA256withECDSA";

    /**
     * Signs a message using the given private key.
     *
     * @param message    The string message to sign.
     * @param privateKey The PrivateKey object.
     * @return A Signature object containing the signature data.
     */
    public static Signature sign(String message, PrivateKey privateKey) throws Exception {
        java.security.Signature ecdsaSign = java.security.Signature.getInstance(SIGNATURE_ALGORITHM);
        ecdsaSign.initSign(privateKey.getJcaKey());
        ecdsaSign.update(message.getBytes(StandardCharsets.UTF_8));
        byte[] signatureBytes = ecdsaSign.sign();
        return new Signature(signatureBytes);
    }

    /**
     * Verifies a signature against a message and a public key.
     *
     * @param message   The original string message.
     * @param signature The Signature object to verify.
     * @param publicKey The PublicKey object corresponding to the key that signed the message.
     * @return true if the signature is valid, false otherwise.
     */
    public static boolean verify(String message, Signature signature, PublicKey publicKey) throws Exception {
        java.security.Signature ecdsaVerify = java.security.Signature.getInstance(SIGNATURE_ALGORITHM);
        ecdsaVerify.initVerify(publicKey.getJcaKey());
        ecdsaVerify.update(message.getBytes(StandardCharsets.UTF_8));
        return ecdsaVerify.verify(signature.getBytes());
    }
}