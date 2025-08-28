import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
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
import org.bouncycastle.util.encoders.Base64;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.util.Base;

public class Main {

    // Define the curve
    private static final String CURVE = "secp256k1";

    public static void main(String[] args) throws Exception {
        // Example usage
        String message = "Hello, World!";
        byte[] privateKey = generatePrivateKey();
        byte[] signature = signMessage(message.getBytes(), privateKey);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
    }

    /**
     * Generate a private key for the secp256k1 curve.
     *
     * @return private key as a byte array
     * @throws Exception if an error occurs during key generation
     */
    public static byte[] generatePrivateKey() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec(CURVE);
        kpg.initialize(ecSpec);
        KeyPair kp = kpg.generateKeyPair();
        PrivateKey privateKey = kp.getPrivate();
        return privateKey.getEncoded();
    }

    /**
     * Sign a message using the provided private key.
     *
     * @param message     the message to sign
     * @param privateKey  the private key to use for signing
     * @return the signature as a byte array
     * @throws Exception if an error occurs during signing
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey) throws Exception {
        // Load the private key
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory kf = KeyFactory.getInstance("EC", "BC");
        PrivateKey privateKeyLoaded = kf.generatePrivate(privateKeySpec);

        // Create an ECDSA signer
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKeyLoaded);

        // Sign the message
        BigInteger[] signature = signer.generateSignature(message);

        // Convert the signature to a byte array
        byte[] r = signature[0].toByteArray();
        byte[] s = signature[1].toByteArray();
        byte[] recoveryParameter = new byte[]{(byte) signature[2].intValue()};
        int rLen = r.length;
        int sLen = s.length;
        byte[] signatureBytes = new byte[rLen + sLen + 1];
        System.arraycopy(r, 0, signatureBytes, 0, rLen);
        System.arraycopy(s, 0, signatureBytes, rLen, sLen);
        System.arraycopy(recoveryParameter, 0, signatureBytes, rLen + sLen, 1);

        return signatureBytes;
    }
}