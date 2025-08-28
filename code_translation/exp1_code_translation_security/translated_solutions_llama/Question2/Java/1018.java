import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairGenerationParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECCustomNamedCurves;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws Exception {
        // Generate a key pair (for demonstration purposes)
        KeyPair keyPair = generateKeyPair();

        // Sign a message
        String msg = "message";
        byte[] signature = sign(msg.getBytes(), keyPair.getPrivate());

        // Print the signature
        System.out.println("Signature: " + Hex.toHexString(signature));
    }

    /**
     * Generate an ECDSA key pair.
     *
     * @return A key pair.
     * @throws Exception If an error occurs during key generation.
     */
    public static KeyPair generateKeyPair() throws Exception {
        // Initialize the Bouncy Castle provider
        Security.addProvider(new BouncyCastleProvider());

        // Define the curve (e.g., secp256r1)
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");

        // Generate the key pair
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("ECDSA", "BC");
        kpg.initialize(ecSpec);

        return kpg.generateKeyPair();
    }

    /**
     * Sign a message using the provided private key.
     *
     * @param msg  The message to sign.
     * @param priv The private key.
     * @return The signature.
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] sign(byte[] msg, PrivateKey priv) throws Exception {
        // Create an ECDSA signer
        Signature signature = Signature.getInstance("SHA256withECDSA", "BC");
        signature.initSign(priv);
        signature.update(msg);
        return signature.sign();
    }
}