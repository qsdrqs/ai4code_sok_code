import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairGenerationParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws Exception {
        // Generate private and public keys
        AsymmetricCipherKeyPair keyPair = generateKeyPair();

        // Message to be signed
        String message = "Test message";

        // Sign the message
        byte[] signature = sign(message.getBytes(), keyPair.getPrivate());

        // Verify the signature
        boolean isValid = verify(message.getBytes(), signature, keyPair.getPublic());

        System.out.println(isValid);
    }

    /**
     * Generate a private and public key pair using elliptic curve cryptography.
     *
     * @return AsymmetricCipherKeyPair containing private and public keys.
     * @throws Exception if an error occurs during key generation.
     */
    private static AsymmetricCipherKeyPair generateKeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        X9ECParameters params = SECNamedCurves.getByName("secp256r1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, secureRandom));
        return generator.generateKeyPair();
    }

    /**
     * Sign a message using the provided private key.
     *
     * @param message  the message to be signed.
     * @param privateKey the private key used for signing.
     * @return the signature of the message.
     * @throws Exception if an error occurs during signing.
     */
    private static byte[] sign(byte[] message, org.bouncycastle.math.ec.ECPrivateKey privateKey) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKey);
        return signer.generateSignature(message);
    }

    /**
     * Verify a signature using the provided public key.
     *
     * @param message   the original message.
     * @param signature the signature to be verified.
     * @param publicKey the public key used for verification.
     * @return true if the signature is valid, false otherwise.
     * @throws Exception if an error occurs during verification.
     */
    private static boolean verify(byte[] message, byte[] signature, org.bouncycastle.math.ec.ECPublicKey publicKey) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(false, publicKey);
        return signer.verifySignature(message, signature);
    }
}