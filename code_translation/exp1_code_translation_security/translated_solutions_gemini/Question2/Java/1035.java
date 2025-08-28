import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

/**
 * This class demonstrates ECDSA key generation, signing, and verification in Java,
 * equivalent to the provided Python example.
 */
public class EcdsaExample {

    /**
     * Signs a message using the given private key.
     *
     * @param msg The message to sign as a byte array.
     * @param sk  The PrivateKey (signing key) to use for signing.
     * @return The signature as a byte array.
     * @throws Exception if any cryptography error occurs.
     */
    public static byte[] sign(byte[] msg, PrivateKey sk) throws Exception {
        // The python-ecdsa library defaults to SHA-1 as the hash function for the NIST192p curve.
        // In Java, we must specify this explicitly. The standard name is "SHA1withECDSA".
        Signature ecdsaSign = Signature.getInstance("SHA1withECDSA");
        ecdsaSign.initSign(sk);
        ecdsaSign.update(msg);
        return ecdsaSign.sign();
    }

    public static void main(String[] args) throws Exception {
        // Python: sk = SigningKey.generate() # uses NIST192p
        // In Java, we generate a KeyPair (public and private key) together.
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        // The curve "NIST192p" is also known as "secp192r1" in standard cryptography libraries.
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp192r1");
        keyGen.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();

        // sk = SigningKey
        PrivateKey sk = keyPair.getPrivate();
        // vk = sk.verifying_key
        PublicKey vk = keyPair.getPublic();

        // The message to be signed. In Java, we get bytes from a string.
        // Python: b"message"
        byte[] message = "message".getBytes(StandardCharsets.UTF_8);

        // Generate the signature
        // Python: sign(b"message", sk)
        byte[] signature = sign(message, sk);

        // Python: assert vk.verify(signature, b"message")
        // In Java, verification also uses a Signature object, but initialized in verify mode.
        Signature ecdsaVerify = Signature.getInstance("SHA1withECDSA");
        ecdsaVerify.initVerify(vk);
        ecdsaVerify.update(message);
        boolean result = ecdsaVerify.verify(signature);

        // Print the result and perform the assertion
        System.out.println("Signature verified: " + result);
        assert result : "Signature verification failed!";
    }
}