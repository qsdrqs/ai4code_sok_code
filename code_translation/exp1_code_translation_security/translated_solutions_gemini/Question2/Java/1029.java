import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

/**
 * This class is a Java translation of the provided Python ECDSA script.
 * It uses Java's built-in security libraries to perform the same cryptographic operations.
 *
 * - Python's `ecdsa.NIST384p` curve is equivalent to `secp384r1` in Java.
 * - The signing algorithm used is `SHA384withECDSA`.
 * - Keys and signatures are printed in Base64 format, a common way to represent binary data as text.
 */
public class EcdsaTranslation {

    /**
     * Corresponds to the Python function `ecdsa_key`.
     * Generates a new key pair, signs the message, verifies it, and returns the signature.
     * Note: The 'key' parameter from the Python function was unused and is omitted here.
     *
     * @param message The message to sign as a byte array.
     * @return The generated signature as a byte array.
     * @throws Exception if any cryptographic error occurs.
     */
    public static byte[] ecdsaKey(byte[] message) throws Exception {
        // sk = SigningKey.generate(curve=NIST384p)
        // vk = sk.get_verifying_key()
        KeyPair keyPair = ecdsaGenKey();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();

        // signature = sk.sign(message)
        byte[] signature = ecdsaSign(sk, message);

        // print(vk.verify(signature, message))
        boolean isVerified = ecdsaVerifyKey(vk, signature, message);
        System.out.println("Verification result from within ecdsaKey: " + isVerified);

        return signature;
    }

    /**
     * Corresponds to the Python function `ecdsa_verify`.
     * This is an alias for ecdsaVerifyKey, as they perform the same action.
     *
     * @param signature The signature to verify.
     * @param message   The original message.
     * @param vk        The public key (verifying key).
     * @return True if the signature is valid, false otherwise.
     * @throws Exception if any cryptographic error occurs.
     */
    public static boolean ecdsaVerify(PublicKey vk, byte[] signature, byte[] message) throws Exception {
        return ecdsaVerifyKey(vk, signature, message);
    }

    /**
     * Corresponds to the Python function `ecdsa_genkey`.
     * Generates an ECDSA key pair using the secp384r1 curve (NIST384p).
     *
     * @return A KeyPair object containing the private (sk) and public (vk) keys.
     * @throws NoSuchAlgorithmException if the EC algorithm is not available.
     * @throws InvalidAlgorithmParameterException if the curve spec is invalid.
     */
    public static KeyPair ecdsaGenKey() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
        // NIST384p is equivalent to the standard name "secp384r1"
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp384r1");
        keyGen.initialize(ecSpec, new SecureRandom());
        return keyGen.generateKeyPair();
    }

    /**
     * Corresponds to the Python function `ecdsa_sign`.
     * Signs a message using the provided private key.
     *
     * @param sk      The private key (signing key).
     * @param message The message to sign.
     * @return The signature as a byte array.
     * @throws NoSuchAlgorithmException if the signing algorithm is not available.
     * @throws InvalidKeyException if the private key is invalid.
     * @throws SignatureException if an error occurs during signing.
     */
    public static byte[] ecdsaSign(PrivateKey sk, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature ecdsaSign = Signature.getInstance("SHA384withECDSA");
        ecdsaSign.initSign(sk);
        ecdsaSign.update(message);
        return ecdsaSign.sign();
    }

    /**
     * Corresponds to the Python function `ecdsa_verify_key`.
     * Verifies a signature against a message using the provided public key.
     *
     * @param vk        The public key (verifying key).
     * @param signature The signature to verify.
     * @param message   The original message.
     * @return True if the signature is valid, false otherwise.
     * @throws NoSuchAlgorithmException if the signing algorithm is not available.
     * @throws InvalidKeyException if the public key is invalid.
     * @throws SignatureException if an error occurs during verification.
     */
    public static boolean ecdsaVerifyKey(PublicKey vk, byte[] signature, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature ecdsaVerify = Signature.getInstance("SHA384withECDSA");
        ecdsaVerify.initVerify(vk);
        ecdsaVerify.update(message);
        return ecdsaVerify.verify(signature);
    }

    /**
     * The main method, equivalent to the `if __name__ == "__main__":` block in Python.
     */
    public static void main(String[] args) {
        try {
            // message = b"This is a test message"
            byte[] message = "This is a test message".getBytes(StandardCharsets.UTF_8);

            // sk,vk = ecdsa_genkey()
            KeyPair keyPair = ecdsaGenKey();
            PrivateKey sk = keyPair.getPrivate();
            PublicKey vk = keyPair.getPublic();

            // print(sk.to_string())
            // In Java, getEncoded() returns the key in a standard format (PKCS#8 for private).
            // We use Base64 to get a printable string representation.
            System.out.println("Private Key (SK): " + Base64.getEncoder().encodeToString(sk.getEncoded()));

            // print(vk.to_string())
            // In Java, getEncoded() returns the key in a standard format (X.509 for public).
            System.out.println("Public Key (VK): " + Base64.getEncoder().encodeToString(vk.getEncoded()));

            // signature = ecdsa_sign(sk,message)
            byte[] signature = ecdsaSign(sk, message);

            // print(signature)
            System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));

            // print(ecdsa_verify_key(vk,signature,message))
            boolean isSignatureValid = ecdsaVerifyKey(vk, signature, message);
            System.out.println("Verification successful: " + isSignatureValid);

        } catch (Exception e) {
            // Java's security methods throw checked exceptions, which we handle here.
            e.printStackTrace();
        }
    }
}