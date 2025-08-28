import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class ECDSAExample {

    /**
     * Signs the supplied message with the given private key and immediately
     * verifies that signature with the matching public key.
     *
     * @param sk       the EC private key (secp256k1)
     * @param vk       the matching EC public key (secp256k1)
     * @param message  the data to sign
     * @return {@code true} if the signature verifies, {@code false} otherwise
     * @throws Exception on any cryptographic error
     */
    public static boolean signAndVerify(PrivateKey sk,
                                        PublicKey  vk,
                                        byte[]     message) throws Exception {

        // ----- Sign -----
        Signature signer = Signature.getInstance("SHA256withECDSA", "BC");
        signer.initSign(sk, new SecureRandom());
        signer.update(message);
        byte[] signature = signer.sign();

        // ----- Verify -----
        Signature verifier = Signature.getInstance("SHA256withECDSA", "BC");
        verifier.initVerify(vk);
        verifier.update(message);
        return verifier.verify(signature);
    }

    public static void main(String[] args) throws Exception {

        // Make sure the Bouncy Castle provider is registered.
        Security.addProvider(new BouncyCastleProvider());

        // --------- Generate a secp256k1 key pair ---------
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "BC");
        kpg.initialize(new ECGenParameterSpec("secp256k1"), new SecureRandom());
        KeyPair keyPair = kpg.generateKeyPair();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey  vk = keyPair.getPublic();

        // --------- Use the helper to sign & verify ---------
        byte[] message = "..".getBytes(StandardCharsets.UTF_8);
        boolean valid  = signAndVerify(sk, vk, message);

        System.out.println(valid);   // prints: true
    }
}