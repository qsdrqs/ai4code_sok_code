import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class ECDSASignDemo {

    // ------------------------------------------------------------------
    // Equivalent to  `def sign(inp, key): return key.sign(inp.encode('utf-8'))`
    // ------------------------------------------------------------------
    public static byte[] sign(String message, PrivateKey privateKey) throws Exception {
        Signature signer = Signature.getInstance("SHA256withECDSA");
        signer.initSign(privateKey);
        signer.update(message.getBytes("UTF-8"));
        return signer.sign();
    }

    public static void main(String[] args) throws Exception {
        // --------------------------------------------------------------
        // Roughly equivalent to  `sk = SigningKey.generate()`
        // --------------------------------------------------------------
        KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");

        // Same curve the Python ecdsa library defaults to (secp256k1):
        generator.initialize(new ECGenParameterSpec("secp256k1"));

        KeyPair keyPair = generator.generateKeyPair();

        // --------------------------------------------------------------
        // Sign and print result
        // --------------------------------------------------------------
        byte[] signature = sign("Hello world", keyPair.getPrivate());

        // Print in a readable form (Base-64)
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }
}