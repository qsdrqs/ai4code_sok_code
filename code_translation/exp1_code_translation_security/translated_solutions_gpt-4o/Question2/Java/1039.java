import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;

public class ECDSASignature {

    static {
        // Add BouncyCastle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] signMessage(String message, PrivateKey privateKey) throws Exception {
        // Initialize the ECDSA signature with SHA256
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);

        // Sign the message
        ecdsaSign.update(message.getBytes());
        return ecdsaSign.sign();
    }

    public static void main(String[] args) throws Exception {
        // Example usage (you'll need to provide the private key)
        // PrivateKey privateKey = ...; // Load or generate your private key
        // String message = "message";
        // byte[] signature = signMessage(message, privateKey);
        // System.out.println(Hex.toHexString(signature));
    }
}