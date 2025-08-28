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

    /**
     * Signs a given message using a given ECDSA curve.
     *
     * @param message The message to be signed.
     * @param curve   The ECDSA curve to use for signing.
     * @return The signature of the message.
     * @throws Exception If an error occurs during key generation or signing.
     */
    public static byte[] signMessage(byte[] message, String curve) throws Exception {
        // Define the curve
        X9ECParameters params = SECNamedCurves.getByName(curve);

        // Generate a random private key
        SecureRandom secureRandom = new SecureRandom();
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());
        ECKeyPairGenerator generator = ECKeyPairGenerator.getInstance("EC");
        generator.init(new ECKeyGenerationParameters(domainParams, secureRandom));
        AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();

        // Sign the message
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, keyPair.getPrivate());
        int[] signature = signer.generateSignature(message);

        // Convert the signature to a byte array
        byte[] r = new byte[params.getCurve().getFieldSize() / 8];
        byte[] s = new byte[params.getCurve().getFieldSize() / 8];
        System.arraycopy(signature[0].toByteArray(), 0, r, r.length - signature[0].toByteArray().length, signature[0].toByteArray().length);
        System.arraycopy(signature[1].toByteArray(), 0, s, s.length - signature[1].toByteArray().length, signature[1].toByteArray().length);

        // Combine r and s into a single byte array
        byte[] signatureBytes = new byte[r.length + s.length];
        System.arraycopy(r, 0, signatureBytes, 0, r.length);
        System.arraycopy(s, 0, signatureBytes, r.length, s.length);

        return signatureBytes;
    }

    public static void main(String[] args) throws Exception {
        String curve = "secp256r1";
        byte[] message = "Hello, World!".getBytes();
        byte[] signature = signMessage(message, curve);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
    }
}