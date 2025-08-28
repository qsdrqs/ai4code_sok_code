import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.util.encoders.Hex;

public class ECDSASignature {

    /**
     * Signs a given message using a given ECDSA signing key
     *
     * @param message     The message to be signed
     * @param signingKey  The ECDSA private key
     * @return The signature in DER format
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available
     */
    public static byte[] signMessage(byte[] message, ECPrivateKeyParameters signingKey) throws NoSuchAlgorithmException {
        // Hash the message using SHA-256
        MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        byte[] messageHash = sha256.digest(message);

        // Sign the message hash using ECDSA
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
        signer.init(true, signingKey);
        BigInteger[] signatureComponents = signer.generateSignature(messageHash);

        // Encode the signature in DER format
        return encodeToDER(signatureComponents);
    }

    /**
     * Encodes the signature components (r, s) into DER format
     *
     * @param signatureComponents The signature components (r, s)
     * @return The DER-encoded signature
     */
    private static byte[] encodeToDER(BigInteger[] signatureComponents) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DEROutputStream derOut = new DEROutputStream(baos);
            ASN1EncodableVector v = new ASN1EncodableVector();
            v.add(new ASN1Integer(signatureComponents[0]));
            v.add(new ASN1Integer(signatureComponents[1]));
            derOut.writeObject(new DERSequence(v));
            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Error encoding signature to DER format", e);
        }
    }
}