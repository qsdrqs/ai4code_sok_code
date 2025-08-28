import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.nio.charset.StandardCharsets;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ECDSAExample {

    // Add Bouncy Castle as a security provider
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        // Generate ECDSA key pair using SECP256k1 curve
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA", "BC");
        kpg.initialize(new ECGenParameterSpec("secp256k1"));
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();

        // Sign and verify the message
        String message = "..";
        boolean result = signMessage(sk, vk, message);
        System.out.println(result);
    }

    /**
     * Signs a message using the private key and verifies it using the public key.
     *
     * @param sk      The private key
     * @param vk      The public key
     * @param message The message to sign
     * @return true if the signature is valid, false otherwise
     * @throws Exception if an error occurs during signing or verification
     */
    public static boolean signMessage(PrivateKey sk, PublicKey vk, String message) throws Exception {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        // Sign the message
        Signature signature = Signature.getInstance("ECDSA", "BC");
        signature.initSign(sk);
        signature.update(messageBytes);
        byte[] sigBytes = signature.sign();

        // Verify the signature
        signature.initVerify(vk);
        signature.update(messageBytes);
        return signature.verify(sigBytes);
    }
}