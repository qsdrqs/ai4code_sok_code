import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Base64;

/**
 * A Java class to perform ECDSA signing, equivalent to the provided Python code.
 */
public class EcdsaSigner {

    /**
     * Signs a message using the ECDSA algorithm with a given private key.
     *
     * Note: The original Python code ignores the 'msg' parameter and always signs
     * the literal byte string b"message". This translation faithfully reproduces that behavior.
     * The standard JCA algorithm "SHA256withECDSA" is used here as a modern, secure default.
     *
     * @param msg The message to be signed (this parameter is ignored, as in the original Python code).
     * @param privateKey The ECDSA private key to use for signing.
     * @return The raw byte array of the signature.
     * @throws NoSuchAlgorithmException If the signature algorithm is not available.
     * @throws InvalidKeyException If the provided key is invalid.
     * @throws SignatureException If an error occurs during the signing process.
     */
    public byte[] sign(String msg, PrivateKey privateKey)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        // 1. Get an instance of the Signature object for ECDSA.
        //    "SHA256withECDSA" is a standard and secure choice.
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");

        // 2. Initialize the Signature object for signing with the private key.
        ecdsaSign.initSign(privateKey);

        // 3. Provide the data to be signed.
        //    The Python code signs the literal b"message", so we do the same here.
        byte[] messageBytes = "message".getBytes(StandardCharsets.UTF_8);
        ecdsaSign.update(messageBytes);

        // 4. Generate the signature and return it.
        byte[] signature = ecdsaSign.sign();
        return signature;
    }

    /**
     * A main method to demonstrate the signing and verification process.
     */
    public static void main(String[] args) {
        try {
            // 1. Generate an ECDSA key pair.
            //    For demonstration, we use the secp256r1 curve (256 bits).
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            keyGen.initialize(256, new SecureRandom());
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            System.out.println("Generated Private Key (first 32 bytes): " + Base64.getEncoder().encodeToString(privateKey.getEncoded()).substring(0, 32) + "...");
            System.out.println("Generated Public Key (first 32 bytes): " + Base64.getEncoder().encodeToString(publicKey.getEncoded()).substring(0, 32) + "...");
            System.out.println("---");

            // 2. Create an instance of our signer and sign the message.
            EcdsaSigner signer = new EcdsaSigner();
            // The first argument ("some other message") is ignored, as per the logic.
            byte[] signature = signer.sign("some other message", privateKey);

            System.out.println("Message to sign: \"message\"");
            System.out.println("Signature (Base64): " + Base64.getEncoder().encodeToString(signature));
            System.out.println("---");

            // 3. (Bonus) Verify the signature to prove it's correct.
            Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update("message".getBytes(StandardCharsets.UTF_8));
            boolean isVerified = ecdsaVerify.verify(signature);

            System.out.println("Verification Result: " + (isVerified ? "SUCCESS" : "FAILURE"));

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            System.err.println("An error occurred during the cryptographic process.");
            e.printStackTrace();
        }
    }
}