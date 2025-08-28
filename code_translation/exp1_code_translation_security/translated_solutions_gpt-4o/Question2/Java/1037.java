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
        try {
            // Add Bouncy Castle as a Security Provider
            Security.addProvider(new BouncyCastleProvider());

            // Generate key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("P-384");
            keyGen.initialize(ecSpec, new SecureRandom());
            KeyPair keyPair = keyGen.generateKeyPair();

            // Sign the message
            byte[] message = "testing".getBytes();
            byte[] signature = signMessage(keyPair.getPrivate(), message);

            // Verify the signature
            boolean isVerified = verifySignature(keyPair.getPublic(), message, signature);
            System.out.println(isVerified);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] signMessage(PrivateKey privateKey, byte[] message) throws Exception {
        Signature ecdsaSign = Signature.getInstance("SHA384withECDSA", "BC");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }

    public static boolean verifySignature(PublicKey publicKey, byte[] message, byte[] signature) throws Exception {
        Signature ecdsaVerify = Signature.getInstance("SHA384withECDSA", "BC");
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(message);
        return ecdsaVerify.verify(signature);
    }
}