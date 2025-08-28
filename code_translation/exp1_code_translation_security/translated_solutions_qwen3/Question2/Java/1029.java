import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.Security;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.security.spec.ECPoint;
import java.util.Random;

public class ECDSAExample {

    // Register Bouncy Castle as the security provider
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Generates an ECDSA key pair using the NIST P-384 (secp384r1) curve.
     *
     * @return A KeyPair containing the private and public keys.
     */
    public static KeyPair ecdsa_genkey() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", "BC");
            kpg.initialize(new ECGenParameterSpec("secp384r1"), new Random());
            return kpg.generateKeyPair();
        } catch (Exception e) {
            throw new RuntimeException("Error generating key pair", e);
        }
    }

    /**
     * Signs a message using the private key.
     *
     * @param sk      The private key.
     * @param message The message to sign (as a byte array).
     * @return The signature as a byte array.
     */
    public static byte[] ecdsa_sign(PrivateKey sk, byte[] message) {
        try {
            Signature signature = Signature.getInstance("SHA1withECDSA", "BC");
            signature.initSign(sk);
            signature.update(message);
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException("Error signing message", e);
        }
    }

    /**
     * Verifies a signature using the public key.
     *
     * @param vk       The public key.
     * @param signature The signature to verify.
     * @param message  The original message.
     * @return true if the signature is valid, false otherwise.
     */
    public static boolean ecdsa_verify(PublicKey vk, byte[] signature, byte[] message) {
        try {
            Signature sig = Signature.getInstance("SHA1withECDSA", "BC");
            sig.initVerify(vk);
            sig.update(message);
            return sig.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException("Error verifying signature", e);
        }
    }

    /**
     * Converts a private key to a 48-byte array (as in Python's sk.to_string()).
     */
    private static byte[] getPrivateKeyBytes(PrivateKey key) {
        ECPrivateKey ecKey = (ECPrivateKey) key;
        BigInteger s = ecKey.getS();
        return toNBytes(s, 48); // 384 bits = 48 bytes
    }

    /**
     * Converts a public key to its raw uncompressed point encoding (as in Python's vk.to_string()).
     */
    private static byte[] getPublicKeyBytes(PublicKey key) {
        ECPublicKey ecKey = (ECPublicKey) key;
        ECPoint w = ecKey.getW();
        return w.getEncoded(false); // false = uncompressed format
    }

    /**
     * Converts a BigInteger to a fixed-length byte array, padding with leading zeros if necessary.
     */
    private static byte[] toNBytes(BigInteger value, int length) {
        byte[] bytes = value.toByteArray();
        if (bytes.length == length) {
            return bytes;
        }
        byte[] padded = new byte[length];
        int offset = bytes.length < length ? (length - bytes.length) : 0;
        System.arraycopy(bytes, 0, padded, offset, Math.min(bytes.length, length));
        return padded;
    }

    /**
     * Converts a byte array to a hexadecimal string for printing.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xFF));
        }
        return sb.toString();
    }

    /**
     * Main method to test the ECDSA functionality.
     */
    public static void main(String[] args) {
        String msg = "This is a test message";
        byte[] message = msg.getBytes(java.nio.charset.StandardCharsets.UTF_8);

        // Generate key pair
        KeyPair keyPair = ecdsa_genkey();
        PrivateKey sk = keyPair.getPrivate();
        PublicKey vk = keyPair.getPublic();

        // Convert keys to raw byte arrays
        byte[] skBytes = getPrivateKeyBytes(sk);
        byte[] vkBytes = getPublicKeyBytes(vk);

        // Print keys and signature
        System.out.println("Private Key (hex): " + bytesToHex(skBytes));
        System.out.println("Public Key (hex):  " + bytesToHex(vkBytes));

        byte[] signature = ecdsa_sign(sk, message);
        System.out.println("Signature (hex):   " + bytesToHex(signature));

        boolean verified = ecdsa_verify(vk, signature, message);
        System.out.println("Signature verified: " + verified);
    }
}