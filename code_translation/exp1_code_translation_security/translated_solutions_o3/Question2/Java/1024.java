// Example.java
import ellipticcurve.ecdsa.Ecdsa;
import ellipticcurve.ecdsa.Signature;
import ellipticcurve.PrivateKey;
import ellipticcurve.PublicKey;

public class Example {

    /**
     * Generates an ECDSA signature for the given message with the provided private key.
     *
     * @param message    message to be signed
     * @param privateKey the private key used to sign
     * @return           the resulting ECDSA signature
     */
    public static Signature generateEcdsaSignedMsg(String message, PrivateKey privateKey) {
        return Ecdsa.sign(message, privateKey);
    }

    public static void main(String[] args) {

        // Generate key-pair
        PrivateKey privateKey = new PrivateKey();
        PublicKey  publicKey  = privateKey.publicKey();

        String message = "My test message";

        // Print the signature
        System.out.println(generateEcdsaSignedMsg(message, privateKey));
    }
}