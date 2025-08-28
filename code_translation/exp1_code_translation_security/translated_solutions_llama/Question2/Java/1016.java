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

public class ECDSASignerUtil {

    /**
     * Signs a given message using a given ECDSA signing private key.
     *
     * @param message     the message to be signed
     * @param privateKey  the ECDSA private key in bytes
     * @return the signed message in bytes
     * @throws Exception if an error occurs during signing
     */
    public static byte[] sign(byte[] message, byte[] privateKey) throws Exception {
        // Define the curve parameters
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        // Create a new ECDSA signer
        ECDSASigner signer = new ECDSASigner();

        // Initialize the signer with the private key
        signer.init(true, new org.bouncycastle.crypto.params.ECPrivateKeyParameters(privateKey, domainParams));

        // Sign the message
        byte[] signature = signer.generateSignature(message);

        // Return the signed message
        return signature;
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        String message = "Hello, World!";
        byte[] privateKey = generatePrivateKey(); // Generate a private key for demonstration purposes
        byte[] signedMessage = sign(message.getBytes(), privateKey);
        System.out.println("Signed Message: " + Base64.getEncoder().encodeToString(signedMessage));
    }

    // Helper method to generate a private key for demonstration purposes
    private static byte[] generatePrivateKey() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, secureRandom));
        AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();
        byte[] privateKey = ((org.bouncycastle.crypto.params.ECPrivateKeyParameters) keyPair.getPrivate()).getD();
        return privateKey;
    }
}