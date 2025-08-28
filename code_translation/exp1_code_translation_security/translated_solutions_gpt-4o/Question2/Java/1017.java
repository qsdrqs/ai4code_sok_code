import java.security.PrivateKey;
import java.security.Signature;

public class ECDSASignature {

    // Function in Java that signs a given message using a given ECdsa signing key
    public static byte[] signMessage(byte[] message, PrivateKey signingKey) throws Exception {
        // Create a Signature object and initialize it with the private key
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
        ecdsaSign.initSign(signingKey);

        // Update the data to be signed
        ecdsaSign.update(message);

        // Sign the data and return the signature
        return ecdsaSign.sign();
    }
}