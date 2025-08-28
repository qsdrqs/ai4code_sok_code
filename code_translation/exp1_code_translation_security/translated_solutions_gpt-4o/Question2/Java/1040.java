import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.ECPointUtil;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ECDSASignature {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] signMessage(byte[] message, byte[] privateKeyBytes) throws Exception {
        // Get the EC parameter spec for the SECP256k1 curve
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");

        // Create a private key from the provided bytes
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
            // Example usage
            String message = "Hello, World!";
            byte[] messageBytes = message.getBytes();

            // Example private key in hexadecimal format (replace with your own)
            String privateKeyHex = "your_private_key_in_hex";
            byte[] privateKeyBytes = Hex.decode(privateKeyHex);

            byte[] signature = signMessage(messageBytes, privateKeyBytes);
            System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}