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
import org.bouncycastle.util.encoders.Hex;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPointSpec;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.util.Base64;

public class ECDSASignature {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // Define the curve parameters
    private static final String CURVE = "secp256r1";
    private static final X9ECParameters params = SECNamedCurves.getByName(CURVE);

    /**
     * Sign a message using a private key.
     *
     * @param message        The message to be signed.
     * @param signingKeyRaw The raw private key.
     * @return The signature in DER format.
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] signNIST256(byte[] message, byte[] signingKeyRaw) throws Exception {
        // Load the private key
        KeyFactory kf = KeyFactory.getInstance("EC", "BC");
        ECPrivateKeySpec privateKeySpec = new ECPrivateKeySpec(new BigInteger(1, signingKeyRaw), params);
        PrivateKey privateKey = kf.generatePrivate(privateKeySpec);

        // Create a signer
        Signature signer = Signature.getInstance("SHA256withECDSA", "BC");
        signer.initSign(privateKey);
        signer.update(message);
        return signer.sign();
    }

    /**
     * Verify a signature using a public key.
     *
     * @param message        The original message.
     * @param signature      The signature to be verified.
     * @param verifyingKeyRaw The raw public key.
     * @return True if the signature is valid, false otherwise.
     * @throws Exception If an error occurs during verification.
     */
    public static boolean verifyNIST256(byte[] message, byte[] signature, byte[] verifyingKeyRaw) throws Exception {
        // Load the public key
        KeyFactory kf = KeyFactory.getInstance("EC", "BC");
        ECPublicKeySpec publicKeySpec = new ECPublicKeySpec(ECPoint.fromAffineX9IntegerEncoding(params.getCurve(), params.getG().getAffineX().add(new BigInteger(1, verifyingKeyRaw)), params.getCurve().getField().getBitLength()), params);
        PublicKey publicKey = kf.generatePublic(publicKeySpec);

        // Create a verifier
        Signature verifier = Signature.getInstance("SHA256withECDSA", "BC");
        verifier.initVerify(publicKey);
        verifier.update(message);
        try {
            verifier.verify(signature);
            return true;
        } catch (SignatureException e) {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        String message = "Hello, World!";
        byte[] signingKeyRaw = Hex.decode("0123456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef");
        byte[] verifyingKeyRaw = Hex.decode("0223456789abcdef0123456789abcdef0123456789abcdef0123456789abcdef");

        byte[] signature = signNIST256(message.getBytes(), signingKeyRaw);
        System.out.println("Signature: " + Hex.toHexString(signature));

        boolean isValid = verifyNIST256(message.getBytes(), signature, verifyingKeyRaw);
        System.out.println("Is signature valid? " + isValid);
    }
}