import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.interfaces.ECPrivateKey;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.ECPrivateKeySpec;

public class EcdsaExample {
    public static void main(String[] args) {
        try {
            // Add BouncyCastle as a Security Provider
            Security.addProvider(new BouncyCastleProvider());

            // Generate EC key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyGen.initialize(ecSpec, new SecureRandom());
            KeyPair keyPair = keyGen.generateKeyPair();

            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            String message = "Test message";

            // Sign the message
            Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
            ecdsaSign.initSign(privateKey);
            ecdsaSign.update(message.getBytes("UTF-8"));
            byte[] signature = ecdsaSign.sign();

            // Verify the signature
            Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(message.getBytes("UTF-8"));
            boolean isVerified = ecdsaVerify.verify(signature);

            System.out.println(isVerified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}