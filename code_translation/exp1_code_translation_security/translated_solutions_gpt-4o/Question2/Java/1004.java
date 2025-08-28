import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.math.BigInteger;

public class ECDSASignatureExample {

    static {
        // Add Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    public static byte[] signMessage(byte[] message, byte[] privateKeyBytes) throws Exception {
        // Get the curve parameters
        ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("secp256k1");

        // Create a private key from the provided bytes
        BigInteger privateKeyInt = new BigInteger(1, privateKeyBytes);
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(privateKeyInt, ecSpec);
        KeyFactory keyFactory = KeyFactory.getInstance("ECDSA", "BC");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // Create a Signature object and initialize it with the private key
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);

        // Sign the message
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }

    public static void main(String[] args) {
        try {
            String message = "hello";
            String privateKeyHex = "71776572747975696f706173646667686a6b6c7a786376626e6d717765727479"; // Hex representation of the private key

            byte[] messageBytes = message.getBytes("UTF-8");
            byte[] privateKeyBytes = Hex.decode(privateKeyHex);

            byte[] signature = signMessage(messageBytes, privateKeyBytes);
            System.out.println("Signature: " + Hex.toHexString(signature));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}