import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

/**
 * This class demonstrates ECDSA signing and verification, equivalent to the Python script.
 * It uses the NIST P-384 curve (also known as secp384r1).
 */
public class EcdsaExample {

    /**
     * The main method that executes the signing and verification process.
     * @param args Command line arguments (not used).
     * @throws Exception if any cryptographic error occurs.
     */
    public static void main(String[] args) throws Exception {
        // In Python: sk = SigningKey.generate(curve=NIST384p)
        // In Java, we generate a KeyPair which contains both the private (sk) and public (vk) keys.
        // The curve "NIST384p" is named "secp384r1" in the JCA standard.
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(new ECGenParameterSpec("secp384r1"));
        KeyPair keyPair = kpg.generateKeyPair();
        
        // sk (SigningKey) is the private key
        PrivateKey privateKey = keyPair.getPrivate();
        
        // vk (VerifyingKey) is the public key
        PublicKey publicKey = keyPair.getPublic();

        // The message to be signed. The b"testing" in Python is a byte array.
        byte[] message = "testing".getBytes(StandardCharsets.UTF_8);

        // In Python: signature = signMessage(sk, b"testing")
        byte[] signature = signMessage(privateKey, message);

        // In Python: print(vk.verify(signature, b"testing"))
        // In Java, the verify method returns a boolean.
        Signature ecdsaVerify = Signature.getInstance("SHA384withECDSA");
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(message);
        boolean result = ecdsaVerify.verify(signature);

        System.out.println(result); // Will print "true"
    }

    /**
     * Signs a message using the given private key.
     * @param key The private key to sign with.
     * @param message The message to sign, as a byte array.
     * @return The resulting signature as a byte array.
     * @throws Exception if any cryptographic error occurs.
     */
    public static byte[] signMessage(PrivateKey key, byte[] message) throws Exception {
        // In Python: return key.sign(message)
        // In Java, we use the Signature class. The algorithm "SHA384withECDSA" is
        // appropriate for the P-384 curve.
        Signature ecdsaSign = Signature.getInstance("SHA384withECDSA");
        ecdsaSign.initSign(key);
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }
}