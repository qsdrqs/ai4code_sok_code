import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairGenerationParameters;
import org.bouncycastle.crypto.params.ECKeyPairParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;

import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws Exception {
        // Generate a key pair
        AsymmetricCipherKeyPair keyPair = generateKeyPair();

        // Sign a message
        byte[] message = "testing".getBytes();
        byte[] signature = signMessage(keyPair.getPrivate(), message);

        // Verify the signature
        boolean isValid = verifySignature(keyPair.getPublic(), message, signature);
        System.out.println(isValid);
    }

    /**
     * Generate a key pair using the NIST384p curve.
     *
     * @return The generated key pair.
     * @throws Exception If an error occurs during key generation.
     */
    private static AsymmetricCipherKeyPair generateKeyPair() throws Exception {
        X9ECParameters params = SECNamedCurves.getByName("secp384r1");
        ECDomainParameters domainParams = new ECDomainParameters(params.getCurve(), params.getG(), params.getN(), params.getH());

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        generator.init(new ECKeyGenerationParameters(domainParams, new SecureRandom()));
        return generator.generateKeyPair();
    }

    /**
     * Sign a message using the provided private key.
     *
     * @param privateKey The private key to use for signing.
     * @param message    The message to sign.
     * @return The signature of the message.
     * @throws Exception If an error occurs during signing.
     */
    private static byte[] signMessage(AsymmetricCipherKeyPair privateKey, byte[] message) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(true, privateKey.getPrivate());
        BigInteger[] signature = signer.generateSignature(message);
        // Convert the signature to DER format
        return derEncode(signature[0], signature[1], ((ECKeyPairGenerator) privateKey).getParameters().getN());
    }

    /**
     * Verify a signature using the provided public key.
     *
     * @param publicKey  The public key to use for verification.
     * @param message    The original message.
     * @param signature  The signature to verify.
     * @return True if the signature is valid, false otherwise.
     * @throws Exception If an error occurs during verification.
     */
    private static boolean verifySignature(AsymmetricCipherKeyPair publicKey, byte[] message, byte[] signature) throws Exception {
        ECDSASigner signer = new ECDSASigner();
        signer.init(false, publicKey.getPublic());
        BigInteger[] values = derDecode(signature);
        return signer.verifySignature(message, values[0], values[1]);
    }

    // Helper methods for DER encoding and decoding
    private static byte[] derEncode(BigInteger r, BigInteger s, BigInteger n) {
        int size = 2 + getByteLength(r) + getByteLength(s);
        byte[] der = new byte[size];
        int offset = 0;
        der[offset++] = 0x30; // SEQUENCE
        der[offset++] = (byte) (size - 2);
        der[offset++] = 0x02; // INTEGER
        der[offset++] = (byte) getByteLength(r);
        System.arraycopy(bigIntToBytes(r), 0, der, offset, getByteLength(r));
        offset += getByteLength(r);
        der[offset++] = 0x02; // INTEGER
        der[offset++] = (byte) getByteLength(s);
        System.arraycopy(bigIntToBytes(s), 0, der, offset, getByteLength(s));
        return der;
    }

    private static BigInteger[] derDecode(byte[] der) {
        int offset = 0;
        if (der[offset++] != 0x30) {
            throw new RuntimeException("Invalid DER encoding");
        }
        int length = der[offset++] & 0xff;
        if (der[offset++] != 0x02) {
            throw new RuntimeException("Invalid DER encoding");
        }
        int rLength = der[offset++] & 0xff;
        BigInteger r = new BigInteger(1, new byte[]{der[offset], der[offset + 1]});
        offset += rLength;
        if (der[offset++] != 0x02) {
            throw new RuntimeException("Invalid DER encoding");
        }
        int sLength = der[offset++] & 0xff;
        BigInteger s = new BigInteger(1, new byte[]{der[offset], der[offset + 1]});
        return new BigInteger[]{r, s};
    }

    private static byte[] bigIntToBytes(BigInteger n) {
        int byteLen = n.bitLength() / 8 + 1;
        byte[] b = new byte[byteLen];
        for (int i = 0; i < byteLen; i++) {
            b[byteLen - 1 - i] = n.toByteArray()[i];
        }
        return b;
    }

    private static int getByteLength(BigInteger n) {
        return (n.bitLength() + 7) / 8;
    }
}