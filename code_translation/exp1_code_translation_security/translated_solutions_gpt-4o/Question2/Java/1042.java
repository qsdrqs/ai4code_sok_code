import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.util.encoders.Hex;
import java.security.Security;
import java.security.Signature;
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;

public class ECDSASignature {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] signMessage(byte[] message, String privateKeyHex) throws Exception {
        // Decode the private key from hex
        byte[] privateKeyBytes = Hex.decode(privateKeyHex);

        // Get the EC parameter spec for SECP256k1
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");

        // Create a private key from the byte array
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // Initialize the signature with the private key
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);

        // Sign the message
        ecdsaSign.update(message);
        byte[] signature = ecdsaSign.sign();

        return signature;
    }

    public static void main(String[] args) {
        try {
            String message = "Your message here";
            String privateKeyHex = "your_private_key_in_hex";

            byte[] signature = signMessage(message.getBytes(), privateKeyHex);
            System.out.println("Signature: " + Hex.toHexString(signature));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}