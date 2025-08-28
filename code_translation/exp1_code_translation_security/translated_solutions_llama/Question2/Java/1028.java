import java.security.*;
import java.security.spec.ECPrivateKeySpec;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class ECDSASignerVerifier {

    /**
     * Signs a given message using an ECDSA signing key.
     * 
     * @param privateKeyBytes ECDSA signing key in bytes
     * @param message         message to be signed
     * @return signature in bytes
     * @throws Exception if an error occurs during signing
     */
    public static byte[] sign(byte[] privateKeyBytes, byte[] message) throws Exception {
        // Create a private key from the provided bytes
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        // Create a signature instance
        Signature signature = Signature.getInstance("SHA256withECDSA");
        signature.initSign(privateKey);

        // Update the signature with the message
        signature.update(message);

        // Sign the message
        return signature.sign();
    }

    /**
     * Verifies a given signature using an ECDSA verification key.
     * 
     * @param publicKeyBytes ECDSA verification key in bytes
     * @param message        message to be verified
     * @param signature      signature to be verified in bytes
     * @return true if signature is valid, false otherwise
     * @throws Exception if an error occurs during verification
     */
    public static boolean verify(byte[] publicKeyBytes, byte[] message, byte[] signature) throws Exception {
        // Create a public key from the provided bytes
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        // Create a signature instance
        Signature sig = Signature.getInstance("SHA256withECDSA");
        sig.initVerify(publicKey);

        // Update the signature with the message
        sig.update(message);

        // Verify the signature
        return sig.verify(signature);
    }

    public static void main(String[] args) throws Exception {
        // Generate a key pair (for demonstration purposes only)
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(256); // Use a secure key size
        KeyPair keyPair = kpg.generateKeyPair();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();

        // Message to be signed
        String messageStr = "Hello, World!";
        byte[] message = messageStr.getBytes();

        // Sign the message
        byte[] signatureBytes = sign(privateKeyBytes, message);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signatureBytes));

        // Verify the signature
        boolean isValid = verify(publicKeyBytes, message, signatureBytes);
        System.out.println("Is signature valid? " + isValid);
    }
}