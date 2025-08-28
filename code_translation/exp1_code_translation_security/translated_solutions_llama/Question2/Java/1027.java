import java.security.Signature;
import java.security.SignedObject;
import java.security.PrivateKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public class Main {

    /**
     * Signs a message with a given signing key.
     * 
     * @param message     The message to be signed.
     * @param signingKey  The private key used for signing.
     * @return            The signed message.
     * @throws NoSuchAlgorithmException If the algorithm is not found.
     * @throws InvalidKeyException       If the key is invalid.
     * @throws SignatureException        If there's an issue with the signature.
     */
    public static byte[] signMessage(byte[] message, PrivateKey signingKey) 
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // Initialize the signature object with the signing key and algorithm
        Signature signature = Signature.getInstance("SHA256withRSA"); // Use a suitable algorithm
        signature.initSign(signingKey);

        // Update the signature with the message
        signature.update(message);

        // Sign the message
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        // Replace with your actual signing key and message
        // PrivateKey signingKey = ...;
        // byte[] message = ...;
        // byte[] signedMessage = signMessage(message, signingKey);
    }
}