import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

// Bouncy Castle is a required dependency for secp256k1 curve support.
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class EcdsaSignatureExample {

    /**
     * Signs a message using the private key and verifies it with the public key.
     *
     * @param privateKey The private key (sk) to sign the message with.
     * @param publicKey  The public key (vk) to verify the signature.
     * @param message    The message to sign and verify.
     * @return True if the signature is valid, false otherwise.
     * @throws Exception if any cryptographic error occurs.
     */
    public static boolean signAndVerifyMessage(PrivateKey privateKey, PublicKey publicKey, String message) throws Exception {
        // The message must be converted to bytes. UTF-8 is a standard choice.
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        // 1. Sign the message
        // Get a Signature instance using the "SHA256withECDSA" algorithm
        // The Python library uses a similar standard algorithm implicitly.
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        
        // Initialize the Signature object for signing
        ecdsaSign.initSign(privateKey);
        
        // Provide the data to be signed
        ecdsaSign.update(messageBytes);
        
        // Generate the signature
        byte[] signature = ecdsaSign.sign();

        // 2. Verify the signature
        // Get a new Signature instance for verification
        Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA", "BC");
        
        // Initialize the Signature object for verification
        ecdsaVerify.initVerify(publicKey);
        
        // Provide the original data to be verified
        ecdsaVerify.update(messageBytes);
        
        // Perform the verification and return the result
        return ecdsaVerify.verify(signature);
    }

    public static void main(String[] args) {
        try {
            // The Bouncy Castle provider is required for secp256k1.
            // This line registers the provider, making its algorithms available.
            Security.addProvider(new BouncyCastleProvider());

            // Python: sk = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)
            // In Java, we generate a KeyPair (private and public key) at once.
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA", "BC");
            keyGen.initialize(new ECGenParameterSpec("secp256k1"), new SecureRandom());
            KeyPair keyPair = keyGen.generateKeyPair();
            
            // sk (Signing Key) is the private key
            PrivateKey privateKey = keyPair.getPrivate();
            
            // vk (Verifying Key) is the public key
            PublicKey publicKey = keyPair.getPublic();

            // Python: print(sign_message(sk,".."))
            String message = "..";
            boolean isVerified = signAndVerifyMessage(privateKey, publicKey, message);

            System.out.println("Message: \"" + message + "\"");
            System.out.println("Signature verified: " + isVerified);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}