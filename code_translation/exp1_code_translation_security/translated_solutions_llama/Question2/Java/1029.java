import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairGenerationParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.*;

public class ECDSASignature {

    // Define the curve parameters
    private static final String CURVE_NAME = "secp384r1";

    static {
        // Add Bouncy Castle provider
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Generate ECDSA key pair
     *
     * @return KeyPair
     * @throws Exception
     */
    public static KeyPair ecdsaGenKey() throws Exception {
        // Get the curve parameters
        X9ECParameters params = SECNamedCurves.getByName(CURVE_NAME);
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        // Generate the key pair
        ECKeyPairGenerator generator = ECKeyPairGenerator.getInstance("ECDSA", "BC");
        generator.initialize(new ECKeyGenerationParameters(domainParams, new SecureRandom()));

        AsymmetricCipherKeyPair keyPair = generator.generateKeyPair();

        // Return the key pair
        return new KeyPair(keyPair.getPublic(), keyPair.getPrivate());
    }

    /**
     * Sign a message using ECDSA
     *
     * @param privateKey Private key
     * @param message    Message to sign
     * @return Signature
     * @throws Exception
     */
    public static byte[] ecdsaSign(PrivateKey privateKey, byte[] message) throws Exception {
        // Create the ECDSA signer
        ECDSASigner signer = new ECDSASigner();

        // Initialize the signer with the private key
        signer.init(true, privateKey);

        // Sign the message
        return signer.processBlock(message, 0, message.length);
    }

    /**
     * Verify a signature using ECDSA
     *
     * @param publicKey  Public key
     * @param signature  Signature
     * @param message    Message
     * @return Verification result
     * @throws Exception
     */
    public static boolean ecdsaVerify(PublicKey publicKey, byte[] signature, byte[] message) throws Exception {
        // Create the ECDSA signer
        ECDSASigner signer = new ECDSASigner();

        // Initialize the signer with the public key
        signer.init(false, publicKey);

        // Verify the signature
        return signer.verifyBlock(signature, 0, signature.length, message, 0, message.length);
    }

    public static void main(String[] args) throws Exception {
        // Generate a key pair
        KeyPair keyPair = ecdsaGenKey();

        // Print the private and public keys
        System.out.println("Private Key: " + Hex.toHexString(keyPair.getPrivate().getEncoded()));
        System.out.println("Public Key: " + Hex.toHexString(keyPair.getPublic().getEncoded()));

        // Define a message
        byte[] message = "This is a test message".getBytes();

        // Sign the message
        byte[] signature = ecdsaSign(keyPair.getPrivate(), message);

        // Print the signature
        System.out.println("Signature: " + Hex.toHexString(signature));

        // Verify the signature
        boolean verificationResult = ecdsaVerify(keyPair.getPublic(), signature, message);

        // Print the verification result
        System.out.println("Verification Result: " + verificationResult);
    }
}