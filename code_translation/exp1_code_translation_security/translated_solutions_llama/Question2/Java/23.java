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

public class ECDSASignerExample {

    public static void main(String[] args) throws Exception {
        // Generate a random key pair
        AsymmetricCipherKeyPair keyPair = generateKeyPair();

        // Sign a message
        byte[] message = "i am a message".getBytes();
        byte[] signature = sign(message, keyPair);

        // Verify the signature (optional)
        boolean isValid = verify(message, signature, keyPair);

        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
        System.out.println("Is signature valid? " + isValid);
    }

    /**
     * Generate a random ECDSA key pair.
     *
     * @return The generated key pair.
     * @throws Exception If an error occurs during key generation.
     */
    private static AsymmetricCipherKeyPair generateKeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        X9ECParameters params = SECNamedCurves.getByName("secp192r1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, secureRandom));
        return generator.generateKeyPair();
    }

    /**
     * Sign a message using the provided key pair.
     *
     * @param message The message to sign.
     * @param keyPair The key pair to use for signing.
     * @return The generated signature.
     * @throws Exception If an error occurs during signing.
     */
    public static byte[] sign(byte[] message, AsymmetricCipherKeyPair keyPair) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, keyPair.getPrivate());
        int[] signature = signer.generateSignature(message);
        byte[] derSignature = new byte[64];
        derSignature[0] = (byte) 0x30;
        derSignature[1] = (byte) 0x44;
        derSignature[2] = (byte) 0x02;
        derSignature[3] = (byte) 0x20;
        System.arraycopy(intToBytes(signature[0]), 0, derSignature, 4, 32);
        derSignature[36] = (byte) 0x02;
        derSignature[37] = (byte) 0x20;
        System.arraycopy(intToBytes(signature[1]), 0, derSignature, 38, 32);
        return derSignature;
    }

    /**
     * Verify a signature using the provided public key.
     *
     * @param message    The original message.
     * @param signature  The signature to verify.
     * @param keyPair The key pair to use for verification.
     * @return True if the signature is valid, false otherwise.
     * @throws Exception If an error occurs during verification.
     */
    public static boolean verify(byte[] message, byte[] signature, AsymmetricCipherKeyPair keyPair) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(false, keyPair.getPublic());
        int[] derSignature = new int[2];
        derSignature[0] = bytesToInt(signature, 4);
        derSignature[1] = bytesToInt(signature, 38);
        return signer.verifySignature(message, derSignature);
    }

    /**
     * Convert an integer to a byte array.
     *
     * @param value The integer to convert.
     * @return The byte array representation of the integer.
     */
    private static byte[] intToBytes(int value) {
        byte[] bytes = new byte[32];
        for (int i = 0; i < 32; i++) {
            bytes[31 - i] = (byte) (value & 0xFF);
            value >>= 8;
        }
        return bytes;
    }

    /**
     * Convert a byte array to an integer.
     *
     * @param bytes The byte array to convert.
     * @param offset The offset to start reading from.
     * @return The integer representation of the byte array.
     */
    private static int bytesToInt(byte[] bytes, int offset) {
        int value = 0;
        for (int i = 0; i < 32; i++) {
            value = (value << 8) | (bytes[offset + i] & 0xFF);
        }
        return value;
    }
}