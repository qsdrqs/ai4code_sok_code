import java.security.PrivateKey;
import java.security.Signature;

public class ECDsaUtils {

    /**
     * Signs the supplied message using the provided ECDSA private key.
     *
     * @param message     The data to sign (as a byte array).
     * @param signingKey  A java.security.PrivateKey representing the ECDSA private key.
     * @return            The generated signature bytes.
     *
     * @throws Exception  If any cryptographic operation fails.
     */
    public static byte[] signMessage(byte[] message, PrivateKey signingKey) throws Exception {
        // 1. Obtain a Signature object for the desired ECDSA algorithm.
        //    You may replace "SHA256withECDSA" with a different hash curve combo if needed.
        Signature signer = Signature.getInstance("SHA256withECDSA");

        // 2. Initialize the signer with the private key.
        signer.initSign(signingKey);

        // 3. Supply the message to be signed.
        signer.update(message);

        // 4. Generate and return the signature.
        return signer.sign();
    }
}