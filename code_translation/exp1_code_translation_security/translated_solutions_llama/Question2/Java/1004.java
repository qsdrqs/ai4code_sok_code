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
     * Function signMessage
     *
     * @param message     the message to be signed
     * @param privateKey  the private key for signing
     * @return            the signature of the message
     * @throws Exception  if an error occurs during signing
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey) throws Exception {
        // Define the curve parameters
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        // Create a new ECDSA key pair generator
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyPairGenerationParameters(domainParams, new SecureRandom()));

        // Generate a key pair (not actually used, but required for curve)
        AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();

        // Load the private key
        java.math.BigInteger privateKeyBigInt = new java.math.BigInteger(1, privateKey);

        // Sign the message
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, new org.bouncycastle.crypto.params.ECPrivateKeyParameters(privateKeyBigInt, domainParams));
        byte[] signature = signer.generateSignature(message);

        return signature;
    }

    public static void main(String[] args) throws Exception {
        String message = "hello";
        String privateKey = "qwertyuiopasdfghjklzxcvbnmqwerty";
        byte[] messageBytes = message.getBytes("UTF-8");
        byte[] privateKeyBytes = privateKey.getBytes("UTF-8");

        byte[] signature = signMessage(messageBytes, privateKeyBytes);
        System.out.println(Base64.getEncoder().encodeToString(signature));
    }
}