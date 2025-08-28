import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECParams;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    /**
     * Signs a given message using a given ECDSA signing key
     *
     * @param message input message
     * @param privateKey ECDSA private key used to sign the message
     * @return signed message
     */
    public static byte[][] sign(byte[] message, byte[] privateKey) {
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, new org.bouncycastle.crypto.params.ECPrivateKeyParameters(privateKey, params));
        BigInteger[] signature = signer.generateSignature(message);
        return new byte[][]{signature[0].toByteArray(), signature[1].toByteArray()};
    }

    /**
     * Verifies a given message using a given ECDSA verifying key and signature
     *
     * @param message input message
     * @param publicKey ECDSA public key used to verify the message
     * @param signature ECDSA signature
     * @return true if the signature is valid, false otherwise
     */
    public static boolean verify(byte[] message, byte[] publicKey, byte[] r, byte[] s) {
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDSASigner signer = new ECDSASigner();
        signer.init(false, new org.bouncycastle.crypto.params.ECPublicKeyParameters(publicKey, params));
        BigInteger[] signature = new BigInteger[]{new BigInteger(1, r), new BigInteger(1, s)};
        return signer.verifySignature(message, signature[0], signature[1]);
    }

    public static void main(String[] args) throws Exception {
        // Generate a new ECDSA key pair
        SecureRandom secureRandom = new SecureRandom();
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, secureRandom));
        AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();
        byte[] privateKey = ((org.bouncycastle.crypto.params.ECPrivateKeyParameters) keyPair.getPrivate()).getD().toByteArray();
        byte[] publicKey = ((org.bouncycastle.crypto.params.ECPublicKeyParameters) keyPair.getPublic()).getQ().getEncoded(false);

        // Sign a message
        byte[] message = "helloworld".getBytes();
        byte[][] signature = sign(message, privateKey);

        // Verify the signature
        boolean isValid = verify(message, publicKey, signature[0], signature[1]);
        System.out.println(isValid);
    }
}