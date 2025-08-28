import java.security.Signature;
import java.security.PrivateKey;
import java.security.PublicKey;

public class ECDSAUtils {

    /**
     * Function that signs a given message using an ECDSA signing key.
     *
     * @param privateKey ECDSA signing key (PrivateKey)
     * @param message    Message to be signed
     * @return Signature in byte array
     * @throws Exception If signing fails
     */
    public static byte[] sign(PrivateKey privateKey, byte[] message) throws Exception {
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }

    /**
     * Function that verifies a given signature using an ECDSA verification key.
     *
     * @param publicKey ECDSA verification key (PublicKey)
     * @param message   Message to be verified
     * @param signature Signature to be verified
     * @return True if the signature is valid, False otherwise
     * @throws Exception If verification fails
     */
    public static boolean verify(PublicKey publicKey, byte[] message, byte[] signature) throws Exception {
        Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(message);
        return ecdsaVerify.verify(signature);
    }
}