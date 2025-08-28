import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.jce.spec.ECPrivateKeySpec;
import org.bouncycastle.jce.spec.ECPublicKeySpec;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

public class ECDSASignatureExample {
    public static void main(String[] args) {
        try {
            // Add Bouncy Castle as a Security Provider
            Security.addProvider(new BouncyCastleProvider());

            // Generate key pair using NIST P-192 curve
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECParameterSpec ecSpec = ECNamedCurveTable.getParameterSpec("P-192");
            keyPairGenerator.initialize(ecSpec, new SecureRandom());

            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // Message to be signed
            byte[] message = "message".getBytes();

            // Sign the message
            byte[] signature = signMessage(message, privateKey);

            // Verify the signature
            boolean isVerified = verifySignature(message, signature, publicKey);

            // Assert the verification
            assert isVerified : "Signature verification failed";

            System.out.println("Signature verified successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] signMessage(byte[] message, PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signer = Signature.getInstance("SHA256withECDSA", "BC");
        signer.initSign(privateKey);
        signer.update(message);
        return signer.sign();
    }

    public static boolean verifySignature(byte[] message, byte[] signature, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature verifier = Signature.getInstance("SHA256withECDSA", "BC");
        verifier.initVerify(publicKey);
        verifier.update(message);
        return verifier.verify(signature);
    }
}