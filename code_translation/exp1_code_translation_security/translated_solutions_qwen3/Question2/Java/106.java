import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Security;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ECDSASignature {

    static {
        // Add Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        // Generate ECDSA key pair using SECP256k1 curve
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA", "BC");
        kpg.initialize(new ECGenParameterSpec("secp256k1"));
        KeyPair keyPair = kpg.generateKeyPair();
        ECPrivateKey privateKey = (ECPrivateKey) keyPair.getPrivate();
        ECPublicKey publicKey = (ECPublicKey) keyPair.getPublic();

        // Message to sign
        byte[] message = "helloworld".getBytes();

        // Sign the message
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
        signature.initSign(privateKey);
        signature.update(message);
        byte[] signedMessage = signature.sign();

        // Verify the signature
        signature.initVerify(publicKey);
        signature.update(message);
        boolean verified = signature.verify(signedMessage);

        // Output the result of verification
        System.out.println(verified); // Should print: true
    }
}