import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class EcdsaExample {

    /**
     * Equivalent to Python’s   signature = key.sign(message)
     */
    public static byte[] sign(byte[] message, PrivateKey key) throws Exception {
        // “SHA256withECDSA” is the usual default in Java for EC signatures.
        // The Python sample used the raw curve without an explicit hash,
        // but SHA-256 is generally preferred and interoperable.
        Signature signer = Signature.getInstance("SHA256withECDSA");
        signer.initSign(key);
        signer.update(message);
        return signer.sign();
    }

    public static void main(String[] args) throws Exception {

        /* 1. === Generate an EC key pair (NIST P-192 curve, same as Python default) === */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        // NIST P-192 is called “secp192r1” (a.k.a prime192v1) in Java
        kpg.initialize(new ECGenParameterSpec("secp192r1"), new SecureRandom());
        KeyPair kp = kpg.generateKeyPair();

        /* 2. === Sign the message === */
        byte[] message = "i am a message".getBytes(StandardCharsets.UTF_8);
        byte[] signature = sign(message, kp.getPrivate());

        /* 3. === Print the signature (Base-64 for readability) === */
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }
}