import java.security.*;
import java.security.spec.*;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;

public class ECDSASignature {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] sign(byte[] message, byte[] privateKeyBytes) throws Exception {
        // Create a private key specification
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // Initialize the signer
        ECDSASigner signer = new ECDSASigner();
        ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(
                ((ECPrivateKeySpec) keyFactory.getKeySpec(privateKey, ECPrivateKeySpec.class)).getD(),
                ECNamedCurveTable.getParameterSpec("secp256k1"));
        signer.init(true, new ParametersWithRandom(privateKeyParameters, new SecureRandom()));

        // Hash the message
        SHA256Digest digest = new SHA256Digest();
        digest.update(message, 0, message.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);

        // Sign the hash
        BigInteger[] signatureComponents = signer.generateSignature(hash);
        byte[] r = signatureComponents[0].toByteArray();
        byte[] s = signatureComponents[1].toByteArray();

        // Concatenate r and s to form the signature
        byte[] signature = new byte[r.length + s.length];
        System.arraycopy(r, 0, signature, 0, r.length);
        System.arraycopy(s, 0, signature, r.length, s.length);

        return signature;
    }

    public static void main(String[] args) {
        try {
            // Example usage
            String message = "Hello, World!";
            byte[] messageBytes = message.getBytes();

            // Example private key in hexadecimal format (replace with actual key)
            String privateKeyHex = "your_private_key_in_hex";
            byte[] privateKeyBytes = Hex.decode(privateKeyHex);

            byte[] signedMessage = sign(messageBytes, privateKeyBytes);
            System.out.println("Signed Message: " + Hex.toHexString(signedMessage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}