import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class ECDSAExample {

    /**
     * Replicates the behavior of the original Python `myfunction`.
     *
     * Generates a new secp256k1 signing key and immediately signs the
     * supplied message.
     *
     * @param message the data to be signed
     * @return the ECDSA signature bytes
     * @throws Exception if any cryptographic operation fails
     */
    public static byte[] myFunction(byte[] message) throws Exception {

        // 1. Generate a fresh EC key pair on the secp256k1 curve
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyPairGen.initialize(ecSpec, new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 2. Sign the message
        Signature signature = Signature.getInstance("SHA256withECDSA"); // or "NONEwithECDSA"
        signature.initSign(keyPair.getPrivate());
        signature.update(message);

        return signature.sign();
    }
}