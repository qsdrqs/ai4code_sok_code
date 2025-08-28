import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class EcdsaSignature {

    /**
     * Signs a message using the provided ECDSA private key.
     *
     * @param privateKey The private key (sk).
     * @param message    The message to sign.
     * @return The signature bytes.
     * @throws Exception if any cryptographic error occurs.
     */
    public static byte[] sign(PrivateKey privateKey, byte[] message) throws Exception {
        // For the NIST P-384 curve, the standard signature algorithm is SHA384withECDSA
        Signature ecdsaSign = Signature.getInstance("SHA384withECDSA");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }

    public static void main(String[] args) {
        try {
            // The message to be signed, equivalent to Python's b"hehehe"
            byte[] message = "hehehe".getBytes(StandardCharsets.UTF_8);

            // Generate an ECDSA key pair using the NIST P-384 curve (secp384r1)
            // This is equivalent to sk = SigningKey.generate(curve=NIST384p)
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            keyGen.initialize(new ECGenParameterSpec("secp384r1"), new SecureRandom());
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate(); // This is 'sk'
            PublicKey publicKey = keyPair.getPublic();   // This is 'sk.verifying_key'

            // Sign the message, equivalent to sig = sign(sk, message)
            byte[] signature = sign(privateKey, message);

            // Verify the signature
            // This is equivalent to sk.verifying_key.verify(sig, message)
            Signature ecdsaVerify = Signature.getInstance("SHA384withECDSA");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(message);
            boolean isVerified = ecdsaVerify.verify(signature);

            // Print the verification result
            System.out.println(isVerified);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}