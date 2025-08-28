import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;
import java.security.spec.ECGenParameterSpec;

public class ECDSASignatureExample {

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        try {
            // Generate a key pair using the NIST P-192 curve
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            keyPairGenerator.initialize(ecSpec, new SecureRandom());

            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();

            // Sign the message
            String message = "something";
            byte[] signature = sign(message, privateKey);

            // Print the signature in hexadecimal format
            System.out.println(Hex.toHexString(signature));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] sign(String message, PrivateKey privateKey) throws Exception {
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message.getBytes("UTF-8"));
        return ecdsaSign.sign();
    }
}