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
        // Generate a new ECDSA key pair
        AsymmetricCipherKeyPair keyPair = generateKeyPair();

        // Sign a message
        String message = "something";
        byte[] signature = sign(message, keyPair);

        // Print the signature
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }

    /**
     * Generate a new ECDSA key pair.
     *
     * @return The generated key pair.
     * @throws Exception If an error occurs during key generation.
     */
    public static AsymmetricCipherKeyPair generateKeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        X9ECParameters params = SECNamedCurves.getByName("secp192r1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, secureRandom));
        return generator.generateKeyPair();
    }

    /**
     * Sign a message using the provided key pair.
     *
     * @param message The message to sign.
     * @param keyPair The key pair to use for signing.
     * @return The signature of the message.
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] sign(String message, AsymmetricCipherKeyPair keyPair) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, keyPair.getPrivate());
        byte[] messageBytes = message.getBytes();
        return signer.generateSignature(messageBytes);
    }
}