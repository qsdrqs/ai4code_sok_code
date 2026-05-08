import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ECDSAExample {

    /**
     * Signs a given message using a given ECDSA signing key
     *
     * @param message input message
     * @param key     ECDSA key used to sign the message
     * @return signed message
     */
    public static byte[] sign(byte[] message, PrivateKey key) throws Exception {
        Signature signature = Signature.getInstance("SHA1withECDSA", "BC");
        signature.initSign(key);
        signature.update(message);
        return signature.sign();
    }

    public static void main(String[] args) throws Exception {
        // Register BouncyCastle as a security provider
        Security.addProvider(new BouncyCastleProvider());

        // Generate an ECDSA key pair using the SECP256k1 curve
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyPairGenerator.initialize(ecSpec);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey sk = keyPair.getPrivate();        // signing key
        PublicKey vk = keyPair.getPublic();          // verifying key

        // Sign the message
        byte[] signedMessage = sign("helloworld".getBytes(), sk);

        // Verify the signature
        Signature verifier = Signature.getInstance("SHA1withECDSA", "BC");
        verifier.initVerify(vk);
        verifier.update("helloworld".getBytes());
        boolean isValid = verifier.verify(signedMessage);

        System.out.println(isValid);
    }
}