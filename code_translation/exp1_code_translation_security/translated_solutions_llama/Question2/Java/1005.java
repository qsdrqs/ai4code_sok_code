import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairGenerationParameters;
import org.bouncycastle.crypto.params.ECParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    public static byte[] sign(byte[] msg, AsymmetricCipherKeyPair keyPair) {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, keyPair.getPrivate());
        BigInteger[] signature = signer.generateSignature(msg);
        // Convert signature to DER format
        byte[] derSignature = ECDSASigner.encodeSignature(signature[0], signature[1]);
        // Verify the signature
        ECDSASigner verifier = new ECDSASigner();
        verifier.init(false, keyPair.getPublic());
        boolean isValid = verifier.verifySignature(msg, signature[0], signature[1]);
        if (!isValid) {
            throw new RuntimeException("Invalid signature");
        }
        return derSignature;
    }

    public static void main(String[] args) throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        X9ECParameters params = SECNamedCurves.getByName("secp256r1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, secureRandom));
        AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();

        byte[] message = "message".getBytes();
        byte[] signature = sign(message, keyPair);
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }
}