import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.util.HexFormat;

/**
 * A Java class to demonstrate ECDSA signing, equivalent to the Python example.
 */
public class EcdsaSigner {

    /**
     * Signs a byte array using the given EC private key with the SHA256withECDSA algorithm.
     *
     * @param privateKey The EC private key to use for signing.
     * @param data The data to be signed.
     * @return The signature in DER-encoded format.
     * @throws NoSuchAlgorithmException If the "SHA256withECDSA" algorithm is not available.
     * @throws InvalidKeyException If the provided private key is invalid.
     * @throws SignatureException If an error occurs during the signing process.
     */
    public static byte[] signString(PrivateKey privateKey, byte[] data)
            throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        
        // Get a Signature instance for the "SHA256withECDSA" algorithm.
        // This is the Java equivalent of Python's ec.ECDSA(hashes.SHA256()).
        Signature ecdsaSign = Signature.getInstance("SHA256withECDSA");

        // Initialize the Signature instance for signing with the private key.
        ecdsaSign.initSign(privateKey);

        // Pass the data to be signed to the Signature instance.
        ecdsaSign.update(data);

        // Generate the signature.
        byte[] signature = ecdsaSign.sign();
        
        return signature;
    }

    /**
     * A main method to demonstrate the usage of the signString function.
     */
    public static void main(String[] args) {
        try {
            // 1. Generate an EC key pair.
            // The Python example's comment mentions ec.SECP384R1(), so we use the same curve.
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1");
            kpg.initialize(ecSpec, new SecureRandom());
            KeyPair keyPair = kpg.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();

            // 2. Define the data to be signed.
            // Equivalent to b"this is some data I'd like to sign"
            byte[] data = "this is some data I'd like to sign".getBytes(StandardCharsets.UTF_8);

            // 3. Sign the data using our function.
            byte[] signature = signString(privateKey, data);

            // 4. Print the resulting signature in a readable format (Hex).
            System.out.println("Signature (Hex): " + HexFormat.of().formatHex(signature));

            // 5. (Optional) Verify the signature to confirm it's correct.
            Signature ecdsaVerify = Signature.getInstance("SHA256withECDSA");
            ecdsaVerify.initVerify(keyPair.getPublic());
            ecdsaVerify.update(data);
            boolean isValid = ecdsaVerify.verify(signature);
            System.out.println("Signature is valid: " + isValid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}