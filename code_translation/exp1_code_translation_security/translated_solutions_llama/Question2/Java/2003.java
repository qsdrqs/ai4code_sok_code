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
        AsymmetricCipherKeyPair keyPair = generateECDSAKeyPair();

        // Sign a message
        String message = "Hello world";
        byte[] signature = sign(message.getBytes(), keyPair);

        // Print the signature
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }

    /**
     * Generate a new ECDSA key pair.
     *
     * @return The generated key pair.
     * @throws Exception If an error occurs during key generation.
     */
    private static AsymmetricCipherKeyPair generateECDSAKeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        X9ECParameters params = SECNamedCurves.getByName("secp256r1");
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
    private static byte[] sign(byte[] message, AsymmetricCipherKeyPair keyPair) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, keyPair.getPrivate());
        int[] signature = signer.generateSignature(message);
        byte[] r = BigInteger.valueOf(signature[0]).toByteArray();
        byte[] s = BigInteger.valueOf(signature[1]).toByteArray();
        byte[] recoveryParameter = new byte[]{(byte) signature[2]};

        // Combine r, s, and recovery parameter into a single byte array
        byte[] signatureBytes = new byte[r.length + s.length + 1];
        System.arraycopy(recoveryParameter, 0, signatureBytes, 0, 1);
        System.arraycopy(r, 0, signatureBytes, 1, r.length);
        System.arraycopy(s, 0, signatureBytes, 1 + r.length, s.length);

        return signatureBytes;
    }
}