import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

// Bouncy Castle is a widely used provider for more advanced cryptographic algorithms in Java.
// It needs to be added as a dependency to your project.
// For Maven, add this to your pom.xml:
// <dependency>
//     <groupId>org.bouncycastle</groupId>
//     <artifactId>bcprov-jdk18on</artifactId>
//     <version>1.77</version>
// </dependency>
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class EcdsaSignatureExample {

    /**
     * Signs a given message using a given ECDSA private key.
     * Note: The Python ecdsa library defaults to SHA-1, but SHA256withECDSA is a more secure
     * and modern standard, so it is used here.
     *
     * @param message    The input message as a byte array.
     * @param privateKey The ECDSA PrivateKey used to sign the message.
     * @return The signature as a byte array.
     * @throws GeneralSecurityException if a security-related error occurs.
     */
    public static byte[] sign(byte[] message, PrivateKey privateKey) throws GeneralSecurityException {
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA", "BC");
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }

    public static void main(String[] args) {
        try {
            // Register Bouncy Castle as a security provider
            Security.addProvider(new BouncyCastleProvider());

            // Python: sk = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)
            // In Java, we generate a KeyPair which contains both the private and public keys.
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ECDSA", "BC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
            keyPairGenerator.initialize(ecSpec, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            
            PrivateKey privateKey = keyPair.getPrivate(); // This is 'sk'
            PublicKey publicKey = keyPair.getPublic();     // This is 'vk'

            // The message to be signed
            byte[] message = "helloworld".getBytes(StandardCharsets.UTF_8);

            // Python: signed_message = sign(b"helloworld", sk)
            byte[] signature = sign(message, privateKey);

            // Python: print(vk.verify(signed_message, b"helloworld"))
            // In Java, verification is also done using the Signature class
            Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(message);
            boolean result = ecdsaVerify.verify(signature);

            // Print the verification result (will be 'true')
            System.out.println(result);

        } catch (GeneralSecurityException e) {
            System.err.println("A security error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}