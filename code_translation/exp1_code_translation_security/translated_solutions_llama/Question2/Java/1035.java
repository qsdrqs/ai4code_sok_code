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
        // Generate a new key pair
        AsymmetricCipherKeyPair keyPair = generateKeyPair();

        // Sign a message
        byte[] message = "message".getBytes();
        byte[] signature = sign(message, keyPair);

        // Verify the signature
        boolean isValid = verify(signature, message, keyPair);
        System.out.println("Is signature valid? " + isValid);
    }

    /**
     * Generate a new elliptic curve key pair using NIST P-192 curve.
     *
     * @return AsymmetricCipherKeyPair
     * @throws Exception
     */
    private static AsymmetricCipherKeyPair generateKeyPair() throws Exception {
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
     * @param message  the message to sign
     * @param keyPair  the key pair to use for signing
     * @return the signature
     * @throws Exception
     */
    private static byte[] sign(byte[] message, AsymmetricCipherKeyPair keyPair) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, keyPair.getPrivate());
        int[] signature = signer.generateSignature(message);
        byte[] r = BigInteger.valueOf(signature[0]).toByteArray();
        byte[] s = BigInteger.valueOf(signature[1]).toByteArray();
        byte[] recoveryParam = new byte[]{(byte) signature[2]};
        byte[] signatureBytes = new byte[r.length + s.length + 1];
        System.arraycopy(r, 0, signatureBytes, 0, r.length);
        System.arraycopy(s, 0, signatureBytes, r.length, s.length);
        System.arraycopy(recoveryParam, 0, signatureBytes, r.length + s.length, 1);
        return signatureBytes;
    }

    /**
     * Verify a signature using the provided public key.
     *
     * @param signature  the signature to verify
     * @param message    the original message
     * @param keyPair    the key pair to use for verification
     * @return true if the signature is valid, false otherwise
     * @throws Exception
     */
    private static boolean verify(byte[] signature, byte[] message, AsymmetricCipherKeyPair keyPair) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(false, keyPair.getPublic());
        int r = new BigInteger(1, signature, 0, signature.length / 2).intValue();
        int s = new BigInteger(1, signature, signature.length / 2, signature.length / 2).intValue();
        return signer.verifySignature(message, r, s);
    }
}