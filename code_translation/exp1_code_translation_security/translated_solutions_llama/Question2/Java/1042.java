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
     * Signs a given message using ECDSA signing key
     * 
     * @param message      message to be signed
     * @param privateKey   private key to sign the message
     * @return signature of the message
     * @throws Exception if an error occurs during signing
     */
    public static byte[] signMessage(byte[] message, byte[] privateKey) throws Exception {
        // Define the curve parameters
        X9ECParameters params = SECNamedCurves.getByName("secp256k1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        // Create the private key
        org.bouncycastle.math.ec.ECPrivateKeyParameters privateKeyParams = new org.bouncycastle.math.ec.ECPrivateKeyParameters(new java.math.BigInteger(1, privateKey), domainParams);

        // Create the signer
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKeyParams);

        // Sign the message
        byte[] signature = signer.generateSignature(message);

        return signature;
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        String message = "Hello, World!";
        byte[] messageBytes = message.getBytes();

        // Replace with your private key
        byte[] privateKey = new byte[32]; // 256-bit private key
        SecureRandom random = new SecureRandom();
        random.nextBytes(privateKey);

        byte[] signature = signMessage(messageBytes, privateKey);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
    }
}