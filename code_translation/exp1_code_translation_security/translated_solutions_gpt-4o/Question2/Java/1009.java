import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class ECDSASignatureExample {

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        try {
            // Generate key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("P-384");
            keyPairGenerator.initialize(ecSpec, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // Sign the message
            byte[] message = "hehehe".getBytes();
            byte[] signature = sign(keyPair.getPrivate(), message);

            // Verify the signature
            boolean isVerified = verify(keyPair.getPublic(), message, signature);
            System.out.println(isVerified);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] sign(PrivateKey privateKey, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature ecdsaSign = Signature.getInstance("SHA384withECDSA", "BC");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }

    public static boolean verify(PublicKey publicKey, byte[] message, byte[] signature) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature ecdsaVerify = Signature.getInstance("SHA384withECDSA", "BC");
        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(message);
        return ecdsaVerify.verify(signature);
    }
}